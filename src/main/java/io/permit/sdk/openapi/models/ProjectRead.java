
package io.permit.sdk.openapi.models;

import java.util.Date;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ProjectRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ProjectRead {

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
     * Id
     * <p>
     * Unique id of the project
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public String id;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the project belongs to.
     * (Required)
     * 
     */
    @SerializedName("organization_id")
    @Expose
    public String organizationId;
    /**
     * Created At
     * <p>
     * Date and time when the project was created (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the project was last updated/modified (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("updated_at")
    @Expose
    public Date updatedAt;
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
    public Settings__5 settings;
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
    public ProjectRead(String key, String id, String organizationId, Date createdAt, Date updatedAt, String name) {
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

    public ProjectRead withId(String id) {
        this.id = id;
        return this;
    }

    public ProjectRead withOrganizationId(String organizationId) {
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

    public ProjectRead withActivePolicyRepoId(String activePolicyRepoId) {
        this.activePolicyRepoId = activePolicyRepoId;
        return this;
    }

}
