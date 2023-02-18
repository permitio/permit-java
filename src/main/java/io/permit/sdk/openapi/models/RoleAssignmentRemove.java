
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * RoleAssignmentRemove
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "role",
    "tenant",
    "user"
})
@Generated("jsonschema2pojo")
public class RoleAssignmentRemove {

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
     * User
     * <p>
     * the user the role will be unassigned from (accepts either the user id or the user key)
     * (Required)
     * 
     */
    @JsonProperty("user")
    @JsonPropertyDescription("the user the role will be unassigned from (accepts either the user id or the user key)")
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
     * @param tenant
     */
    public RoleAssignmentRemove(String role, String tenant, String user) {
        super();
        this.role = role;
        this.tenant = tenant;
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

    public RoleAssignmentRemove withUser(String user) {
        this.user = user;
        return this;
    }

}
