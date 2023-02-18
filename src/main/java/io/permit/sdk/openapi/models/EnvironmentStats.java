
package io.permit.sdk.openapi.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * EnvironmentStats
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "id",
    "organization_id",
    "project_id",
    "created_at",
    "updated_at",
    "name",
    "description",
    "custom_branch_name",
    "pdp_configs",
    "stats"
})
@Generated("jsonschema2pojo")
public class EnvironmentStats {

    /**
     * Key
     * <p>
     * A URL-friendly name of the environment (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the environment.
     * (Required)
     * 
     */
    @JsonProperty("key")
    @JsonPropertyDescription("A URL-friendly name of the environment (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the environment.")
    public String key;
    /**
     * Id
     * <p>
     * Unique id of the environment
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("Unique id of the environment")
    public UUID id;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the environment belongs to.
     * (Required)
     * 
     */
    @JsonProperty("organization_id")
    @JsonPropertyDescription("Unique id of the organization that the environment belongs to.")
    public UUID organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the environment belongs to.
     * (Required)
     * 
     */
    @JsonProperty("project_id")
    @JsonPropertyDescription("Unique id of the project that the environment belongs to.")
    public UUID projectId;
    /**
     * Created At
     * <p>
     * Date and time when the environment was created (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("created_at")
    @JsonPropertyDescription("Date and time when the environment was created (ISO_8601 format).")
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the environment was last updated/modified (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("updated_at")
    @JsonPropertyDescription("Date and time when the environment was last updated/modified (ISO_8601 format).")
    public Date updatedAt;
    /**
     * Name
     * <p>
     * The name of the environment
     * (Required)
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("The name of the environment")
    public String name;
    /**
     * Description
     * <p>
     * an optional longer description of the environment
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("an optional longer description of the environment")
    public String description;
    /**
     * Custom Branch Name
     * <p>
     * when using gitops feature, an optional branch name for the environment
     * 
     */
    @JsonProperty("custom_branch_name")
    @JsonPropertyDescription("when using gitops feature, an optional branch name for the environment")
    public String customBranchName;
    /**
     * Pdp Configs
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("pdp_configs")
    public List<PDPConfigRead> pdpConfigs = new ArrayList<PDPConfigRead>();
    /**
     * Statistics
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("stats")
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
    public EnvironmentStats(String key, UUID id, UUID organizationId, UUID projectId, Date createdAt, Date updatedAt, String name, List<PDPConfigRead> pdpConfigs, Statistics stats) {
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

    public EnvironmentStats withId(UUID id) {
        this.id = id;
        return this;
    }

    public EnvironmentStats withOrganizationId(UUID organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public EnvironmentStats withProjectId(UUID projectId) {
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
