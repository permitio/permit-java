
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * UserRoleCreate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class UserRoleCreate {

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
     * (Required)
     * 
     */
    @SerializedName("tenant")
    @Expose
    public String tenant;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserRoleCreate() {
    }

    /**
     * 
     * @param role
     * @param tenant
     */
    public UserRoleCreate(String role, String tenant) {
        super();
        this.role = role;
        this.tenant = tenant;
    }

    public UserRoleCreate withRole(String role) {
        this.role = role;
        return this;
    }

    public UserRoleCreate withTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

}
