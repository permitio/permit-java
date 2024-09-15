package io.permit.sdk.e2e;

import io.permit.sdk.Permit;
import io.permit.sdk.PermitE2ETestBase;
import io.permit.sdk.api.PermitApiError;
import io.permit.sdk.api.PermitContextError;
import io.permit.sdk.enforcement.Resource;
import io.permit.sdk.enforcement.User;
import io.permit.sdk.openapi.models.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@FunctionalInterface
interface CheckedFunction {
    void call() throws IOException, PermitApiError, PermitContextError;
}

class ShortDerivation {
    String sourceRole;
    String derivedRole;
    String viaRelation;

    public ShortDerivation(String sourceRole, String derivedRole, String viaRelation) {
        this.sourceRole = sourceRole;
        this.derivedRole = derivedRole;
        this.viaRelation = viaRelation;
    }

    public String sourceResourceKey() {
        return this.sourceRole.split("#")[0];
    }

    public String sourceRoleKey() {
        return this.sourceRole.split("#")[1];
    }

    public String objectKey() {
        return this.derivedRole.split("#")[0];
    }

    public String derivedRoleKey() {
        return this.derivedRole.split("#")[1];
    }
}

class CheckAssertion {
    public String user;
    public String action;
    public Resource resource;
    public boolean expectedDecision;
    public CheckAssertion(String user, String action, Resource resource, boolean expectedDecision) {
        this.user = user;
        this.action = action;
        this.resource = resource;
        this.expectedDecision = expectedDecision;
    }
}

class PermissionsAssertions {
    List<RoleAssignmentCreate> assignments;
    List<CheckAssertion> assertions;
}

public class RebacE2ETest extends PermitE2ETestBase {
    final int WAIT_PERIOD = 30; // in seconds

    // Role keys
    final String VIEWER = "viewer";
    final String COMMENTER = "commenter";
    final String EDITOR = "editor";
    final String ADMIN = "admin";
    final String MEMBER = "member";


    final ResourceCreate ACCOUNT = new ResourceCreate()
            .withName("Account")
            .withKey("account")
            .withUrn("prn:gdrive:account")
            .withDescription("a google drive account")
            .withActions(new HashMap<String, ActionBlockEditable>() {{
                put("create", new ActionBlockEditable());
                put("invite-user", new ActionBlockEditable());
                put("delete", new ActionBlockEditable());
                put("view-members", new ActionBlockEditable());
                put("create-folder", new ActionBlockEditable());
                put("create-document", new ActionBlockEditable());
            }})
            .withRoles(new HashMap<String, RoleBlockEditable>() {{
                put(ADMIN, new RoleBlockEditable("Admin").withPermissions(Arrays.asList("create", "invite-user", "delete", "view-members", "create-folder", "create-document")));
                put(MEMBER, new RoleBlockEditable("Admin").withPermissions(Arrays.asList("view-members", "create-folder", "create-document")));
            }});
    final ResourceCreate FOLDER = new ResourceCreate()
            .withName("Folder")
            .withKey("folder")
            .withUrn("prn:gdrive:folder")
            .withDescription("a folder")
            .withActions(new HashMap<String, ActionBlockEditable>() {{
                put("read", new ActionBlockEditable());
                put("rename", new ActionBlockEditable());
                put("delete", new ActionBlockEditable());
                put("create-document", new ActionBlockEditable());
            }})
            .withRelations(new HashMap<String, String>() {{
                put("account", ACCOUNT.key);
            }});

    final ResourceCreate DOCUMENT = new ResourceCreate()
            .withName("Document")
            .withKey("document")
            .withActions(new HashMap<String, ActionBlockEditable>() {{
                put("comment", new ActionBlockEditable());
                put("read", new ActionBlockEditable());
                put("update", new ActionBlockEditable());
                put("delete", new ActionBlockEditable());
            }});

