
package io.permit.sdk.openapi.models;

import java.util.Date;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * EnvironmentRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class EnvironmentRead {

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
     * No args constructor for use in serialization
     * 
     */
    public EnvironmentRead() {
    }

    /**
     * 
     * @param organizationId
     * @param createdAt
     * @param name
     * @param id
     * @param projectId
     * @param key
     * @param updatedAt
     */
    public EnvironmentRead(String key, String id, String organizationId, String projectId, Date createdAt, Date updatedAt, String name) {
        super();
        this.key = key;
        this.id = id;
        this.organizationId = organizationId;
        this.projectId = projectId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
    }

    public EnvironmentRead withKey(String key) {
        this.key = key;
        return this;
    }

    public EnvironmentRead withId(String id) {
        this.id = id;
        return this;
    }

    public EnvironmentRead withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public EnvironmentRead withProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public EnvironmentRead withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public EnvironmentRead withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public EnvironmentRead withName(String name) {
        this.name = name;
        return this;
    }

    public EnvironmentRead withDescription(String description) {
        this.description = description;
        return this;
    }

    public EnvironmentRead withCustomBranchName(String customBranchName) {
        this.customBranchName = customBranchName;
        return this;
    }

}
