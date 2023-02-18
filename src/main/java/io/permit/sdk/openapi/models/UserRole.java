
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * UserRole
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "role",
    "tenant"
})
@Generated("jsonschema2pojo")
public class UserRole {

    /**
     * Role
     * <p>
     * the role that is assigned
     * (Required)
     * 
     */
    @JsonProperty("role")
    @JsonPropertyDescription("the role that is assigned")
    public String role;
    /**
     * Tenant
     * <p>
     * the tenant the role is associated with
     * (Required)
     * 
     */
    @JsonProperty("tenant")
    @JsonPropertyDescription("the tenant the role is associated with")
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
