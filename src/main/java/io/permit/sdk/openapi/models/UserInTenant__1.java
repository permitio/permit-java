
package io.permit.sdk.openapi.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * UserInTenant
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "tenant",
    "roles",
    "status"
})
@Generated("jsonschema2pojo")
public class UserInTenant__1 {

    /**
     * Tenant
     * <p>
     * The tenant key which the user is associated with
     * (Required)
     * 
     */
    @JsonProperty("tenant")
    @JsonPropertyDescription("The tenant key which the user is associated with")
    public String tenant;
    /**
     * Roles
     * <p>
     * List of roles assigned to the user in that tenant
     * (Required)
     * 
     */
    @JsonProperty("roles")
    @JsonPropertyDescription("List of roles assigned to the user in that tenant")
    public List<String> roles = new ArrayList<String>();
    /**
     * Whether the user has signed in or not
     * (Required)
     * 
     */
    @JsonProperty("status")
    @JsonPropertyDescription("Whether the user has signed in or not")
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
