
package io.permit.sdk.openapi.models;

import java.util.ArrayList;
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
public class UserInTenant {

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
    public List<String> roles = new ArrayList<String>();
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
    public UserInTenant() {
    }

    /**
     * 
     * @param roles
     * @param tenant
     * @param status
     */
    public UserInTenant(String tenant, List<String> roles, Object status) {
        super();
        this.tenant = tenant;
        this.roles = roles;
        this.status = status;
    }

    public UserInTenant withTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public UserInTenant withRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public UserInTenant withStatus(Object status) {
        this.status = status;
        return this;
    }

}
