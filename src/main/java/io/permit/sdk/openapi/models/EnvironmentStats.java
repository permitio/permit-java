
package io.permit.sdk.openapi.models;

import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * EnvironmentStats
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class EnvironmentStats {

    /**
     * Key
     * <p>
     * A URL-friendly name of the environment (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the environment.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;
    /**
     * Id
     * <p>
     * Unique id of the environment
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public String id;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the environment belongs to.
     * (Required)
     * 
     */
    @SerializedName("organization_id")
    @Expose
    public String organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the environment belongs to.
     * (Required)
     * 
     */
    @SerializedName("project_id")
    @Expose
    public String projectId;
    /**
     * Created At
     * <p>
     * Date and time when the environment was created (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the environment was last updated/modified (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("updated_at")
    @Expose
    public Date updatedAt;
    /**
     * Name
     * <p>
     * The name of the environment
     * (Required)
     * 
     */
    @SerializedName("name")
    @Expose
    public String name;
    /**
     * Description
     * <p>
     * an optional longer description of the environment
     * 
     */
    @SerializedName("description")
    @Expose
    public String description;
    /**
     * Custom Branch Name
     * <p>
     * when using gitops feature, an optional branch name for the environment
     * 
     */
    @SerializedName("custom_branch_name")
    @Expose
    public String customBranchName;
    /**
     * Pdp Configs
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("pdp_configs")
    @Expose
    public List<PDPConfigRead> pdpConfigs;
    /**
     * Statistics
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("stats")
    @Expose
    public Statistics stats;

    /**
     * No args constructor for use in serialization
     * 
     */
    public EnvironmentStats() {
    }

    /**
     * 
     * @param organizationId
     * @param createdAt
     * @param stats
     * @param name
     * @param id
     * @param pdpConfigs
     * @param projectId
     * @param key
     * @param updatedAt
     */
    public EnvironmentStats(String key, String id, String organizationId, String projectId, Date createdAt, Date updatedAt, String name, List<PDPConfigRead> pdpConfigs, Statistics stats) {
        super();
        this.key = key;
        this.id = id;
        this.organizationId = organizationId;
        this.projectId = projectId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.pdpConfigs = pdpConfigs;
        this.stats = stats;
    }

    public EnvironmentStats withKey(String key) {
        this.key = key;
        return this;
    }

    public EnvironmentStats withId(String id) {
        this.id = id;
        return this;
    }

    public EnvironmentStats withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public EnvironmentStats withProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public EnvironmentStats withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public EnvironmentStats withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public EnvironmentStats withName(String name) {
        this.name = name;
        return this;
    }

    public EnvironmentStats withDescription(String description) {
        this.description = description;
        return this;
    }

    public EnvironmentStats withCustomBranchName(String customBranchName) {
        this.customBranchName = customBranchName;
        return this;
    }

    public EnvironmentStats withPdpConfigs(List<PDPConfigRead> pdpConfigs) {
        this.pdpConfigs = pdpConfigs;
        return this;
    }

    public EnvironmentStats withStats(Statistics stats) {
        this.stats = stats;
        return this;
    }

}
