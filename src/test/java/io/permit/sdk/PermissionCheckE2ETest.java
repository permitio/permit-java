package io.permit.sdk;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import io.permit.sdk.api.PermitApiError;
import io.permit.sdk.api.PermitContextError;
import io.permit.sdk.openapi.models.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this e2e test should run against a clean permit environment.
 * if the environment contains any objects the test will fail.
 * eventually we want to create an environment programmatically
 * and then extract the api key and start the test.
 */
public class PermissionCheckE2ETest extends PermitE2ETestBase {
    @Test
    void testPermitCheck() {
        // init the client
        Permit permit = new Permit(this.config);
        Gson gson = new Gson();

        try {
            // resource actions
            HashMap<String, ActionBlockEditable> actions = new HashMap<>();
            actions.put("create", null);
            actions.put("read", new ActionBlockEditable().withName("Read").withDescription("Read Action"));
            actions.put("update", null);
            actions.put("delete", null);

            // resource attribtues
            HashMap<String, AttributeBlockEditable> attributes = new HashMap<>();
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
                .withAttributes(attributes)
            );
            ResourceRead document = permit.api.resources.create(resourceInput);

            // verify create output
            assertNotNull(document);
            assertNotNull(document.id);
            assertEquals(document.key, "document");
            assertEquals(document.name, "Document");
            assertEquals(document.description, "google drive document");
            assertEquals(document.urn, "prn:gdrive:document");

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
                    new RoleCreate("admin","Admin").withDescription("an admin role")
            );
            assertNotNull(admin);
            assertEquals(admin.key, "admin");
            assertEquals(admin.name, "Admin");
            assertEquals(admin.description, "an admin role");

            // set up roles


            // assign permissions to roles

            // create a tenant

            // create a user

            // assign role to user in tenant

            // positive permission check

            // negative permission check

            // list all objects

            // cleanup
            permit.api.resources.delete("document");
            permit.api.roles.delete("admin");

            assertEquals(permit.api.resources.list().length, 0);
            assertEquals(permit.api.roles.list().length, 0);

        }
        catch (IOException | PermitApiError | PermitContextError e) {
            fail("got error: " + e);
        }
    }
}
