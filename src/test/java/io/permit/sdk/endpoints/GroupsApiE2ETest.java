package io.permit.sdk.endpoints;

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

public class GroupsApiE2ETest extends PermitE2ETestBase {
    private static final String TEAM_KEY = "teams";
    private static final String MARKETING_KEY = "marketing";
    private static final String GROUP_KEY = TEAM_KEY + ":" + MARKETING_KEY;
    private static final String DEFAULT_TENANT = "default";
    private static GroupCreate marketingData;

    private static ResourceCreate socialMediaData;
    private static ResourceInstanceCreate trainingVideoData;
    private static ResourceRoleCreate editorRole;

    private static UserCreate bobData;
    private static GroupAddRole groupRoleData;

    @BeforeAll
    static void setup() {
        // resource actions
        HashMap<String, ActionBlockEditable> actions = new HashMap<>();
        actions.put("create", new ActionBlockEditable());
        actions.put("read", new ActionBlockEditable().withName("Read").withDescription("Read Action"));
        actions.put("update", new ActionBlockEditable());
        actions.put("delete", new ActionBlockEditable());

        marketingData = new GroupCreate(MARKETING_KEY, DEFAULT_TENANT).withGroupResourceTypeKey(TEAM_KEY);

        socialMediaData = new ResourceCreate("social_media", "social media", actions);
        trainingVideoData = new ResourceInstanceCreate("training_video", socialMediaData.key).withTenant(DEFAULT_TENANT);
        editorRole = new ResourceRoleCreate("editor", "editor");

        bobData = new UserCreate("bob");
        groupRoleData = new GroupAddRole(editorRole.key, trainingVideoData.key, socialMediaData.key, DEFAULT_TENANT);
    }

    void cleanup() {
        Permit permit = new Permit(this.config);
        logger.info("cleaning");
        try {
            try {
                permit.api.resourceRoles.delete(socialMediaData.key, editorRole.key);
            } catch (PermitApiError e) {
                logger.info(String.format("SKIPPING delete of role %s on resource instance %s", editorRole.key, trainingVideoData.key));
            }
            try {
                permit.api.resourceInstances.delete(trainingVideoData.resource + ":" + trainingVideoData.key);
            } catch (PermitApiError e) {
                logger.info("SKIPPING delete of resource instance: " + trainingVideoData.key);
            }
            try {
                permit.api.users.delete(bobData.key);
            } catch (PermitApiError e) {
                logger.info("SKIPPING delete of user: " + bobData.key);
            }
            try {
                permit.api.groups.delete(GROUP_KEY);
            } catch (PermitApiError e) {
                logger.info("SKIPPING delete of group: " + GROUP_KEY);
            }
            try {
                permit.api.resourceRoles.delete(TEAM_KEY, "member");
            } catch (PermitApiError e) {
                logger.info("SKIPPING delete of resource role: " + TEAM_KEY + " member");
            }
            try {
                permit.api.resources.delete(socialMediaData.key);
            } catch (PermitApiError e) {
                logger.info("SKIPPING delete of resource: " + socialMediaData.key);
            }
            try {
                permit.api.resources.delete(TEAM_KEY);
            } catch (PermitApiError e) {
                logger.info("SKIPPING delete of resource: " + TEAM_KEY);
            }
            try {
                permit.api.resourceRelations.delete(socialMediaData.key, TEAM_KEY + "_group_" + editorRole.key);
            } catch (PermitApiError e) {
                logger.info("SKIPPING delete of resource relation: " + TEAM_KEY + "_group_" + editorRole.key);
            }
            try {
                permit.api.groups.removeRoleFromGroup(GROUP_KEY, groupRoleData);
            } catch (PermitApiError e) {
                logger.info("SKIPPING delete of resource relation: " + TEAM_KEY + "_group_" + editorRole.key);
            }
        } catch (PermitContextError e) {
            fail("clean up got error: " + e);
        } catch (IOException e) {
            fail("clean up got IOexception: " + e);
        }
    }

