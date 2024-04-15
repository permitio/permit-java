
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * EnvironmentCopyScope
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class EnvironmentCopyScope {

    /**
     * Resources
     * <p>
     * Resources to copy
     * 
     */
    @SerializedName("resources")
    @Expose
    public io.permit.sdk.openapi.models.EnvironmentCopyScopeFilters resources;
    /**
     * Roles
     * <p>
     * Roles to copy
     * 
     */
    @SerializedName("roles")
    @Expose
    public io.permit.sdk.openapi.models.EnvironmentCopyScopeFilters roles;
    /**
     * User Sets
     * <p>
     * User sets to copy
     * 
     */
    @SerializedName("user_sets")
    @Expose
    public io.permit.sdk.openapi.models.EnvironmentCopyScopeFilters userSets;
    /**
     * Resource Sets
     * <p>
     * Resource sets to copy
     * 
     */
    @SerializedName("resource_sets")
    @Expose
    public io.permit.sdk.openapi.models.EnvironmentCopyScopeFilters resourceSets;

    public EnvironmentCopyScope withResources(io.permit.sdk.openapi.models.EnvironmentCopyScopeFilters resources) {
        this.resources = resources;
        return this;
    }

    public EnvironmentCopyScope withRoles(io.permit.sdk.openapi.models.EnvironmentCopyScopeFilters roles) {
        this.roles = roles;
        return this;
    }

    public EnvironmentCopyScope withUserSets(io.permit.sdk.openapi.models.EnvironmentCopyScopeFilters userSets) {
        this.userSets = userSets;
        return this;
    }

    public EnvironmentCopyScope withResourceSets(io.permit.sdk.openapi.models.EnvironmentCopyScopeFilters resourceSets) {
        this.resourceSets = resourceSets;
        return this;
    }

}
