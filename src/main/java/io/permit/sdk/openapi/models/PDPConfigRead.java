
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * PDPConfigRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class PDPConfigRead {

    /**
     * Id
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public String id;
    /**
     * Name
     * <p>
     * 
     * 
     */
    @SerializedName("name")
    @Expose
    public String name;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the pdp_config belongs to.
     * (Required)
     * 
     */
    @SerializedName("organization_id")
    @Expose
    public String organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the pdp_config belongs to.
     * (Required)
     * 
     */
    @SerializedName("project_id")
    @Expose
    public String projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the pdp_config belongs to.
     * (Required)
     * 
     */
    @SerializedName("environment_id")
    @Expose
    public String environmentId;
    /**
     * Client Secret
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("client_secret")
    @Expose
    public String clientSecret;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PDPConfigRead() {
    }

    /**
     * 
     * @param organizationId
     * @param environmentId
     * @param clientSecret
     * @param id
     * @param projectId
     */
    public PDPConfigRead(String id, String organizationId, String projectId, String environmentId, String clientSecret) {
        super();
        this.id = id;
        this.organizationId = organizationId;
        this.projectId = projectId;
        this.environmentId = environmentId;
        this.clientSecret = clientSecret;
    }

    public PDPConfigRead withId(String id) {
        this.id = id;
        return this;
    }

    public PDPConfigRead withName(String name) {
        this.name = name;
        return this;
    }

    public PDPConfigRead withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public PDPConfigRead withProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public PDPConfigRead withEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public PDPConfigRead withClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

}
