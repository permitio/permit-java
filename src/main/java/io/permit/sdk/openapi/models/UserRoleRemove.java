
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * UserRoleRemove
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class UserRoleRemove {

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
     * No args constructor for use in serialization
     * 
     */
    public UserRoleRemove() {
    }

    /**
     * 
     * @param role
     */
    public UserRoleRemove(String role) {
        super();
        this.role = role;
    }

    public UserRoleRemove(String role, String tenant) {
        super();
        this.role = role;
        this.tenant = tenant;
    }

    public UserRoleRemove withRole(String role) {
        this.role = role;
        return this;
    }

    public UserRoleRemove withTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public UserRoleRemove withResourceInstance(String resourceInstance) {
        this.resourceInstance = resourceInstance;
        return this;
    }

}
