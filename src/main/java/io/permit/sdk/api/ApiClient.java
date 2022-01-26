package io.permit.sdk.api;

import io.permit.sdk.api.models.RoleModel;
import io.permit.sdk.api.models.TenantModel;
import io.permit.sdk.api.models.UserModel;
import io.permit.sdk.enforcement.User;

interface IReadApis {
    UserModel getUser(String userKey);
    RoleModel getRole(String roleKey);
    TenantModel getTenant(String tenantKey);
    RoleAssignmentList getAssignedRoles(String userKey, String tenantKey);
    RoleAssignmentList getAssignedRolesInAllTenants(String userKey);
}

interface IWriteApis {
    UserModel syncUser(User user);
    Boolean deleteUser(String userKey);
    TenantModel createTenant(TenantInput tenant);
    TenantModel updateTenant(TenantInput tenant);
    Boolean deleteTenant(String tenantKey);
    RoleAssignmentModel assignRole(String userKey, String roleKey, String tenantKey);
    Boolean unassignRole(String userKey, String roleKey, String tenantKey);
}

public class ApiClient implements IReadApis, IWriteApis {
    @Override
    public UserModel getUser(String userKey) {
        return null;
    }

    @Override
    public RoleModel getRole(String roleKey) {
        return null;
    }

    @Override
    public TenantModel getTenant(String tenantKey) {
        return null;
    }

    @Override
    public RoleAssignmentList getAssignedRoles(String userKey, String tenantKey) {
        return null;
    }

    @Override
    public RoleAssignmentList getAssignedRolesInAllTenants(String userKey) {
        return null;
    }

    @Override
    public UserModel syncUser(User user) {
        return null;
    }

    @Override
    public Boolean deleteUser(String userKey) {
        return null;
    }

    @Override
    public TenantModel createTenant(TenantInput tenant) {
        return null;
    }

    @Override
    public TenantModel updateTenant(TenantInput tenant) {
        return null;
    }

    @Override
    public Boolean deleteTenant(String tenantKey) {
        return null;
    }

    @Override
    public RoleAssignmentModel assignRole(String userKey, String roleKey, String tenantKey) {
        return null;
    }

    @Override
    public Boolean unassignRole(String userKey, String roleKey, String tenantKey) {
        return null;
    }
}
