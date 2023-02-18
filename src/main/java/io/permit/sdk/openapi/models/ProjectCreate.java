
package io.permit.sdk.openapi.models;

import java.util.UUID;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ProjectCreate
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "urn_namespace",
    "name",
    "description",
    "settings",
    "active_policy_repo_id"
})
@Generated("jsonschema2pojo")
public class ProjectCreate {

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
    public Settings__4 settings;
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
    public ProjectCreate() {
    }

    /**
     * 
     * @param name
     * @param key
     */
    public ProjectCreate(String key, String name) {
        super();
        this.key = key;
        this.name = name;
    }

    public ProjectCreate withKey(String key) {
        this.key = key;
        return this;
    }

    public ProjectCreate withUrnNamespace(String urnNamespace) {
        this.urnNamespace = urnNamespace;
        return this;
    }

    public ProjectCreate withName(String name) {
        this.name = name;
        return this;
    }

    public ProjectCreate withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProjectCreate withSettings(Settings__4 settings) {
        this.settings = settings;
        return this;
    }

    public ProjectCreate withActivePolicyRepoId(UUID activePolicyRepoId) {
        this.activePolicyRepoId = activePolicyRepoId;
        return this;
    }

}
