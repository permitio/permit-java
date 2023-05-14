
package io.permit.sdk.openapi.models;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ResourceActionGroupRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ResourceActionGroupRead {

    /**
     * Name
     * <p>
     * The name of the action group
     * (Required)
     * 
     */
    @SerializedName("name")
    @Expose
    public java.lang.String name;
    /**
     * Description
     * <p>
     * An optional longer description of what this action group represents in your system
     * 
     */
    @SerializedName("description")
    @Expose
    public java.lang.String description;
    /**
     * Attributes
     * <p>
     * optional dictionary of key-value pairs that can be used to store arbitrary metadata about this action group. This metadata can be used to filter action groups using query parameters with attr_ prefix
     * 
     */
    @SerializedName("attributes")
    @Expose
    public HashMap<String, Object> attributes;
    /**
     * Actions
     * <p>
     * 
     * 
     */
    @SerializedName("actions")
    @Expose
    public List<java.lang.String> actions;
    /**
     * Key
     * <p>
     * A URL-friendly name of the action group (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the action group.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public java.lang.String key;
    /**
     * Id
     * <p>
     * Unique id of the action group
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public UUID id;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the action group belongs to.
     * (Required)
     * 
     */
    @SerializedName("organization_id")
    @Expose
    public UUID organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the action group belongs to.
     * (Required)
     * 
     */
    @SerializedName("project_id")
    @Expose
    public UUID projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the action group belongs to.
     * (Required)
     * 
     */
    @SerializedName("environment_id")
    @Expose
    public UUID environmentId;
    /**
     * Resource Id
     * <p>
     * Unique id of the resource that the action group belongs to.
     * (Required)
     * 
     */
    @SerializedName("resource_id")
    @Expose
    public UUID resourceId;
    /**
     * Created At
     * <p>
     * Date and time when the action group was created (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the action group was last updated/modified (ISO_8601 format).
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
    public ResourceActionGroupRead() {
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
    public ResourceActionGroupRead(java.lang.String name, java.lang.String key, UUID id, UUID organizationId, UUID projectId, UUID environmentId, UUID resourceId, Date createdAt, Date updatedAt) {
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

    public ResourceActionGroupRead withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    public ResourceActionGroupRead withDescription(java.lang.String description) {
        this.description = description;
        return this;
    }

    public ResourceActionGroupRead withAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public ResourceActionGroupRead withActions(List<java.lang.String> actions) {
        this.actions = actions;
        return this;
    }

    public ResourceActionGroupRead withKey(java.lang.String key) {
        this.key = key;
        return this;
    }

    public ResourceActionGroupRead withId(UUID id) {
        this.id = id;
        return this;
    }

    public ResourceActionGroupRead withOrganizationId(UUID organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public ResourceActionGroupRead withProjectId(UUID projectId) {
        this.projectId = projectId;
        return this;
    }

    public ResourceActionGroupRead withEnvironmentId(UUID environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public ResourceActionGroupRead withResourceId(UUID resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public ResourceActionGroupRead withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ResourceActionGroupRead withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

}
