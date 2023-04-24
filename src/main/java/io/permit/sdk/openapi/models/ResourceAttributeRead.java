
package io.permit.sdk.openapi.models;

import java.util.Date;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ResourceAttributeRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ResourceAttributeRead {

    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("type")
    @Expose
    public AttributeType type;
    /**
     * Description
     * <p>
     * An optional longer description of what this attribute respresents in your system
     * 
     */
    @SerializedName("description")
    @Expose
    public String description;
    /**
     * Key
     * <p>
     * A URL-friendly name of the attribute (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the attribute.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;
    /**
     * Id
     * <p>
     * Unique id of the attribute
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public String id;
    /**
     * Resource Id
     * <p>
     * Unique id of the resource that the attribute belongs to.
     * (Required)
     * 
     */
    @SerializedName("resource_id")
    @Expose
    public String resourceId;
    /**
     * Resource Key
     * <p>
     * A URL-friendly name of the resource (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the resource.
     * (Required)
     * 
     */
    @SerializedName("resource_key")
    @Expose
    public String resourceKey;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the attribute belongs to.
     * (Required)
     * 
     */
    @SerializedName("organization_id")
    @Expose
    public String organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the attribute belongs to.
     * (Required)
     * 
     */
    @SerializedName("project_id")
    @Expose
    public String projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the attribute belongs to.
     * (Required)
     * 
     */
    @SerializedName("environment_id")
    @Expose
    public String environmentId;
    /**
     * Created At
     * <p>
     * Date and time when the attribute was created (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the attribute was last updated/modified (ISO_8601 format).
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
    public ResourceAttributeRead() {
    }

    /**
     * 
     * @param organizationId
     * @param createdAt
     * @param resourceId
     * @param environmentId
     * @param resourceKey
     * @param id
     * @param type
     * @param projectId
     * @param key
     * @param updatedAt
     */
    public ResourceAttributeRead(AttributeType type, String key, String id, String resourceId, String resourceKey, String organizationId, String projectId, String environmentId, Date createdAt, Date updatedAt) {
        super();
        this.type = type;
        this.key = key;
        this.id = id;
        this.resourceId = resourceId;
        this.resourceKey = resourceKey;
        this.organizationId = organizationId;
        this.projectId = projectId;
        this.environmentId = environmentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ResourceAttributeRead withType(AttributeType type) {
        this.type = type;
        return this;
    }

    public ResourceAttributeRead withDescription(String description) {
        this.description = description;
        return this;
    }

    public ResourceAttributeRead withKey(String key) {
        this.key = key;
        return this;
    }

    public ResourceAttributeRead withId(String id) {
        this.id = id;
        return this;
    }

    public ResourceAttributeRead withResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public ResourceAttributeRead withResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
        return this;
    }

    public ResourceAttributeRead withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public ResourceAttributeRead withProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public ResourceAttributeRead withEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public ResourceAttributeRead withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ResourceAttributeRead withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

}
