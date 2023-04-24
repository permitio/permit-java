
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Statistics
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class Statistics__1 {

    /**
     * Roles
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("roles")
    @Expose
    public Integer roles;
    /**
     * Users
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("users")
    @Expose
    public Integer users;
    /**
     * Policies
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("policies")
    @Expose
    public Integer policies;
    /**
     * Resources
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("resources")
    @Expose
    public Integer resources;
    /**
     * Tenants
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("tenants")
    @Expose
    public Integer tenants;
    /**
     * Has Decision Logs
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("has_decision_logs")
    @Expose
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
