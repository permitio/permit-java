package io.permit.sdk;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import io.permit.sdk.api.PermitApiError;
import io.permit.sdk.api.PermitContextError;
import io.permit.sdk.openapi.models.RoleCreate;
import io.permit.sdk.openapi.models.RoleRead;
import io.permit.sdk.openapi.models.RoleUpdate;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this e2e test should run against a clean permit environment.
 * if the environment contains any objects the test will fail.
 * eventually we want to create an environment programmatically
 * and then extract the api key and start the test.
 */
public class RolesApiE2ETest extends PermitE2ETestBase {
    @Test
    void testRolesApi() {
        // init the client
        Permit permit = new Permit(this.config);
        Gson gson = new Gson();

        // roles lifecycle
        try {
            // list
            RoleRead[] emptyRoles = permit.api.roles.list();
            assertEquals(emptyRoles.length, 0);

            // create
            RoleRead admin = permit.api.roles.create(
                new RoleCreate("admin","Admin").withDescription("an admin role")
            );
            assertNotNull(admin);
            assertEquals(admin.key, "admin");
            assertEquals(admin.name, "Admin");
            assertEquals(admin.description, "an admin role");


            RoleRead viewer = permit.api.roles.create(
                new RoleCreate("viewer","Viewer").withDescription("an viewer role")
            );
            assertNotNull(viewer);
            assertEquals(viewer.key, "viewer");
            assertEquals(viewer.name, "Viewer");
            assertEquals(viewer.description, "an viewer role");
            
            RoleRead[] roles = permit.api.roles.list();
            assertEquals(roles.length, 2);
            assertEquals(roles[0].key, "admin");
            assertEquals(roles[0].name, "Admin");
            assertEquals(roles[1].key, "viewer");
            assertEquals(roles[1].name, "Viewer");

            // get
            RoleRead role = permit.api.roles.get("admin");
            assertNotNull(role);
            assertEquals(role.key, "admin");
            assertEquals(role.name, "Admin");

            // get 404 no such role
            PermitApiError notFoundError = assertThrows(PermitApiError.class, () -> {
                permit.api.roles.get("editor");
            });
            assertEquals(notFoundError.getMessage(), "Got error status code: 404");
            assertEquals(notFoundError.getResponseCode(), 404);
            LinkedTreeMap error = notFoundError.getErrorObject();
            assertEquals(error.get("error_code"), "NOT_FOUND");
            assertTrue(error.get("message").toString().startsWith("The requested data could not be found"));

            // delete
            try {
                permit.api.roles.delete("admin");
            } catch (PermitApiError e) {
                fail("got error: " + e);
            }

            roles = permit.api.roles.list();
            assertEquals(roles.length, 1);
            assertEquals(roles[0].key, "viewer");
            assertEquals(roles[0].name, "Viewer");
            assertEquals(roles[0].description, "an viewer role");

            // update
            permit.api.roles.update("viewer", new RoleUpdate().withDescription("new description"));

            roles = permit.api.roles.list();
            assertEquals(roles.length, 1);
            assertEquals(roles[0].key, "viewer");
            assertEquals(roles[0].name, "Viewer");
            assertEquals(roles[0].description, "new description");

            // delete
            try {
                permit.api.roles.delete("viewer");
            } catch (PermitApiError e) {
                fail("got error: " + e);
            }

            roles = permit.api.roles.list();
            assertEquals(roles.length, 0);

            // we already deleted this
            PermitApiError exception = assertThrows(PermitApiError.class, () -> {
                permit.api.roles.delete("viewer");
            });
            assertEquals(exception.getResponseCode(), 404);
        } catch (IOException | PermitApiError | PermitContextError e) {
            fail("got error: " + e);
        }
    }
}
