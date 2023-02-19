
package io.permit.sdk.openapi.models;

import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * UserInTenant
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class UserInTenant__1 {

    /**
     * Tenant
     * <p>
     * The tenant key which the user is associated with
     * (Required)
     * 
     */
    @SerializedName("tenant")
    @Expose
    public String tenant;
    /**
     * Roles
     * <p>
     * List of roles assigned to the user in that tenant
     * (Required)
     * 
     */
    @SerializedName("roles")
    @Expose
    public List<String> roles;
    /**
     * Whether the user has signed in or not
     * (Required)
     * 
     */
    @SerializedName("status")
    @Expose
    public Object status;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserInTenant__1() {
    }

    /**
     * 
     * @param roles
     * @param tenant
     * @param status
     */
    public UserInTenant__1(String tenant, List<String> roles, Object status) {
        super();
        this.tenant = tenant;
        this.roles = roles;
        this.status = status;
    }

    public UserInTenant__1 withTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public UserInTenant__1 withRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public UserInTenant__1 withStatus(Object status) {
        this.status = status;
        return this;
    }

}
