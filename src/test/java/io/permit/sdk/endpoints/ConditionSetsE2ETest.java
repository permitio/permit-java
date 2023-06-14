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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


public class ConditionSetsE2ETest extends PermitE2ETestBase {
    private static final String DOCUMENT_KEY = "document";
    private static final String USER_RESOURCE_KEY = "__user";

    private static final String USERSET_KEY = "users_over_thirty";

    private static final String RESOURCESET_KEY = "private_docs";
    private static ResourceCreate documentData;
    private static ConditionSetCreate usersetData;
    private static ConditionSetCreate resourcesetData;
    private static ConditionSetRuleCreate ruleData;

    @BeforeAll
    static void setup() {
        // resource actions
        HashMap<String, ActionBlockEditable> actions = new HashMap<>();
        actions.put("create", new ActionBlockEditable());
        actions.put("read", new ActionBlockEditable().withName("Read").withDescription("Read Action"));
        actions.put("update", new ActionBlockEditable());
        actions.put("delete", new ActionBlockEditable());
        actions.put("sign", new ActionBlockEditable());

        // resource attributes
        HashMap<String, AttributeBlockEditable> attributes = new HashMap<>();
        AttributeBlockEditable privateAttr = new AttributeBlockEditable()
                .withType(AttributeType.BOOL)
                .withDescription("whether the document is private");
        attributes.put("private", privateAttr);

        // create document resource
        documentData = ((
            new ResourceCreate("document", "Document", actions)
        )
            .withUrn("prn:gdrive:document")
            .withDescription("google drive document")
            .withAttributes(attributes)
        );

        // condition sets data
        usersetData = ((
            new ConditionSetCreate(USERSET_KEY, "Users over 30")
        )
            .withType(ConditionSetType.USERSET)
            .withConditions(new HashMap<String, Object>() {{
                put("allOf", Collections.singletonList(
                    new HashMap<String, List<HashMap<String, HashMap<String, Integer>>>>() {{
                        put("allOf", Collections.singletonList(
                            new HashMap<String, HashMap<String, Integer>>() {{
                                put("user.age", new HashMap<String, Integer>() {{
                                    put("greater-than", 30);
                                }});
                            }}
                        ));
                    }}
                ));
            }})
        );

        resourcesetData = ((
            new ConditionSetCreate(RESOURCESET_KEY, "Private Docs")
        )
            .withType(ConditionSetType.RESOURCESET)
            .withConditions(new HashMap<String, Object>() {{
                put("allOf", Collections.singletonList(
                    new HashMap<String, List<HashMap<String, HashMap<String, Boolean>>>>() {{
                        put("allOf", Collections.singletonList(
                            new HashMap<String, HashMap<String, Boolean>>() {{
                                put("resource.private", new HashMap<String, Boolean>() {{
                                    put("equals", false);
                                }});
                            }}
                        ));
                    }}
                ));
            }})
        );

        ruleData = new ConditionSetRuleCreate(USERSET_KEY, DOCUMENT_KEY + ":sign", RESOURCESET_KEY);
    }

    void cleanup() {
        Permit permit = new Permit(this.config);
        try {
            try {
                permit.api.conditionSets.delete(USERSET_KEY);
            } catch (PermitApiError e) {
                logger.info("SKIPPING delete of user set: " + USERSET_KEY);
            }
            try {
                permit.api.conditionSets.delete(RESOURCESET_KEY);
            } catch (PermitApiError e) {
                logger.info("SKIPPING delete of resource set: " + RESOURCESET_KEY);
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
    void testResourcesApi() {
        // init the client
        Permit permit = new Permit(this.config);
        Gson gson = new Gson();

        // roles lifecycle
        try {
            logger.info("create document with actions");
            ResourceRead document = permit.api.resources.create(documentData);
            assertNotNull(document);
            assertNotNull(document.id);
            assertEquals(document.key, documentData.key);
            assertEquals(document.name, documentData.name);
            assertEquals(document.description, documentData.description);
            assertEquals(document.urn, documentData.urn);
            assertEquals(document.actions.size(), 5);
            assertEquals(document.attributes.size(), 1);
            assertTrue(document.actions.containsKey("create"));
            assertTrue(document.actions.containsKey("read"));
            assertTrue(document.actions.containsKey("update"));
            assertTrue(document.actions.containsKey("delete"));
            assertTrue(document.actions.containsKey("sign"));
            assertTrue(document.attributes.containsKey("private"));

            logger.info("verify can find new resource in the new list");
            ResourceRead[] resources = permit.api.resources.list();
            assertTrue(Arrays.stream(resources).map(r -> r.key).collect(Collectors.toList()).contains(documentData.key));

            logger.info("Create User.age attribute");
            try {
                permit.api.resourceAttributes.create(
                        USER_RESOURCE_KEY,
                        new ResourceAttributeCreate("age", AttributeType.NUMBER)
                );
            } catch (PermitApiError e) {
                if (e.getResponseCode() != 409) {
                    fail("got error while creating User.age attribute: " + e);
                }
            }

            logger.info("Create condition sets");
            ConditionSetRead userset = permit.api.conditionSets.create(usersetData);
            assertNotNull(userset);
            assertNotNull(userset.id);
            assertEquals(userset.key, usersetData.key);
            assertEquals(userset.name, usersetData.name);
            assertEquals(userset.description, usersetData.description);

            ConditionSetRead resourceset = permit.api.conditionSets.create(resourcesetData);
            assertNotNull(resourceset);
            assertNotNull(resourceset.id);
            assertEquals(resourceset.key, resourcesetData.key);
            assertEquals(resourceset.name, resourcesetData.name);
            assertEquals(resourceset.description, resourcesetData.description);

            logger.info("Getting condition set list");
            ConditionSetRead[] conditionSets = permit.api.conditionSets.list();
            assertTrue(Arrays.stream(conditionSets).map(c -> c.key).collect(Collectors.toList()).contains(USERSET_KEY));
            assertTrue(Arrays.stream(conditionSets).map(c -> c.key).collect(Collectors.toList()).contains(RESOURCESET_KEY));

            logger.info("Create condition set rules");
            ConditionSetRuleRead rule = permit.api.conditionSetRules.create(ruleData);
            assertNotNull(rule);
            assertNotNull(rule.id);
            assertEquals(rule.userSet, USERSET_KEY);
            assertEquals(rule.resourceSet, RESOURCESET_KEY);
            assertEquals(rule.permission, DOCUMENT_KEY + ":sign");

            logger.info("Getting condition set rules list");
            ConditionSetRuleRead[] rules = permit.api.conditionSetRules.list();
            assertEquals(rules.length, 1);

            logger.info("Delete objects...");
            try {
                permit.api.conditionSetRules.delete(
                    new ConditionSetRuleRemove(
                        rule.userSet,
                        rule.permission,
                        rule.resourceSet
                    )
                );
            } catch (PermitApiError e) {
                fail("got error: " + e);
            }
            try {
                permit.api.conditionSets.delete(USERSET_KEY);
            } catch (PermitApiError e) {
                fail("got error: " + e);
            }
            try {
                permit.api.conditionSets.delete(RESOURCESET_KEY);
            } catch (PermitApiError e) {
                fail("got error: " + e);
            }
            try {
                permit.api.resources.delete(DOCUMENT_KEY);
            } catch (PermitApiError e) {
                fail("got error: " + e);
            }

        } catch (IOException | PermitApiError | PermitContextError e) {
            fail("got error: " + e);
        }
        finally {
            cleanup();
        }
    }
}
