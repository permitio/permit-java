package io.permit.sdk.e2e;

import io.permit.sdk.PermitE2ETestBase;
import io.permit.sdk.api.PermitApiError;
import io.permit.sdk.api.PermitContextError;
import io.permit.sdk.Permit;
import io.permit.sdk.api.models.CreateOrUpdateResult;
import io.permit.sdk.enforcement.Resource;
import io.permit.sdk.enforcement.User;
import io.permit.sdk.openapi.models.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this e2e test should run against a clean permit environment.
 * if the environment contains any objects the test will fail.
 * eventually we want to create an environment programmatically
 * and then extract the api key and start the test.
 */
public class RbacE2ETest extends PermitE2ETestBase {
    private final Logger logger = LoggerFactory.getLogger(RbacE2ETest.class);

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