    final Map<String, List<ResourceRoleCreate>> RESOURCE_ROLES = new HashMap<String, List<ResourceRoleCreate>>() {{
        put(FOLDER.key, Arrays.asList(
                new ResourceRoleCreate()
                        .withName("Viewer")
                        .withKey(VIEWER)
                        .withPermissions(Collections.singletonList("read"))
                        .withGrantedTo(new DerivedRoleBlockEdit()
                                .withUsersWithRole(Collections.singletonList(
                                        new DerivedRoleRuleCreate()
                                                .withRole(MEMBER)
                                                .withOnResource(ACCOUNT.key)
                                                .withLinkedByRelation("account")))),
                new ResourceRoleCreate()
                        .withName("Commenter")
                        .withKey(COMMENTER)
                        .withPermissions(Collections.singletonList("read")),
                new ResourceRoleCreate()
                        .withName("Editor")
                        .withKey(EDITOR)
                        .withPermissions(Arrays.asList("read", "rename", "delete", "create-document"))
                        .withGrantedTo(new DerivedRoleBlockEdit()
                                .withUsersWithRole(Collections.singletonList(
                                        new DerivedRoleRuleCreate()
                                                .withRole(ADMIN)
                                                .withOnResource(ACCOUNT.key)
                                                .withLinkedByRelation("account")
                                )))
        ));

        put(DOCUMENT.key, Arrays.asList(
                new ResourceRoleCreate()
                        .withName("Viewer")
                        .withKey(VIEWER)
                        .withPermissions(Collections.singletonList("read")),
                new ResourceRoleCreate()
                        .withName("Commenter")
                        .withKey(COMMENTER)
                        .withPermissions(Arrays.asList("read", "comment")),
                new ResourceRoleCreate()
                        .withName("Editor")
                        .withKey(EDITOR)
                        .withPermissions(Arrays.asList("read", "comment", "update", "delete"))
        ));
    }};
    final Map<String, List<RelationCreate>> RESOURCE_RELATIONS = new HashMap<String, List<RelationCreate>>() {{
        put(DOCUMENT.key, Collections.singletonList(
                new RelationCreate()
                        .withKey("parent")
                        .withName("Parent")
                        .withSubjectResource(FOLDER.key))
        );
    }};
    final List<ResourceCreate> CREATED_RESOURCES = Arrays.asList(ACCOUNT, FOLDER, DOCUMENT);

    final List<ShortDerivation> ROLE_DERIVATIONS = Arrays.asList(
            new ShortDerivation("folder#viewer", "document#viewer", "parent"),
            new ShortDerivation("folder#commenter", "document#commenter", "parent"),
            new ShortDerivation("folder#editor", "document#editor", "parent")
    );

    final List<UserRoleCreate> roleAssignments = Arrays.asList(
        new UserRoleCreate("editor", "default")
    );

    final UserCreate USER_PERMIT = new UserCreate()
            .withKey("asaf@permit.io")
            .withEmail("asaf@permit.io")
            .withFirstName("Asaf")
            .withLastName("Cohen")
            .withRoleAssignments(roleAssignments)
            .withAttributes(new HashMap<String, Object>() {{
                put("age", 35);
            }});
            
    final UserCreate USER_CC = new UserCreate()
            .withKey("auth0|john")
            .withEmail("john@cocacola.com")
            .withFirstName("John")
            .withLastName("Doe")
            .withAttributes(new HashMap<String, Object>() {{
                put("age", 27);
            }});
    final TenantCreate TENANT_PERMIT = new TenantCreate().withName("Permit.io").withKey("permit");
    final TenantCreate TENANT_CC = new TenantCreate().withName("Coca Cola").withKey("cocacola");
    final List<UserCreate> CREATED_USERS = Arrays.asList(USER_PERMIT, USER_CC);
    final List<TenantCreate> CREATED_TENANT = Arrays.asList(TENANT_PERMIT, TENANT_CC);
    final List<RelationshipTupleCreate> RELATIONSHIPS = Arrays.asList(
            new RelationshipTupleCreate().withSubject("folder:finance").withRelation("parent").withObject("document:budget23").withTenant(TENANT_PERMIT.key),
            new RelationshipTupleCreate().withSubject("folder:finance").withRelation("parent").withObject("document:june-expenses").withTenant(TENANT_PERMIT.key),

            new RelationshipTupleCreate().withSubject("folder:rnd").withRelation("parent").withObject("document:architecture").withTenant(TENANT_PERMIT.key),
            new RelationshipTupleCreate().withSubject("folder:rnd").withRelation("parent").withObject("document:opal").withTenant(TENANT_PERMIT.key),

            new RelationshipTupleCreate().withSubject("account:permitio").withRelation("account").withObject("folder:finance").withTenant(TENANT_PERMIT.key),
            new RelationshipTupleCreate().withSubject("account:permitio").withRelation("account").withObject("folder:rnd").withTenant(TENANT_PERMIT.key),

            new RelationshipTupleCreate().withSubject("folder:recipes").withRelation("parent").withObject("document:secret-recipe").withTenant(TENANT_CC.key),
            new RelationshipTupleCreate().withSubject("account:cocacola").withRelation("account").withObject("folder:recipes").withTenant(TENANT_CC.key)
    );

