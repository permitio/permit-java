package io.permit.sdk;

import com.google.gson.Gson;
import io.permit.sdk.api.PermitApiError;
import io.permit.sdk.api.PermitContextError;
import io.permit.sdk.openapi.models.RoleCreate;
import io.permit.sdk.openapi.models.RoleRead;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class E2ERoleTests extends PermitE2ETest {
    @Test
    void testRolesApi() {
        // init the client
        Permit permit = new Permit(this.config);
        Gson gson = new Gson();
        RoleRead role;

        // create user lifecycle
        try {
            // list
            RoleRead[] roles = permit.api.roles.list();
            assertEquals(roles.length, 2);
            assertEquals(roles[0].key, "admin");
            assertEquals(roles[0].name, "Admin");
            assertEquals(roles[1].key, "viewer");
            assertEquals(roles[1].name, "Viewer");

            // get
            role = permit.api.roles.get("admin");
            assertNotNull(role);
            assertEquals(role.key, "admin");
            assertEquals(role.name, "Admin");

            // create
            RoleRead createdRole = permit.api.roles.create(
                new RoleCreate(
                    "editor",
                    "Editor"
                ).withDescription("an editor role")
            );
            assertNotNull(createdRole);
            assertEquals(createdRole.key, "editor");
            assertEquals(createdRole.name, "Editor");
            assertEquals(createdRole.description, "an editor role");

            try {
                permit.api.roles.delete("editor");
            } catch (PermitApiError e) {
                fail("got error: " + e);
            }

            // we already deleted this
            PermitApiError exception = assertThrows(PermitApiError.class, () -> {
                permit.api.roles.delete("editor");
            });
            assertEquals(exception.getResponseCode(), 404);
        } catch (IOException | PermitApiError | PermitContextError e) {
            fail("got error: " + e);
        }
    }
}