    @Test
    void testGroupsApi() {
        Permit permit = new Permit(this.config);
        try {
            logger.info("check original groups length");
            int originalLength = permit.api.groups.list().length;

            GroupRead marketingGroup = permit.api.groups.create(marketingData);
            assertNotNull(marketingGroup);
            assertNotNull(marketingGroup.groupResourceTypeKey);
            assertEquals(marketingGroup.groupResourceTypeKey, marketingData.groupResourceTypeKey);
            assertEquals(marketingGroup.groupTenant, marketingData.groupTenant);
            assertEquals(marketingGroup.groupInstanceKey, marketingData.groupInstanceKey);
            boolean foundMember = false;
            for (String role : marketingGroup.assignedRoles) {
                if (role.contains("member")) {
                    foundMember = true;
                    break;
                }
            }
            assertTrue(foundMember);

            logger.info("verify number of items increased by 1");
            GroupRead[] groups = permit.api.groups.list();
            assertEquals(groups.length, originalLength + 1);

            logger.info("verify can find new group in the new list");
            assertTrue(Arrays.stream(groups).map(r -> r.groupInstanceKey).collect(Collectors.toList()).toString().contains(marketingData.groupInstanceKey));

            logger.info("get non existing group -> 404");
            PermitApiError notFoundError = assertThrows(PermitApiError.class, () -> {
                permit.api.groups.get("group");
            });
            assertEquals("Got error status code: 404", notFoundError.getMessage());
            assertEquals(404, notFoundError.getResponseCode());
            LinkedTreeMap error = notFoundError.getErrorObject();
            assertEquals("NOT_FOUND", error.get("error_code"));
            assertTrue(error.get("message").toString().startsWith("The requested data could not be found"));

            logger.info("create existing group -> 409");
            PermitApiError duplicateError = assertThrows(PermitApiError.class, () -> {
                permit.api.groups.create(marketingData);
            });
            assertEquals(409, duplicateError.getResponseCode());


            // test assign user
            logger.info("Adding user to group");
            UserRead bob = permit.api.users.create(bobData);
            GroupRead afterAddingUser = permit.api.groups.assignUserToGroup(bobData.key, GROUP_KEY, DEFAULT_TENANT);
            assertTrue(afterAddingUser.users.contains(bob.id));
            assertEquals(1, afterAddingUser.users.size());

            logger.info("Adding user already in group");
            GroupRead afterAddingUserAgain = permit.api.groups.assignUserToGroup(bobData.key, GROUP_KEY, DEFAULT_TENANT);
            assertEquals(afterAddingUserAgain.users.size(), afterAddingUser.users.size());
            logger.info("Removing user from group");
            permit.api.groups.removeUserFromGroup(bobData.key, GROUP_KEY, DEFAULT_TENANT);
            GroupRead afterRemovingUser = permit.api.groups.get(GROUP_KEY);
            assertEquals(0, afterRemovingUser.users.size());

            // test assign role
            logger.info("Adding role to group");

            try {
                permit.api.resources.create(socialMediaData);
            } catch (PermitApiError e) {
                logger.info("SKIPPING creating resource: " + socialMediaData.key);
            }
            try {
                permit.api.resourceInstances.create(trainingVideoData);
            } catch (PermitApiError e) {
                logger.info("SKIPPING creating resource instances: " + trainingVideoData.key);
            }
            try {
                permit.api.resourceRoles.create(socialMediaData.key, editorRole);
            } catch (PermitApiError e) {
                logger.info("SKIPPING creating resource role: " + socialMediaData.key + editorRole.key);
            }

            GroupRead afterAddingRole = permit.api.groups.assignRoleToGroup(GROUP_KEY, groupRoleData);
            boolean foundRole = false;
            boolean foundResource = false;
            boolean foundResourceInstance = false;
            for (String role : afterAddingRole.assignedRoles) {
                if (role.contains(groupRoleData.role))
                    foundRole = true;
                if (role.contains(groupRoleData.resource))
                    foundResource = true;
                if (role.contains(groupRoleData.resourceInstance))
                    foundResourceInstance = true;
            }
            assertTrue(foundRole);
            assertTrue(foundResource);
            assertTrue(foundResourceInstance);
            assertEquals(2, afterAddingRole.assignedRoles.size());

            logger.info("Removing role from group");
            permit.api.groups.removeRoleFromGroup(GROUP_KEY, groupRoleData);
            GroupRead afterRemovingRole = permit.api.groups.get(GROUP_KEY);
            for (String role : afterRemovingRole.assignedRoles) {
                if (!role.contains(groupRoleData.role))
                    foundRole = false;
                if (!role.contains(groupRoleData.resource))
                    foundResource = false;
                if (!role.contains(groupRoleData.resourceInstance))
                    foundResourceInstance = false;
            }
            assertFalse(foundRole);
            assertFalse(foundResource);
            assertFalse(foundResourceInstance);
            assertEquals(1, marketingGroup.assignedRoles.size());

            // delete group
            logger.info("Delete group...");
            try {
                permit.api.groups.delete(GROUP_KEY);
            } catch (PermitApiError e) {
                fail("got error: " + e);
            }

            logger.info("Verify that again we have the initial number of groups");
            GroupRead[] groupsAfterDelete = permit.api.groups.list();
            assertEquals(groupsAfterDelete.length, originalLength);

            logger.info("Verify deleted groups cannot be deleted again");
            PermitApiError exception = assertThrows(PermitApiError.class, () -> {
                permit.api.groups.delete(GROUP_KEY);
            });
            assertEquals(404, exception.getResponseCode());

        } catch (IOException | PermitApiError | PermitContextError e) {
            fail("testGroupsApi got error: " + e);
        } finally {
            cleanup();
        }
    }
}
