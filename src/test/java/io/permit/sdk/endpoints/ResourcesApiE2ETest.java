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
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


public class ResourcesApiE2ETest extends PermitE2ETestBase {
    private static final String DOCUMENT_KEY = "document";
    private static final String FOLDER_KEY = "folder";
    private static ResourceCreate documentData;
    private static ResourceCreate folderData;
    private static ResourceCreate duplicateData;

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

        // create folder resource
        folderData = ((
                new ResourceCreate("folder", "Folder", new HashMap<>())
        )
                .withUrn("prn:gdrive:folder")
                .withDescription("google drive folder")
        );

        duplicateData = ((
                new ResourceCreate("document", "Document2", actions)
        )
                .withUrn("prn:gdrive:document2")
                .withDescription("google drive document2")
        );
    }

    void cleanup() {
        Permit permit = new Permit(this.config);
        try {
            try {
                permit.api.resources.delete(DOCUMENT_KEY);
            } catch (PermitApiError e) {
                logger.info("SKIPPING delete of resource: " + DOCUMENT_KEY);
            }
            try {
                permit.api.resources.delete(FOLDER_KEY);
            } catch (PermitApiError e) {
                logger.info("SKIPPING delete of resource: " + FOLDER_KEY);
            }
        } catch (PermitContextError e) {
            fail("got error: " + e);
        } catch (IOException e) {
            fail("got error: " + e);
        }
    }

    @Test
    void testResourcesApi() {
        // init the client
        Permit permit = new Permit(this.config);
        Gson gson = new Gson();

        // users lifecycle
        try {
            logger.info("check original resource length");
            int originalLength = permit.api.resources.list().length;

            logger.info("create document with actions");
            ResourceRead document = permit.api.resources.create(documentData);
            assertNotNull(document);
            assertNotNull(document.id);
            assertEquals(document.key, documentData.key);
            assertEquals(document.name, documentData.name);
            assertEquals(document.description, documentData.description);
            assertEquals(document.urn, documentData.urn);
            assertEquals(document.actions.size(), 4);
            assertTrue(document.actions.containsKey("create"));
            assertTrue(document.actions.containsKey("read"));
            assertTrue(document.actions.containsKey("update"));
            assertTrue(document.actions.containsKey("delete"));

            logger.info("verify number of items increased by 1");
            ResourceRead[] resources = permit.api.resources.list();
            assertEquals(resources.length, originalLength + 1);

            logger.info("verify can find new resource in the new list");
            assertTrue(Arrays.stream(resources).map(r -> r.key).collect(Collectors.toList()).contains(documentData.key));

            logger.info("get non existing user -> 404");
            PermitApiError notFoundError = assertThrows(PermitApiError.class, () -> {
                permit.api.resources.get("group");
            });
            assertEquals(notFoundError.getMessage(), "Got error status code: 404");
            assertEquals(notFoundError.getResponseCode(), 404);
            LinkedTreeMap error = notFoundError.getErrorObject();
            assertEquals(error.get("error_code"), "NOT_FOUND");
            assertTrue(error.get("message").toString().startsWith("The requested data could not be found"));

            logger.info("create existing resource -> 409");
            PermitApiError duplicateError = assertThrows(PermitApiError.class, () -> {
                permit.api.resources.create(duplicateData);
            });
            assertEquals(duplicateError.getResponseCode(), 409);

            logger.info("create empty resource");
            ResourceRead folder = permit.api.resources.create(folderData);
            assertNotNull(folder);
            assertEquals(folder.key, folderData.key);
            assertEquals(folder.name, folderData.name);
            assertEquals(folder.description, folderData.description);
            assertNotNull(folder.actions);
            assertEquals(folder.actions.size(), 0);

            logger.info("verify number of items increased by 1");
            ResourceRead[] resources2 = permit.api.resources.list();
            assertEquals(resources2.length, originalLength + 2);

            logger.info("Update actions");
            permit.api.resources.update(
                    folder.key,
                    new ResourceUpdate()
                            .withDescription("wat")
                            .withActions(
                                    new HashMap<String, ActionBlockEditable>() {{
                                        put("pick", new ActionBlockEditable());
                                    }}
                            )
            );

            logger.info("get after update");
            ResourceRead updatedFolder = permit.api.resources.get(folderData.key);
            assertNotNull(updatedFolder);
            assertEquals(updatedFolder.key, folderData.key);
            assertEquals(updatedFolder.name, folderData.name);
            assertEquals(updatedFolder.description, "wat");
            assertNotNull(updatedFolder.actions);
            assertEquals(updatedFolder.actions.size(), 1);
            assertTrue(updatedFolder.actions.containsKey("pick"));

            logger.info("Delete objects...");
            try {
                permit.api.resources.delete(DOCUMENT_KEY);
            } catch (PermitApiError e) {
                fail("got error: " + e);
            }
            try {
                permit.api.resources.delete(FOLDER_KEY);
            } catch (PermitApiError e) {
                fail("got error: " + e);
            }

            logger.info("Verify that again we have the initial number of resources");
            ResourceRead[] resources3 = permit.api.resources.list();
            assertEquals(resources3.length, originalLength);

            logger.info("Verify deleted resource cannot be deleted again");
            PermitApiError exception = assertThrows(PermitApiError.class, () -> {
                permit.api.roles.delete(FOLDER_KEY);
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
