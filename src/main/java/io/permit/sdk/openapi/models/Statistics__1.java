
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Statistics
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "roles",
    "users",
    "policies",
    "resources",
    "tenants",
    "has_decision_logs"
})
@Generated("jsonschema2pojo")
public class Statistics__1 {

    /**
     * Roles
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("roles")
    public Integer roles;
    /**
     * Users
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("users")
    public Integer users;
    /**
     * Policies
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("policies")
    public Integer policies;
    /**
     * Resources
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("resources")
    public Integer resources;
    /**
     * Tenants
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("tenants")
    public Integer tenants;
    /**
     * Has Decision Logs
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("has_decision_logs")
    public Boolean hasDecisionLogs;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Statistics__1() {
    }

    /**
     * 
     * @param tenants
     * @param roles
     * @param policies
     * @param resources
     * @param hasDecisionLogs
     * @param users
     */
    public Statistics__1(Integer roles, Integer users, Integer policies, Integer resources, Integer tenants, Boolean hasDecisionLogs) {
        super();
        this.roles = roles;
        this.users = users;
        this.policies = policies;
        this.resources = resources;
        this.tenants = tenants;
        this.hasDecisionLogs = hasDecisionLogs;
    }

    public Statistics__1 withRoles(Integer roles) {
        this.roles = roles;
        return this;
    }

    public Statistics__1 withUsers(Integer users) {
        this.users = users;
        return this;
    }

    public Statistics__1 withPolicies(Integer policies) {
        this.policies = policies;
        return this;
    }

    public Statistics__1 withResources(Integer resources) {
        this.resources = resources;
        return this;
    }

    public Statistics__1 withTenants(Integer tenants) {
        this.tenants = tenants;
        return this;
    }

    public Statistics__1 withHasDecisionLogs(Boolean hasDecisionLogs) {
        this.hasDecisionLogs = hasDecisionLogs;
        return this;
    }

}
