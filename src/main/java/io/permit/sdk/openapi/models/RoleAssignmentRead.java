
package io.permit.sdk.openapi.models;

import java.util.Date;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * RoleAssignmentRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class RoleAssignmentRead {

    /**
     * Id
     * <p>
     * Unique id of the role assignment
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public String id;
    /**
     * User
     * <p>
     * the user the role is assigned to
     * (Required)
     * 
     */
    @SerializedName("user")
    @Expose
    public String user;
    /**
     * Role
     * <p>
     * the role that is assigned
     * (Required)
     * 
     */
    @SerializedName("role")
    @Expose
    public String role;
    /**
     * Tenant
     * <p>
     * the tenant the role is associated with
     * (Required)
     * 
     */
    @SerializedName("tenant")
    @Expose
    public String tenant;
    /**
     * User Id
     * <p>
     * Unique id of the user
     * (Required)
     * 
     */
    @SerializedName("user_id")
    @Expose
    public String userId;
    /**
     * Role Id
     * <p>
     * Unique id of the role
     * (Required)
     * 
     */
    @SerializedName("role_id")
    @Expose
    public String roleId;
    /**
     * Tenant Id
     * <p>
     * Unique id of the tenant
     * (Required)
     * 
     */
    @SerializedName("tenant_id")
    @Expose
    public String tenantId;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the role assignment belongs to.
     * (Required)
     * 
     */
    @SerializedName("organization_id")
    @Expose
    public String organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the role assignment belongs to.
     * (Required)
     * 
     */
    @SerializedName("project_id")
    @Expose
    public String projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the role assignment belongs to.
     * (Required)
     * 
     */
    @SerializedName("environment_id")
    @Expose
    public String environmentId;
    /**
     * Created At
     * <p>
     * Date and time when the role assignment was created (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("created_at")
    @Expose
    public Date createdAt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RoleAssignmentRead() {
    }

    /**
     * 
     * @param organizationId
     * @param createdAt
     * @param role
     * @param environmentId
     * @param roleId
     * @param tenantId
     * @param id
     * @param user
     * @param userId
     * @param projectId
     * @param tenant
     */
    public RoleAssignmentRead(String id, String user, String role, String tenant, String userId, String roleId, String tenantId, String organizationId, String projectId, String environmentId, Date createdAt) {
        super();
        this.id = id;
        this.user = user;
        this.role = role;
        this.tenant = tenant;
        this.userId = userId;
        this.roleId = roleId;
        this.tenantId = tenantId;
        this.organizationId = organizationId;
        this.projectId = projectId;
        this.environmentId = environmentId;
        this.createdAt = createdAt;
    }

    public RoleAssignmentRead withId(String id) {
        this.id = id;
        return this;
    }

    public RoleAssignmentRead withUser(String user) {
        this.user = user;
        return this;
    }

    public RoleAssignmentRead withRole(String role) {
        this.role = role;
        return this;
    }

    public RoleAssignmentRead withTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public RoleAssignmentRead withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public RoleAssignmentRead withRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public RoleAssignmentRead withTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public RoleAssignmentRead withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public RoleAssignmentRead withProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public RoleAssignmentRead withEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public RoleAssignmentRead withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

}
