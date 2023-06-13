package io.permit.sdk.endpoints;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import io.permit.sdk.Permit;
import io.permit.sdk.PermitE2ETestBase;
import io.permit.sdk.api.PermitApiError;
import io.permit.sdk.api.PermitContextError;
import io.permit.sdk.openapi.models.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this e2e test should run against a clean permit environment.
 * if the environment contains any objects the test will fail.
 * eventually we want to create an environment programmatically
 * and then extract the api key and start the test.
 */
public class RolesApiE2ETest extends PermitE2ETestBase {
    private static final String DOCUMENT_KEY = "document";
    private static final String ADMIN_KEY = "testadmin";
    private static final String EMPTY_ROLE_KEY = "emptyrole";
    private static ResourceCreate documentData;
    private static RoleCreate adminData;
    private static RoleCreate duplicateRoleData;
    private static RoleCreate emptyRoleData;

    @BeforeAll
    static void setup() {
        // resource actions
        HashMap<String, ActionBlockEditable> actions = new HashMap<>();
        actions.put("create", new ActionBlockEditable());
        actions.put("read", new ActionBlockEditable().withName("Read").withDescription("Read Action"));
        actions.put("update", new ActionBlockEditable());
        actions.put("delete", new ActionBlockEditable());

        // create document resource
        documentData = ((
            new ResourceCreate("document", "Document", actions)
        )
            .withUrn("prn:gdrive:document")
            .withDescription("google drive document")
        );

        adminData = new RoleCreate(ADMIN_KEY, "Admin")
                .withDescription("a test role")
                // Assuming there is a method to add permissions
                .withPermissions(Arrays.asList(
                        String.format("%s:create", DOCUMENT_KEY),
                        String.format("%s:read", DOCUMENT_KEY)
                ));

        duplicateRoleData = new RoleCreate(ADMIN_KEY, "Admin2").withDescription("a test duplicate role");

        emptyRoleData = new RoleCreate(EMPTY_ROLE_KEY, "Empty").withDescription("empty role");
    }

    void cleanup() {
        Permit permit = new Permit(this.config);
        try {
            try {
                permit.api.roles.delete(adminData.key);
            } catch (PermitApiError e) {
                logger.info("SKIPPING delete of role: " + adminData.key);
            }
            try {
                permit.api.roles.delete(emptyRoleData.key);
            } catch (PermitApiError e) {
                logger.info("SKIPPING delete of role: " + emptyRoleData.key);
            }
            try {
                permit.api.resources.delete(DOCUMENT_KEY);
            } catch (PermitApiError e) {
                logger.info("SKIPPING delete of resource: " + DOCUMENT_KEY);
            }
        } catch (PermitContextError e) {
            fail("got error: " + e);
        } catch (IOException e) {
            fail("got error: " + e);
        }
    }

