package io.permit.sdk.api;

import io.permit.sdk.PermitConfig;
import io.permit.sdk.api.models.CreateOrUpdateResult;
import io.permit.sdk.openapi.models.*;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

interface IDeprecatedApis {
    UserRead getUser(String userKey) throws IOException, PermitApiError, PermitContextError;
    RoleRead getRole(String roleKey) throws IOException, PermitApiError, PermitContextError;
    TenantRead getTenant(String tenantKey) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead[] getAssignedRoles(@NotNull String userKey, @NotNull String tenantKey) throws IOException, PermitApiError, PermitContextError;
    RoleAssignmentRead[] getAssignedRolesInAllTenants(@NotNull String userKey) throws IOException, PermitApiError, PermitContextError;
    CreateOrUpdateResult<UserRead> syncUser(UserCreate userData) throws IOException, PermitApiError, PermitContextError;
    void deleteUser(String userKey) throws IOException, PermitContextError, PermitApiError;
    TenantRead createTenant(TenantCreate tenantData) throws IOException, PermitApiError, PermitContextError;
    TenantRead updateTenant(String tenantKey, TenantUpdate tenantData) throws IOException, PermitApiError, PermitContextError;
    void deleteTenant(String tenantKey) throws IOException, PermitContextError, PermitApiError;
    RoleAssignmentRead assignRole(String userKey, String roleKey, String tenantKey) throws IOException, PermitApiError, PermitContextError;
    void unassignRole(String userKey, String roleKey, String tenantKey) throws IOException, PermitContextError, PermitApiError;
}

public class ApiClient implements IDeprecatedApis {
    final static Logger logger = LoggerFactory.getLogger(ApiClient.class);
    private final OkHttpClient client;
    private final PermitConfig config;

    public final ProjectsApi projects;
    public final EnvironmentsApi environments;
    public final ResourcesApi resources;
    public final ResourceActionsApi resourceActions;
    public final ResourceActionGroupsApi resourceActionGroups;
    public final ResourceAttributesApi resourceAttributes;
    public final RolesApi roles;
    public final ConditionSetsApi conditionSets;
    public final TenantsApi tenants;
    public final UsersApi users;
    public final RoleAssignmentsApi roleAssignments;
    public final ElementsApi elements;

    public ApiClient(PermitConfig config) {
        this.config = config;
        this.client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(logger, config))
                .build();