    final List<PermissionsAssertions> ASSIGNMENTS_AND_ASSERTIONS = Arrays.asList(
            new PermissionsAssertions() {{
                assignments = Arrays.asList(
                        new RoleAssignmentCreate()
                                .withUser(USER_PERMIT.key)
                                .withRole(VIEWER)
                                .withResourceInstance("document:architecture")
                                .withTenant(TENANT_PERMIT.key)
                );
                assertions = Arrays.asList(
                        // direct access allowed
                        new CheckAssertion(
                                USER_PERMIT.key,
                                "read",
                                pdpResourceObject(DOCUMENT.key, "architecture", TENANT_PERMIT.key),
                                true
                        ),
                        // higher permissions not allowed
                        new CheckAssertion(
                                USER_PERMIT.key,
                                "comment",
                                pdpResourceObject(DOCUMENT.key, "architecture", TENANT_PERMIT.key),
                                false
                        ),
                        // other instances not allowed
                        new CheckAssertion(
                                USER_PERMIT.key,
                                "comment",
                                pdpResourceObject(DOCUMENT.key, "opal", TENANT_PERMIT.key),
                                false
                        )
                );
            }},
            // access from a higher level
            new PermissionsAssertions() {{
                // direct access allowed
                assignments = Collections.singletonList(
                        new RoleAssignmentCreate()
                                .withUser(USER_PERMIT.key)
                                .withRole(COMMENTER)
                                .withResourceInstance("folder:rnd")
                                .withTenant(TENANT_PERMIT.key)
                );
                // access to child resources allowed
                assertions = Arrays.asList(
                        new CheckAssertion(
                                USER_PERMIT.key,
                                "read",
                                pdpResourceObject(FOLDER.key, "rnd", TENANT_PERMIT.key),
                                true
                        ),
                        new CheckAssertion(
                                USER_PERMIT.key,
                                "read",
                                pdpResourceObject(DOCUMENT.key, "architecture", TENANT_PERMIT.key),
                                true
                        ),
                        new CheckAssertion(
                                USER_PERMIT.key,
                                "comment",
                                pdpResourceObject(DOCUMENT.key, "architecture", TENANT_PERMIT.key),
                                true
                        ),
                        new CheckAssertion(
                                USER_PERMIT.key,
                                "read",
                                pdpResourceObject(DOCUMENT.key, "opal", TENANT_PERMIT.key),
                                true
                        ),
                        new CheckAssertion(
                                USER_PERMIT.key,
                                "comment",
                                pdpResourceObject(DOCUMENT.key, "opal", TENANT_PERMIT.key),
                                true
                        ),
                        // higher permissions not allowed
                        new CheckAssertion(
                                USER_PERMIT.key,
                                "update",
                                pdpResourceObject(DOCUMENT.key, "architecture", TENANT_PERMIT.key),
                                false
                        ),
                        // access to other resources not allowed
                        new CheckAssertion(
                                USER_PERMIT.key,
                                "read",
                                pdpResourceObject(DOCUMENT.key, "budget23", TENANT_PERMIT.key),
                                false
                        )
                );
            }},
            new PermissionsAssertions() {{
                assignments = Arrays.asList(
                        new RoleAssignmentCreate()
                                .withUser(USER_PERMIT.key)
                                .withRole(ADMIN)
                                .withResourceInstance("account:permitio")
                                .withTenant(TENANT_PERMIT.key),
                        new RoleAssignmentCreate()
                                .withUser(USER_CC.key)
                                .withRole(MEMBER)
                                .withResourceInstance("account:cocacola")
                                .withTenant(TENANT_CC.key)
                );
                assertions = Arrays.asList(
                        // direct access allowed
                        new CheckAssertion(
                                USER_PERMIT.key,
                                "invite-user",
                                pdpResourceObject(ACCOUNT.key, "permitio", TENANT_PERMIT.key),
                                true
                        ),
                        // access to other tenants not allowed
                        new CheckAssertion(
                                USER_PERMIT.key,
                                "read",
                                pdpResourceObject(DOCUMENT.key, "secret-recipe", TENANT_CC.key),
                                false
                        ),
                        // but access is allowed to user with lower permissions in the right tenant
                        new CheckAssertion(
                                USER_CC.key,
                                "read",
                                pdpResourceObject(DOCUMENT.key, "secret-recipe", TENANT_CC.key),
                                true
                        )
                );
                assertions = Stream.concat(assertions.stream(), buildChildAssertionMatrix().stream()).collect(Collectors.toList());
            }}
    );

