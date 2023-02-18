
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * UserRoleRemove
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
public class UserRoleRemove {

    /**
     * Role
     * <p>
     * the role that will be unassigned (accepts either the role id or the role key)
     * (Required)
     * 
     */
    @JsonProperty("role")
    @JsonPropertyDescription("the role that will be unassigned (accepts either the role id or the role key)")
    public String role;
    /**
     * Tenant
     * <p>
     * the tenant the role is associated with (accepts either the tenant id or the tenant key)
     * (Required)
     * 
     */
    @JsonProperty("tenant")
    @JsonPropertyDescription("the tenant the role is associated with (accepts either the tenant id or the tenant key)")
    public String tenant;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UserRoleRemove() {
    }

    /**
     * 
     * @param role
     * @param tenant
     */
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

}
