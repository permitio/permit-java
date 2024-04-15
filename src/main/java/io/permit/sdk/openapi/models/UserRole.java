
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * UserRole
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class UserRole {

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
     * No args constructor for use in serialization
     * 
     */
    public UserRole() {
    }

    /**
     * 
     * @param role
     * @param tenant
     */
    public UserRole(String role, String tenant) {
        super();
        this.role = role;
        this.tenant = tenant;
    }

    public UserRole withRole(String role) {
        this.role = role;
        return this;
    }

    public UserRole withTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

}