    Permit permit = new Permit(this.config);

    List<CheckAssertion> buildChildAssertionMatrix() {
        ArrayList<CheckAssertion> result = new ArrayList<>();

        String[] instances = {"architecture", "opal", "budget23", "june-expenses"};
        String[] actions = {"read", "comment", "update", "delete"};

        for (String instance : instances) {
            for (String action : actions) {
                result.add(new CheckAssertion(
                                USER_PERMIT.key,
                                action,
                                pdpResourceObject(DOCUMENT.key, instance, TENANT_PERMIT.key),
                                true
                        )
                );
            }
        }

        return result;
    }

    @Test
    void testREBAC() throws Exception {
        try {
            this.cleanup();

            this.setup();
            this.runAssertions();
        } catch (Exception ex) {
            logger.error("Error during e2e test", ex);
            throw ex;
        } finally {
            this.cleanup();
        }
    }

    void runAssertions() throws Exception {
        for (PermissionsAssertions step : ASSIGNMENTS_AND_ASSERTIONS) {
            for (RoleAssignmentCreate assignment : step.assignments) {
                logger.info("Creating role assignment ({}, {}, {}) in tenant {}", assignment.user, assignment.role, assignment.resourceInstance, assignment.tenant);

                RoleAssignmentRead created = permit.api.roleAssignments.assign(assignment);
                assertEquals(assignment.user, created.user);
                assertEquals(assignment.role, created.role);
                assertEquals(assignment.resourceInstance, created.resourceInstance);
                assertEquals(assignment.tenant, created.tenant);
            }

            logger.info("Waiting for {} seconds", WAIT_PERIOD);
            Thread.sleep(WAIT_PERIOD * 1000);

            for (CheckAssertion assertion : step.assertions) {
                logger.info("Checking: permit.check({}, {}, {}) == {}",
                        assertion.user,
                        assertion.action,
                        assertion.resource,
                        assertion.expectedDecision
                );

                // Main assertion
                User user = new User.Builder(assertion.user).build();
                boolean decision = permit.check(user, assertion.action, assertion.resource);
                assertEquals(assertion.expectedDecision, decision);
            }
        }
    }