    @Test
    void testRolesApi() {
        // init the client
        Permit permit = new Permit(this.config);
        Gson gson = new Gson();

        // roles lifecycle
        try {
            logger.info("check original roles length");
            int lenRolesOriginal = permit.api.roles.list().length;

            logger.info("create document with permissions");
            permit.api.resources.create(documentData);

            logger.info("create role with permissions");
            RoleRead admin = permit.api.roles.create(adminData);
            assertNotNull(admin);
            assertEquals(admin.key, adminData.key);
            assertEquals(admin.name, adminData.name);
            assertEquals(admin.description, adminData.description);
            assertNotNull(admin.permissions);
            assertTrue(admin.permissions.contains(DOCUMENT_KEY + ":create"));
            assertTrue(admin.permissions.contains(DOCUMENT_KEY + ":read"));

            logger.info("verify number of roles increased by 1");
            RoleRead[] roles = permit.api.roles.list();
            assertEquals(roles.length, lenRolesOriginal + 1);

            logger.info("verify can find new role in the new list");
            assertTrue(Arrays.stream(roles).map(r -> r.key).collect(Collectors.toList()).contains(admin.key));

            logger.info("get non existing role -> 404");
            PermitApiError notFoundError = assertThrows(PermitApiError.class, () -> {
                permit.api.roles.get("editor");
            });
            assertEquals(notFoundError.getMessage(), "Got error status code: 404");
            assertEquals(notFoundError.getResponseCode(), 404);
            LinkedTreeMap error = notFoundError.getErrorObject();
            assertEquals(error.get("error_code"), "NOT_FOUND");
            assertTrue(error.get("message").toString().startsWith("The requested data could not be found"));

            logger.info("create existing role -> 409");
            PermitApiError duplicateError = assertThrows(PermitApiError.class, () -> {
                permit.api.roles.create(duplicateRoleData);
            });
            assertEquals(duplicateError.getResponseCode(), 409);

            logger.info("create empty role");
            RoleRead emptyRole = permit.api.roles.create(emptyRoleData);
            assertNotNull(emptyRole);
            assertEquals(emptyRole.key, emptyRoleData.key);
            assertEquals(emptyRole.name, emptyRoleData.name);
            assertEquals(emptyRole.description, emptyRoleData.description);
            assertNotNull(emptyRole.permissions);
            assertEquals(emptyRole.permissions.size(), 0);

            logger.info("verify number of roles increased by 1");
            RoleRead[] roles2 = permit.api.roles.list();
            assertEquals(roles2.length, lenRolesOriginal + 2);

            logger.info("assign permissions to roles");
            RoleRead assignedEmpty = permit.api.roles.assignPermissions(
                    emptyRole.key,
                    new ArrayList<>(Arrays.asList(DOCUMENT_KEY + ":delete"))
            );

            logger.info("Ensure permissions are assigned as expected");
            assertNotNull(assignedEmpty);
            assertEquals(assignedEmpty.key, emptyRole.key);
            assertEquals(assignedEmpty.permissions.size(), 1);
            assertTrue(assignedEmpty.permissions.contains(DOCUMENT_KEY + ":delete"));

            logger.info("Remove permissions from role");
            permit.api.roles.removePermissions(
                    admin.key,
                    new ArrayList<>(Arrays.asList(DOCUMENT_KEY + ":create"))
            );

            logger.info("Get admin role");
            RoleRead updatedAdmin = permit.api.roles.get(adminData.key);
            assertNotNull(updatedAdmin);
            assertEquals(updatedAdmin.key, adminData.key);
            assertEquals(updatedAdmin.name, adminData.name);
            assertNotNull(updatedAdmin.permissions);
            assertFalse(updatedAdmin.permissions.contains(DOCUMENT_KEY + ":create"));
            assertTrue(updatedAdmin.permissions.contains(DOCUMENT_KEY + ":read"));

            logger.info("Update admin role");
            permit.api.roles.update(
                    admin.key,
                    new RoleUpdate().withDescription("wat")
            );

            logger.info("admin changed again");
            updatedAdmin = permit.api.roles.get(adminData.key);
            assertNotNull(updatedAdmin);
            assertEquals(updatedAdmin.key, adminData.key);
            assertEquals(updatedAdmin.name, adminData.name);
            assertEquals(updatedAdmin.description, "wat");
            assertNotNull(updatedAdmin.permissions);
            assertFalse(updatedAdmin.permissions.contains(DOCUMENT_KEY + ":create"));
            assertTrue(updatedAdmin.permissions.contains(DOCUMENT_KEY + ":read"));

            logger.info("Delete objects...");
            try {
                permit.api.roles.delete(adminData.key);
            } catch (PermitApiError e) {
                fail("got error: " + e);
            }
            try {
                permit.api.roles.delete(emptyRoleData.key);
            } catch (PermitApiError e) {
                fail("got error: " + e);
            }
            try {
                permit.api.resources.delete(DOCUMENT_KEY);
            } catch (PermitApiError e) {
                fail("got error: " + e);
            }

            logger.info("Verify that again we have the initial number of roles");
            roles = permit.api.roles.list();
            assertEquals(roles.length, lenRolesOriginal);

            logger.info("Verify deleted role cannot be deleted again");
            PermitApiError exception = assertThrows(PermitApiError.class, () -> {
                permit.api.roles.delete(emptyRole.key);
            });
            assertEquals(exception.getResponseCode(), 404);
        } catch (IOException | PermitApiError | PermitContextError e) {
            fail("got error: " + e);
        }
        finally {
            cleanup();
        }
    }
}
