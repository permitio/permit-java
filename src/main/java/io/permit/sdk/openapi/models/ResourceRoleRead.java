
package io.permit.sdk.openapi.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ResourceRoleRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ResourceRoleRead {

    /**
     * Name
     * <p>
     * The name of the role
     * (Required)
     * 
     */
    @SerializedName("name")
    @Expose
    public String name;
    /**
     * Description
     * <p>
     * optional description string explaining what this role represents, or what permissions are granted to it.
     * 
     */
    @SerializedName("description")
    @Expose
    public String description;
    /**
     * Permissions
     * <p>
     * list of action keys that define what actions this resource role is permitted to do
     * 
     */
    @SerializedName("permissions")
    @Expose
    public List<String> permissions = new ArrayList<String>();
    /**
     * Extends
     * <p>
     * list of role keys that define what roles this role extends. In other words: this role will automatically inherit all the permissions of the given roles in this list.
     * 
     */
    @SerializedName("extends")
    @Expose
    public List<String> _extends = new ArrayList<String>();
    /**
     * Key
     * <p>
     * A URL-friendly name of the role (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the role.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;
    /**
     * Id
     * <p>
     * Unique id of the role
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public String id;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the role belongs to.
     * (Required)
     * 
     */
    @SerializedName("organization_id")
    @Expose
    public String organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the role belongs to.
     * (Required)
     * 
     */
    @SerializedName("project_id")
    @Expose
    public String projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the role belongs to.
     * (Required)
     * 
     */
    @SerializedName("environment_id")
    @Expose
    public String environmentId;
    /**
     * Resource Id
     * <p>
     * Unique id of the resource that the role belongs to.
     * (Required)
     * 
     */
    @SerializedName("resource_id")
    @Expose
    public String resourceId;
    /**
     * Created At
     * <p>
     * Date and time when the role was created (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the role was last updated/modified (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("updated_at")
    @Expose
    public Date updatedAt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResourceRoleRead() {
    }

    /**
     * 
     * @param organizationId
     * @param createdAt
     * @param resourceId
     * @param environmentId
     * @param name
     * @param id
     * @param projectId
     * @param key
     * @param updatedAt
     */
    public ResourceRoleRead(String name, String key, String id, String organizationId, String projectId, String environmentId, String resourceId, Date createdAt, Date updatedAt) {
        super();
        this.name = name;
        this.key = key;
        this.id = id;
        this.organizationId = organizationId;
        this.projectId = projectId;
        this.environmentId = environmentId;
        this.resourceId = resourceId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ResourceRoleRead withName(String name) {
        this.name = name;
        return this;
    }

    public ResourceRoleRead withDescription(String description) {
        this.description = description;
        return this;
    }

    public ResourceRoleRead withPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

    public ResourceRoleRead withExtends(List<String> _extends) {
        this._extends = _extends;
        return this;
    }

    public ResourceRoleRead withKey(String key) {
        this.key = key;
        return this;
    }

    public ResourceRoleRead withId(String id) {
        this.id = id;
        return this;
    }

    public ResourceRoleRead withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public ResourceRoleRead withProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public ResourceRoleRead withEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public ResourceRoleRead withResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public ResourceRoleRead withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ResourceRoleRead withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

}
