
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * APIKeyScopeRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class APIKeyScopeRead {

    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the api_key belongs to.
     * (Required)
     * 
     */
    @SerializedName("organization_id")
    @Expose
    public String organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the api_key belongs to.
     * 
     */
    @SerializedName("project_id")
    @Expose
    public String projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the api_key belongs to.
     * 
     */
    @SerializedName("environment_id")
    @Expose
    public String environmentId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public APIKeyScopeRead() {
    }

    /**
     * 
     * @param organizationId
     */
    public APIKeyScopeRead(String organizationId) {
        super();
        this.organizationId = organizationId;
    }

    public APIKeyScopeRead withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public APIKeyScopeRead withProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public APIKeyScopeRead withEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
        return this;
    }

}