        this.projects = new ProjectsApi(this.client, this.config);
        this.environments = new EnvironmentsApi(this.client, this.config);
        this.resources = new ResourcesApi(this.client, this.config);
        this.resourceActions = new ResourceActionsApi(this.client, this.config);
        this.resourceActionGroups = new ResourceActionGroupsApi(this.client, this.config);
        this.resourceAttributes = new ResourceAttributesApi(this.client, this.config);
        this.roles = new RolesApi(this.client, this.config);
        this.conditionSets = new ConditionSetsApi(this.client, this.config);
        this.tenants = new TenantsApi(this.client, this.config);
        this.users = new UsersApi(this.client, this.config);
        this.roleAssignments = new RoleAssignmentsApi(this.client, this.config);
        this.elements = new ElementsApi(this.client, this.config);
    }

    /**
     * Gets a user by its key
     *
     * @deprecated replaced with permit.api.users.get()
     * @see io.permit.sdk.api.UsersApi#get(String)
     */
    @Deprecated
    @Override
    public UserRead getUser(String userKey) throws IOException, PermitApiError, PermitContextError {
        return this.users.get(userKey);
    }

    /**
     * Gets a role by its key
     *
     * @deprecated replaced with permit.api.users.get()
     * @see io.permit.sdk.api.RolesApi#get(String)
     */
    @Deprecated
    @Override
    public RoleRead getRole(String roleKey) throws IOException, PermitApiError, PermitContextError {
        return this.roles.get(roleKey);
    }

    /**
     * Gets a role by its key
     *
     * @deprecated replaced with permit.api.users.get()
     * @see io.permit.sdk.api.TenantsApi#get(String)
     */
    @Deprecated
    @Override
    public TenantRead getTenant(String tenantKey) throws IOException, PermitApiError, PermitContextError {
        return this.tenants.get(tenantKey);
    }

    /**
     * Gets the roles assigned to a user in a specific tenant
     *
     * @deprecated replaced with permit.api.users.getAssignedRoles()
     * @see io.permit.sdk.api.UsersApi#getAssignedRoles(String, String, int, int)
     */
    @Deprecated
    @Override
    public RoleAssignmentRead[] getAssignedRoles(@NotNull String userKey, @NotNull String tenantKey) throws IOException, PermitApiError, PermitContextError {
        return this.users.getAssignedRoles(userKey, tenantKey, 1, 100);
    }

    /**
     * Gets the roles assigned to a user in all tenants
     *
     * @deprecated replaced with permit.api.users.getAssignedRoles()
     * @see io.permit.sdk.api.UsersApi#getAssignedRoles(String, String, int, int)
     */
    @Deprecated
    @Override
    public RoleAssignmentRead[] getAssignedRolesInAllTenants(@NotNull String userKey) throws IOException, PermitApiError, PermitContextError {
        return this.users.getAssignedRoles(userKey, null, 1, 100);
    }

    /**
     * Syncs a user to the permissions system, i.e: creates the user if it's not already created, or updates the user in place.
     * The user is identified by its key (a customer-side unique id that identifies the user).
     *
     * @deprecated replaced with permit.api.users.get()
     * @see io.permit.sdk.api.UsersApi#sync(UserCreate)
     */
    @Deprecated
    @Override
    public CreateOrUpdateResult<UserRead> syncUser(UserCreate userData) throws IOException, PermitApiError, PermitContextError {
        return this.users.sync(userData);
    }

    /**
     * Deletes a user from the permission system (this will delete the user from all tenants at once).
     *
     * @deprecated replaced with permit.api.users.delete()
     * @see io.permit.sdk.api.UsersApi#delete(String)
     */
    @Deprecated
    @Override
    public void deleteUser(String userKey) throws IOException, PermitContextError, PermitApiError {
        this.users.delete(userKey);
    }

    /**
     * Creates a new tenant. 
     * @throws PermitApiError if a tenant already exists with the given key.
     *
     * @deprecated replaced with permit.api.tenants.create()
     * @see io.permit.sdk.api.TenantsApi#create(TenantCreate) 
     */
    @Deprecated
    @Override
    public TenantRead createTenant(TenantCreate tenantData) throws IOException, PermitApiError, PermitContextError {
        return this.tenants.create(tenantData);
    }

    /**
     * Updates a tenant.
     *
     * @deprecated replaced with permit.api.tenants.update()
     * @see io.permit.sdk.api.TenantsApi#update(String, TenantUpdate)
     */
    @Deprecated
    @Override
    public TenantRead updateTenant(String tenantKey, TenantUpdate tenantData) throws IOException, PermitApiError, PermitContextError {
        return this.tenants.update(tenantKey, tenantData);
    }

    /**
     * Deletes a tenant from the system.
     * All roles assigned to users in that tenants will be unassigned as a result.
     *
     * @deprecated replaced with permit.api.tenants.delete()
     * @see io.permit.sdk.api.TenantsApi#delete(String)
     */
    @Deprecated
    @Override
    public void deleteTenant(String tenantKey) throws IOException, PermitContextError, PermitApiError {
        this.tenants.delete(tenantKey);
    }

    /**
     * assigns a role to user in tenant, if not already assigned.
     *
     * @deprecated replaced with permit.api.users.assignRole()
     * @see io.permit.sdk.api.UsersApi#assignRole(String, String, String)
     */
    @Deprecated
    @Override
    public RoleAssignmentRead assignRole(String userKey, String roleKey, String tenantKey) throws IOException, PermitApiError, PermitContextError {
        return this.users.assignRole(userKey, roleKey, tenantKey);
    }

    /**
     * unassigns a role to user in tenant, if assigned.
     *
     * @deprecated replaced with permit.api.users.unassignRole()
     * @see io.permit.sdk.api.UsersApi#unassignRole(String, String, String)
     */
    @Deprecated
    @Override
    public void unassignRole(String userKey, String roleKey, String tenantKey) throws IOException, PermitContextError, PermitApiError {
        this.users.unassignRole(userKey, roleKey, tenantKey);
    }
}
