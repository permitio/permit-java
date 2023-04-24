
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ProjectCreate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ProjectCreate {

    /**
     * Key
     * <p>
     * A URL-friendly name of the project (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the project.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;
    /**
     * Urn Namespace
     * <p>
     * Optional namespace for URNs. If empty, URNs will be generated from project key.
     * 
     */
    @SerializedName("urn_namespace")
    @Expose
    public String urnNamespace;
    /**
     * Name
     * <p>
     * The name of the project
     * (Required)
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
    public Settings__4 settings;
    /**
     * Active Policy Repo Id
     * <p>
     * the id of the policy repo to use for this project
     * 
     */
    @SerializedName("active_policy_repo_id")
    @Expose
    public String activePolicyRepoId;

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

    public ProjectCreate withActivePolicyRepoId(String activePolicyRepoId) {
        this.activePolicyRepoId = activePolicyRepoId;
        return this;
    }

}
