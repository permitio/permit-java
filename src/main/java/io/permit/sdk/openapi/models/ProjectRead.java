
package io.permit.sdk.openapi.models;

import java.util.Date;
import java.util.UUID;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ProjectRead
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "urn_namespace",
    "id",
    "organization_id",
    "created_at",
    "updated_at",
    "name",
    "description",
    "settings",
    "active_policy_repo_id"
})
@Generated("jsonschema2pojo")
public class ProjectRead {

    /**
     * Key
     * <p>
     * A URL-friendly name of the project (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the project.
     * (Required)
     * 
     */
    @JsonProperty("key")
    @JsonPropertyDescription("A URL-friendly name of the project (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the project.")
    public String key;
    /**
     * Urn Namespace
     * <p>
     * Optional namespace for URNs. If empty, URNs will be generated from project key.
     * 
     */
    @JsonProperty("urn_namespace")
    @JsonPropertyDescription("Optional namespace for URNs. If empty, URNs will be generated from project key.")
    public String urnNamespace;
    /**
     * Id
     * <p>
     * Unique id of the project
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("Unique id of the project")
    public UUID id;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the project belongs to.
     * (Required)
     * 
     */
    @JsonProperty("organization_id")
    @JsonPropertyDescription("Unique id of the organization that the project belongs to.")
    public UUID organizationId;
    /**
     * Created At
     * <p>
     * Date and time when the project was created (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("created_at")
    @JsonPropertyDescription("Date and time when the project was created (ISO_8601 format).")
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the project was last updated/modified (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("updated_at")
    @JsonPropertyDescription("Date and time when the project was last updated/modified (ISO_8601 format).")
    public Date updatedAt;
    /**
     * Name
     * <p>
     * The name of the project
     * (Required)
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("The name of the project")
    public String name;
    /**
     * Description
     * <p>
     * a longer description outlining the project objectives
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("a longer description outlining the project objectives")
    public String description;
    /**
     * Settings
     * <p>
     * the settings for this project
     * 
     */
    @JsonProperty("settings")
    @JsonPropertyDescription("the settings for this project")
    public Settings__5 settings;
    /**
     * Active Policy Repo Id
     * <p>
     * the id of the policy repo to use for this project
     * 
     */
    @JsonProperty("active_policy_repo_id")
    @JsonPropertyDescription("the id of the policy repo to use for this project")
    public UUID activePolicyRepoId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ProjectRead() {
    }

    /**
     * 
     * @param organizationId
     * @param createdAt
     * @param name
     * @param id
     * @param key
     * @param updatedAt
     */
    public ProjectRead(String key, UUID id, UUID organizationId, Date createdAt, Date updatedAt, String name) {
        super();
        this.key = key;
        this.id = id;
        this.organizationId = organizationId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
    }

    public ProjectRead withKey(String key) {
        this.key = key;
        return this;
    }

    public ProjectRead withUrnNamespace(String urnNamespace) {
        this.urnNamespace = urnNamespace;
        return this;
    }

    public ProjectRead withId(UUID id) {
        this.id = id;
        return this;
    }

    public ProjectRead withOrganizationId(UUID organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public ProjectRead withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ProjectRead withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public ProjectRead withName(String name) {
        this.name = name;
        return this;
    }

    public ProjectRead withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProjectRead withSettings(Settings__5 settings) {
        this.settings = settings;
        return this;
    }

    public ProjectRead withActivePolicyRepoId(UUID activePolicyRepoId) {
        this.activePolicyRepoId = activePolicyRepoId;
        return this;
    }

}
