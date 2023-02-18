
package io.permit.sdk.openapi.models;

import java.util.UUID;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ProjectUpdate
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "description",
    "settings",
    "active_policy_repo_id"
})
@Generated("jsonschema2pojo")
public class ProjectUpdate {

    /**
     * Name
     * <p>
     * The name of the project
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
    public Settings__6 settings;
    /**
     * Active Policy Repo Id
     * <p>
     * the id of the policy repo to use for this project
     * 
     */
    @JsonProperty("active_policy_repo_id")
    @JsonPropertyDescription("the id of the policy repo to use for this project")
    public UUID activePolicyRepoId;

    public ProjectUpdate withName(String name) {
        this.name = name;
        return this;
    }

    public ProjectUpdate withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProjectUpdate withSettings(Settings__6 settings) {
        this.settings = settings;
        return this;
    }

    public ProjectUpdate withActivePolicyRepoId(UUID activePolicyRepoId) {
        this.activePolicyRepoId = activePolicyRepoId;
        return this;
    }

}
