
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * RoleAssignmentRemove
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class RoleAssignmentRemove {

    /**
     * Role
     * <p>
     * the role that will be unassigned (accepts either the role id or the role key)
     * (Required)
     * 
     */
    @SerializedName("role")
    @Expose
    public String role;
    /**
     * Tenant
     * <p>
     * the tenant the role is associated with (accepts either the tenant id or the tenant key)
     * 
     */
    @SerializedName("tenant")
    @Expose
    public String tenant;
    /**
     * Resource Instance
     * <p>
     * the resource instance the role is associated with (accepts either the resource instance id or key using this format resource_type:resource_instance)
     * 
     */
    @SerializedName("resource_instance")
    @Expose
    public String resourceInstance;
    /**
     * User
     * <p>
     * the user the role will be unassigned from (accepts either the user id or the user key)
     * (Required)
     * 
     */
    @SerializedName("user")
    @Expose
    public String user;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RoleAssignmentRemove() {
    }

    /**
     * 
     * @param role
     * @param user
     */
    public RoleAssignmentRemove(String role, String user) {
        super();
        this.role = role;
        this.user = user;
    }

    public RoleAssignmentRemove withRole(String role) {
        this.role = role;
        return this;
    }

    public RoleAssignmentRemove withTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public RoleAssignmentRemove withResourceInstance(String resourceInstance) {
        this.resourceInstance = resourceInstance;
        return this;
    }

    public RoleAssignmentRemove withUser(String user) {
        this.user = user;
        return this;
    }

}
