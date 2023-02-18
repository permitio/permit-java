
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ProjectUpdate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ProjectUpdate {

    /**
     * Name
     * <p>
     * The name of the project
     * 
     */
    @SerializedName("name")
    @Expose
    public String name;
    /**
     * Description
     * <p>
     * a longer description outlining the project objectives
     * 
     */
    @SerializedName("description")
    @Expose
    public String description;
    /**
     * Settings
     * <p>
     * the settings for this project
     * 
     */
    @SerializedName("settings")
    @Expose
    public Settings__6 settings;
    /**
     * Active Policy Repo Id
     * <p>
     * the id of the policy repo to use for this project
     * 
     */
    @SerializedName("active_policy_repo_id")
    @Expose
    public String activePolicyRepoId;

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

    public ProjectUpdate withActivePolicyRepoId(String activePolicyRepoId) {
        this.activePolicyRepoId = activePolicyRepoId;
        return this;
    }

}
