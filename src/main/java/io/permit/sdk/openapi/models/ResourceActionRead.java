
package io.permit.sdk.openapi.models;

import java.util.Date;
import java.util.HashMap;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ResourceActionRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ResourceActionRead {

    /**
     * Name
     * <p>
     * The name of the action
     * (Required)
     * 
     */
    @SerializedName("name")
    @Expose
    public String name;
    /**
     * Description
     * <p>
     * An optional longer description of what this action respresents in your system
     * 
     */
    @SerializedName("description")
    @Expose
    public String description;
    /**
     * Attributes
     * <p>
     * optional dictionary of key-value pairs that can be used to store arbitrary metadata about this action. This metadata can be used to filter actions using query parameters with attr_ prefix
     * 
     */
    @SerializedName("attributes")
    @Expose
    public HashMap<String, Object> attributes;
    /**
     * Key
     * <p>
     * A URL-friendly name of the action (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the action.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;
    /**
     * Id
     * <p>
     * Unique id of the action
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public String id;
    /**
     * Permission Name
     * <p>
     * The name of the action, prefixed by the resource the action is acting upon.
     * (Required)
     * 
     */
    @SerializedName("permission_name")
    @Expose
    public String permissionName;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the action belongs to.
     * (Required)
     * 
     */
    @SerializedName("organization_id")
    @Expose
    public String organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the action belongs to.
     * (Required)
     * 
     */
    @SerializedName("project_id")
    @Expose
    public String projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the action belongs to.
     * (Required)
     * 
     */
    @SerializedName("environment_id")
    @Expose
    public String environmentId;
    /**
     * Resource Id
     * <p>
     * Unique id of the resource that the action belongs to.
     * (Required)
     * 
     */
    @SerializedName("resource_id")
    @Expose
    public String resourceId;
    /**
     * Created At
     * <p>
     * Date and time when the action was created (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the action was last updated/modified (ISO_8601 format).
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
    public ResourceActionRead() {
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
     * @param permissionName
     * @param updatedAt
     */
    public ResourceActionRead(String name, String key, String id, String permissionName, String organizationId, String projectId, String environmentId, String resourceId, Date createdAt, Date updatedAt) {
        super();
        this.name = name;
        this.key = key;
        this.id = id;
        this.permissionName = permissionName;
        this.organizationId = organizationId;
        this.projectId = projectId;
        this.environmentId = environmentId;
        this.resourceId = resourceId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ResourceActionRead withName(String name) {
        this.name = name;
        return this;
    }

    public ResourceActionRead withDescription(String description) {
        this.description = description;
        return this;
    }

    public ResourceActionRead withAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public ResourceActionRead withKey(String key) {
        this.key = key;
        return this;
    }

    public ResourceActionRead withId(String id) {
        this.id = id;
        return this;
    }

    public ResourceActionRead withPermissionName(String permissionName) {
        this.permissionName = permissionName;
        return this;
    }

    public ResourceActionRead withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public ResourceActionRead withProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public ResourceActionRead withEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public ResourceActionRead withResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public ResourceActionRead withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ResourceActionRead withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

}