    void setup() throws Exception {
        logger.info("Initial setup...");

        for (ResourceCreate resource : CREATED_RESOURCES) {
            logger.info("Creating resource: {}", resource.key);

            ResourceRead created = permit.api.resources.create(resource);
            assertNotNull(created);
            assertEquals(resource.key, created.key);
            assertEquals(resource.name, created.name);
            assertEquals(resource.description, created.description);
            assertNotNull(resource.actions);
            assertEquals(resource.actions.size(), created.actions.size());
            assertEqualKeys(resource.actions, created.actions);
        }

        for (Map.Entry<String, List<ResourceRoleCreate>> entry : RESOURCE_ROLES.entrySet()) {
            String resourceKey = entry.getKey();
            List<ResourceRoleCreate> roles = entry.getValue();

            for (ResourceRoleCreate role : roles) {
                logger.info("Creating resource role {}#{}", resourceKey, role.key);

                ResourceRoleRead created = permit.api.resourceRoles.create(resourceKey, role);
                assertNotNull(created);
                assertEquals(role.key, created.key);
                assertEquals(role.name, created.name);
                assertEquals(role.description, created.description);
                assertNotNull(role.permissions);
                assertEquals(role.permissions.size(), created.permissions.size());
            }
        }

        for (Map.Entry<String, List<RelationCreate>> entry : RESOURCE_RELATIONS.entrySet()) {
            String resourceKey = entry.getKey();
            List<RelationCreate> relations = entry.getValue();

            for (RelationCreate relation : relations) {
                logger.info("Creating resource relation {}->{}", resourceKey, relation.key);

                RelationRead created = permit.api.resourceRelations.create(resourceKey, relation);
                assertNotNull(created);
                assertEquals(relation.key, created.key);
                assertEquals(relation.name, created.name);
                assertEquals(relation.subjectResource, created.subjectResource);
                assertEquals(resourceKey, created.objectResource);
            }
        }

        for (ShortDerivation derivation : ROLE_DERIVATIONS) {
            logger.info("Creating derivation {} -> {} (via {})", derivation.sourceRole, derivation.derivedRole, derivation.viaRelation);

            DerivedRoleRuleRead created = permit.api.resourceRoles.createRoleDerivation(
                    derivation.objectKey(),
                    derivation.derivedRoleKey(),
                    new DerivedRoleRuleCreate()
                            .withRole(derivation.sourceRoleKey())
                            .withOnResource(derivation.sourceResourceKey())
                            .withLinkedByRelation(derivation.viaRelation));

            assertNotNull(created);
            assertEquals(derivation.sourceRoleKey(), created.role);
            assertEquals(derivation.sourceResourceKey(), created.onResource);
            assertEquals(derivation.viaRelation, created.linkedByRelation);
        }

        for (TenantCreate tenant : CREATED_TENANT) {
            logger.info("Creating tenant {}", tenant.key);

            TenantRead created = permit.api.tenants.create(tenant);
            assertNotNull(created);
            assertEquals(tenant.key, created.key);
            assertEquals(tenant.name, created.name);
            assertNull(tenant.description);
        }

        for (UserCreate user : CREATED_USERS) {
            logger.info("Creating user {}", user.key);

            UserRead created = permit.api.users.create(user);
            assertNotNull(created);
            assertEquals(created.key, user.key);
            assertEquals(created.email, user.email);
            assertEquals(created.firstName, user.firstName);
            assertEquals(created.lastName, user.lastName);
            assertEqualKeys(created.attributes, user.attributes);
        }

        for (RelationshipTupleCreate relationship : RELATIONSHIPS) {
            logger.info("Creating relationship tuple ({}, {}, {}, {})", relationship.subject, relationship.relation, relationship.object, relationship.tenant);

            RelationshipTupleRead created = permit.api.relationshipTuples.create(relationship);
            assertNotNull(created);
            assertEquals(relationship.subject, created.subject);
            assertEquals(relationship.relation, created.relation);
            assertEquals(relationship.tenant, created.tenant);
            assertEquals(relationship.object, created.object);
        }
    }

    void cleanup() throws Exception {
        for (UserCreate user : CREATED_USERS) {
            withNotFoundIgnored(() -> permit.api.users.delete(user.key));
        }

        for (TenantCreate tenant : CREATED_TENANT) {
            withNotFoundIgnored(() -> permit.api.tenants.delete(tenant.key));
        }

        for (RelationshipTupleCreate tuple : RELATIONSHIPS) {
            withNotFoundIgnored(() -> permit.api.relationshipTuples.delete(new RelationshipTupleDelete()
                    .withSubject(tuple.subject)
                    .withObject(tuple.object)
                    .withRelation(tuple.relation)
            ));
        }

        for (PermissionsAssertions assertion : ASSIGNMENTS_AND_ASSERTIONS) {
            for (RoleAssignmentCreate assignment : assertion.assignments) {
                withNotFoundIgnored(() -> {
                    permit.api.roleAssignments.unassign(
                            new RoleAssignmentRemove()
                                    .withUser(assignment.user)
                                    .withTenant(assignment.tenant)
                                    .withRole(assignment.role)
                                    .withResourceInstance(assignment.resourceInstance)
                    );
                });
            }
        }

        for (ResourceCreate resource : CREATED_RESOURCES) {
            withNotFoundIgnored(() -> permit.api.resources.delete(resource.key));
        }

        logger.debug("Cleaned up");
    }

    void withNotFoundIgnored(CheckedFunction callable) throws Exception {
        try {
            callable.call();
        } catch (PermitApiError ex) {
            if (ex.getResponseCode() != 404) {
                throw ex;
            }
        }
    }

    Resource pdpResourceObject(String type, String key, String tenant) {
        return new Resource.Builder(String.format("%s:%s", type, key)).withTenant(tenant).build();
    }

    void assertEqualKeys(Map<String, ?> expected, Map<String, ?> actual) {
        assertEquals(expected.keySet(), actual.keySet());
    }
}