package io.permit.sdk.e2e;

import com.google.common.primitives.Booleans;
import io.permit.sdk.PermitE2ETestBase;
import io.permit.sdk.api.PermitApiError;
import io.permit.sdk.api.PermitContextError;
import io.permit.sdk.Permit;
import io.permit.sdk.api.models.CreateOrUpdateResult;
import io.permit.sdk.enforcement.*;
import io.permit.sdk.openapi.models.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this e2e test should run against a clean permit environment.
 * if the environment contains any objects the test will fail.
 * eventually we want to create an environment programmatically
 * and then extract the api key and start the test.
 */
public class RbacE2ETest extends PermitE2ETestBase {
    private final Logger logger = LoggerFactory.getLogger(RbacE2ETest.class);

    private static final String TENANT_RESOURCE_KEY = "__tenant";

    @Test
    void testPermissionCheckRBAC() {
        // init the client
        Permit permit = new Permit(this.config);

        try {
            // resource actions
            HashMap<String, ActionBlockEditable> actions = new HashMap<>();
            actions.put("create", new ActionBlockEditable());
            actions.put("read", new ActionBlockEditable().withName("Read").withDescription("Read Action"));
            actions.put("update", new ActionBlockEditable());
            actions.put("delete", new ActionBlockEditable());


            // resource attributes
            HashMap<String, Object> attributes = new HashMap<String,Object>();
            attributes.put(
                "private",
                new AttributeBlockEditable().withType(AttributeType.BOOL).withDescription("whether the document is private")
            );

            // create document resource
            ResourceCreate resourceInput = ((
                new ResourceCreate("document", "Document", actions)
            )
                .withUrn("prn:gdrive:document")
                .withDescription("google drive document")
            );
            ResourceRead document = permit.api.resources.create(resourceInput);

            // verify create output
            assertNotNull(document);
            assertNotNull(document.id);
            assertEquals(document.key, "document");
            assertEquals(document.name, "Document");
            assertEquals(document.description, "google drive document");
            assertEquals(document.urn, "prn:gdrive:document");
            assertEquals(document.actions.size(), 4);
            assertTrue(document.actions.containsKey("create"));
            assertTrue(document.actions.containsKey("read"));
            assertTrue(document.actions.containsKey("update"));
            assertTrue(document.actions.containsKey("delete"));

            // verify list output
            ResourceRead[] resources = permit.api.resources.list();
            assertEquals(resources.length, 1);
            assertEquals(resources[0].id, document.id);
            assertEquals(resources[0].key, document.key);
            assertEquals(resources[0].name, document.name);
            assertEquals(resources[0].description, document.description);
            assertEquals(resources[0].urn, document.urn);

            // create admin role
            RoleRead admin = permit.api.roles.create(
                new RoleCreate("admin","Admin")
                    .withDescription("an admin role")
                    .withPermissions(
                            new ArrayList<>(Arrays.asList("document:create", "document:read"))
                    )
            );
            assertNotNull(admin);
            assertEquals(admin.key, "admin");
            assertEquals(admin.name, "Admin");
            assertEquals(admin.description, "an admin role");
            assertTrue(admin.permissions.containsAll(Arrays.asList("document:create", "document:read")));

            // create viewer role
            RoleRead viewer = permit.api.roles.create(
                    new RoleCreate("viewer","Viewer")
                            .withDescription("an viewer role")
            );
            assertNotNull(viewer);
            assertEquals(viewer.key, "viewer");
            assertEquals(viewer.name, "Viewer");
            assertEquals(viewer.description, "an viewer role");
            assertEquals(viewer.permissions.size(), 0);

            // assign permissions to roles
            viewer = permit.api.roles.assignPermissions("viewer", new ArrayList<>(Arrays.asList("document:read")));
            assertEquals(viewer.key, "viewer");
            assertEquals(viewer.permissions.size(), 1);
            assertTrue(viewer.permissions.contains("document:read"));
            assertFalse(viewer.permissions.contains("document:create"));

            // create a tenant
            TenantRead tenant = permit.api.tenants.create(
                    new TenantCreate("tesla", "Tesla Inc").withDescription("The car company")
            );
            assertEquals(tenant.key, "tesla");
            assertEquals(tenant.name, "Tesla Inc");
            assertEquals(tenant.description, "The car company");
            assertNull(tenant.attributes);

            // create another tenant
            HashMap<String, Object> tenantAttributes = new HashMap<>();
            tenantAttributes.put("tier", "pro");
            tenantAttributes.put("unit", "one");

            TenantRead tenant2 = permit.api.tenants.create(
                    new TenantCreate("twitter", "Twitter Inc").withAttributes(tenantAttributes)
            );
            assertEquals(tenant2.key, "twitter");
            assertEquals(((String)tenant2.attributes.get("tier")), "pro");
            assertEquals(((String)tenant2.attributes.get("unit")), "one");

            // create a user
            HashMap<String, Object> userAttributes = new HashMap<>();
            userAttributes.put("age", Integer.valueOf(50));
            userAttributes.put("fav_color", "red");


            User userInput = (new User.Builder("auth0|elon"))
                .withEmail("elonmusk@tesla.com")
                .withFirstName("Elon")
                .withLastName("Musk")
                .withAttributes(userAttributes)
                .build();
            CreateOrUpdateResult<UserRead> result = permit.api.users.sync(userInput);
            UserRead user = result.getResult();
            // assertTrue(result.wasCreated());
            assertEquals(user.key, "auth0|elon");
            assertEquals(user.email, "elonmusk@tesla.com");
            assertEquals(user.firstName, "Elon");
            assertEquals(user.lastName, "Musk");
            assertEquals(user.attributes.size(), 2);
            assertEquals(((Double)user.attributes.get("age")).doubleValue(), 50.0);
            assertEquals(((String)user.attributes.get("fav_color")), "red");

            // assign role to user in tenant
            RoleAssignmentRead ra = permit.api.users.assignRole("auth0|elon", "viewer", "tesla");
            assertEquals(ra.userId, user.id);
            assertEquals(ra.roleId, viewer.id);
            assertEquals(ra.tenantId, tenant.id);
            assertTrue(ra.user.equals(user.key) || ra.user.equals(user.email)); // TODO: remove user.email
            assertEquals(ra.role, viewer.key);
            assertEquals(ra.tenant, tenant.key);

            // assign a second role in another tenant
            RoleAssignmentRead ra2 = permit.api.users.assignRole("auth0|elon", "admin", "twitter");
            assertEquals(ra2.userId, user.id);
            assertEquals(ra2.roleId, admin.id);
            assertEquals(ra2.tenantId, tenant2.id);

            logger.info("sleeping 20 seconds before permit.check() to make sure all writes propagated from cloud to PDP");
            Thread.sleep(20000);

            // positive permission check (will be true because elon is a viewer, and a viewer can read a document)
            logger.info("testing positive permission check");
            HashMap<String,Object> resourceAttrs = new HashMap<String,Object>();
            resourceAttrs.put("sdfa", new ArrayList<String>(Arrays.asList("sdf","sdf")));

            assertTrue(permit.check(
                User.fromString("auth0|elon"),
                "read",
                new Resource.Builder("document").withTenant(tenant.key).withAttributes(resourceAttrs).build()
            ));

            logger.info("testing positive permission check with complete user object");
            assertTrue(permit.check(
                    userInput,
                    "read",
                    new Resource.Builder("document").withTenant(tenant.key).build()
            ));

            // negative permission check (will be false because a viewer cannot create a document)
            logger.info("testing negative permission check");
            assertFalse(permit.check(
                User.fromString("auth0|elon"),
                "create",
                new Resource.Builder("document").withTenant(tenant.key).build()
            ));

            logger.info("testing bulk permission check");
            boolean[] checks = permit.bulkCheck(Arrays.asList(
                // positive permission check
                new CheckQuery(
                    userInput,
                    "read",
                    new Resource.Builder("document").withTenant(tenant.key).build()
                ),
                // negative permission check
                new CheckQuery(
                    User.fromString("auth0|elon"),
                    "create",
                    new Resource.Builder("document").withTenant(tenant.key).build()
                )
            ));
            assertEquals(checks.length, 2);
            assertTrue(checks[0]);
            assertFalse(checks[1]);

            logger.info("testing 'check in all tenants' on read:document");
            List<TenantDetails> allowedTenants = permit.checkInAllTenants(
                userInput,
                "read",
                new Resource.Builder("document").build()
            );
            assertEquals(allowedTenants.size(), 2);
            assertTrue(allowedTenants.get(0).key.equals(tenant.key) || allowedTenants.get(0).key.equals(tenant2.key));
            assertTrue(allowedTenants.get(1).key.equals(tenant.key) || allowedTenants.get(1).key.equals(tenant2.key));
            assertNotEquals(allowedTenants.get(0).key, allowedTenants.get(1).key);

            logger.info("testing 'check in all tenants' on create:document");
            List<TenantDetails> allowedTenants2 = permit.checkInAllTenants(
                    userInput,
                    "create",
                    new Resource.Builder("document").build()
            );
            assertEquals(allowedTenants2.size(), 1);
            assertEquals(allowedTenants2.get(0).key, tenant2.key);
            assertEquals(((String)allowedTenants2.get(0).attributes.get("unit")), "one");

            logger.info("testing 'get user permissions' on user 'elon'");
            UserPermissions permissions = permit.getUserPermissions(
                new GetUserPermissionsQuery(
                    User.fromString("auth0|elon")
                )
            );
            assertEquals(permissions.keySet().size(), 2); // elon has access to 2 tenants
            String tenantObjectKey = String.format("%s:%s", TENANT_RESOURCE_KEY, tenant.key);
            String tenant2ObjectKey = String.format("%s:%s", TENANT_RESOURCE_KEY, tenant2.key);
            assertTrue(permissions.containsKey(tenantObjectKey));
            assertTrue(permissions.containsKey(tenant2ObjectKey));
            assertTrue(permissions.get(tenantObjectKey).permissions.contains("document:read"));
            assertTrue(permissions.get(tenant2ObjectKey).permissions.contains("document:create"));

            // change the user role
            permit.api.users.assignRole(user.key, admin.key, tenant.key);
            permit.api.users.unassignRole(user.key, viewer.key, tenant.key);

            // list user roles in tenant
            RoleAssignmentRead[] assignedRoles = permit.api.users.getAssignedRoles(user.key);
            assertEquals(assignedRoles.length, 1);
            assertEquals(assignedRoles[0].userId, user.id);
            assertEquals(assignedRoles[0].roleId, admin.id);
            assertEquals(assignedRoles[0].tenantId, tenant.id);

            logger.info("sleeping 2 seconds before permit.check() to make sure all writes propagated from cloud to PDP");
            Thread.sleep(2000);

            // run the same negative permission check again, this time it's true
            logger.info("testing previously negative permission check, should now be positive");
            assertTrue(permit.check(
                    User.fromString("auth0|elon"),
                    "create",
                    new Resource.Builder("document").withTenant(tenant.key).build()
            ));
        } catch (IOException | PermitApiError | PermitContextError e) {
            fail("got error: " + e);
        } catch (InterruptedException e) {
            fail("got interrupt: " + e);
        } finally {
            // cleanup
            try {
                permit.api.resources.delete("document");
                permit.api.roles.delete("admin");
                permit.api.roles.delete("viewer");
                permit.api.tenants.delete("tesla");
                permit.api.tenants.delete("twitter");
                permit.api.users.delete("auth0|elon");
                assertEquals(permit.api.resources.list().length, 0);
                assertEquals(permit.api.roles.list().length, 0);
                assertEquals(permit.api.tenants.list().length, 1);
                assertEquals(permit.api.users.list().data.size(), 0);
            } catch (IOException | PermitApiError | PermitContextError e) {
                fail("got error: " + e);
            }
        }
    }
}
