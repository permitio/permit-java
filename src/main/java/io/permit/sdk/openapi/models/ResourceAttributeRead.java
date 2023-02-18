
package io.permit.sdk.openapi.models;

import java.util.Date;
import java.util.UUID;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ResourceAttributeRead
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "description",
    "key",
    "id",
    "resource_id",
    "resource_key",
    "organization_id",
    "project_id",
    "environment_id",
    "created_at",
    "updated_at"
})
@Generated("jsonschema2pojo")
public class ResourceAttributeRead {

    /**
     * The type of the attribute, we currently support: `bool`, `number` (ints, floats), `time` (a timestamp), `string`, and `json`.
     * (Required)
     * 
     */
    @JsonProperty("type")
    @JsonPropertyDescription("The type of the attribute, we currently support: `bool`, `number` (ints, floats), `time` (a timestamp), `string`, and `json`.")
    public Object type;
    /**
     * Description
     * <p>
     * An optional longer description of what this attribute respresents in your system
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("An optional longer description of what this attribute respresents in your system")
    public String description;
    /**
     * Key
     * <p>
     * A URL-friendly name of the attribute (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the attribute.
     * (Required)
     * 
     */
    @JsonProperty("key")
    @JsonPropertyDescription("A URL-friendly name of the attribute (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the attribute.")
    public String key;
    /**
     * Id
     * <p>
     * Unique id of the attribute
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("Unique id of the attribute")
    public UUID id;
    /**
     * Resource Id
     * <p>
     * Unique id of the resource that the attribute belongs to.
     * (Required)
     * 
     */
    @JsonProperty("resource_id")
    @JsonPropertyDescription("Unique id of the resource that the attribute belongs to.")
    public UUID resourceId;
    /**
     * Resource Key
     * <p>
     * A URL-friendly name of the resource (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the resource.
     * (Required)
     * 
     */
    @JsonProperty("resource_key")
    @JsonPropertyDescription("A URL-friendly name of the resource (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the resource.")
    public String resourceKey;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the attribute belongs to.
     * (Required)
     * 
     */
    @JsonProperty("organization_id")
    @JsonPropertyDescription("Unique id of the organization that the attribute belongs to.")
    public UUID organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the attribute belongs to.
     * (Required)
     * 
     */
    @JsonProperty("project_id")
    @JsonPropertyDescription("Unique id of the project that the attribute belongs to.")
    public UUID projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the attribute belongs to.
     * (Required)
     * 
     */
    @JsonProperty("environment_id")
    @JsonPropertyDescription("Unique id of the environment that the attribute belongs to.")
    public UUID environmentId;
    /**
     * Created At
     * <p>
     * Date and time when the attribute was created (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("created_at")
    @JsonPropertyDescription("Date and time when the attribute was created (ISO_8601 format).")
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the attribute was last updated/modified (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("updated_at")
    @JsonPropertyDescription("Date and time when the attribute was last updated/modified (ISO_8601 format).")
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
    public ResourceAttributeRead(Object type, String key, UUID id, UUID resourceId, String resourceKey, UUID organizationId, UUID projectId, UUID environmentId, Date createdAt, Date updatedAt) {
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

    public ResourceAttributeRead withType(Object type) {
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

    public ResourceAttributeRead withId(UUID id) {
        this.id = id;
        return this;
    }

    public ResourceAttributeRead withResourceId(UUID resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public ResourceAttributeRead withResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
        return this;
    }

    public ResourceAttributeRead withOrganizationId(UUID organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public ResourceAttributeRead withProjectId(UUID projectId) {
        this.projectId = projectId;
        return this;
    }

    public ResourceAttributeRead withEnvironmentId(UUID environmentId) {
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
