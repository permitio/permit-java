
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * RoleAssignmentCreate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class RoleAssignmentCreate {

    /**
     * Role
     * <p>
     * the role that will be assigned (accepts either the role id or the role key)
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
     * the user the role will be assigned to (accepts either the user id or the user key)
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
    public RoleAssignmentCreate() {
    }

    /**
     * 
     * @param role
     * @param user
     */
    public RoleAssignmentCreate(String role, String user) {
        super();
        this.role = role;
        this.user = user;
    }

    public RoleAssignmentCreate withRole(String role) {
        this.role = role;
        return this;
    }

    public RoleAssignmentCreate withTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public RoleAssignmentCreate withResourceInstance(String resourceInstance) {
        this.resourceInstance = resourceInstance;
        return this;
    }

    public RoleAssignmentCreate withUser(String user) {
        this.user = user;
        return this;
    }

}
