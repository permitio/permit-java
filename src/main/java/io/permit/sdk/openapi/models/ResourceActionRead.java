
package io.permit.sdk.openapi.models;

import java.util.Date;
import java.util.UUID;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ResourceActionRead
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "description",
    "key",
    "id",
    "permission_name",
    "organization_id",
    "project_id",
    "environment_id",
    "resource_id",
    "created_at",
    "updated_at"
})
@Generated("jsonschema2pojo")
public class ResourceActionRead {

    /**
     * Name
     * <p>
     * The name of the action
     * (Required)
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("The name of the action")
    public String name;
    /**
     * Description
     * <p>
     * An optional longer description of what this action respresents in your system
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("An optional longer description of what this action respresents in your system")
    public String description;
    /**
     * Key
     * <p>
     * A URL-friendly name of the action (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the action.
     * (Required)
     * 
     */
    @JsonProperty("key")
    @JsonPropertyDescription("A URL-friendly name of the action (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the action.")
    public String key;
    /**
     * Id
     * <p>
     * Unique id of the action
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("Unique id of the action")
    public UUID id;
    /**
     * Permission Name
     * <p>
     * The name of the action, prefixed by the resource the action is acting upon.
     * (Required)
     * 
     */
    @JsonProperty("permission_name")
    @JsonPropertyDescription("The name of the action, prefixed by the resource the action is acting upon.")
    public String permissionName;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the action belongs to.
     * (Required)
     * 
     */
    @JsonProperty("organization_id")
    @JsonPropertyDescription("Unique id of the organization that the action belongs to.")
    public UUID organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the action belongs to.
     * (Required)
     * 
     */
    @JsonProperty("project_id")
    @JsonPropertyDescription("Unique id of the project that the action belongs to.")
    public UUID projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the action belongs to.
     * (Required)
     * 
     */
    @JsonProperty("environment_id")
    @JsonPropertyDescription("Unique id of the environment that the action belongs to.")
    public UUID environmentId;
    /**
     * Resource Id
     * <p>
     * Unique id of the resource that the action belongs to.
     * (Required)
     * 
     */
    @JsonProperty("resource_id")
    @JsonPropertyDescription("Unique id of the resource that the action belongs to.")
    public UUID resourceId;
    /**
     * Created At
     * <p>
     * Date and time when the action was created (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("created_at")
    @JsonPropertyDescription("Date and time when the action was created (ISO_8601 format).")
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the action was last updated/modified (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("updated_at")
    @JsonPropertyDescription("Date and time when the action was last updated/modified (ISO_8601 format).")
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
    public ResourceActionRead(String name, String key, UUID id, String permissionName, UUID organizationId, UUID projectId, UUID environmentId, UUID resourceId, Date createdAt, Date updatedAt) {
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

    public ResourceActionRead withKey(String key) {
        this.key = key;
        return this;
    }

    public ResourceActionRead withId(UUID id) {
        this.id = id;
        return this;
    }

    public ResourceActionRead withPermissionName(String permissionName) {
        this.permissionName = permissionName;
        return this;
    }

    public ResourceActionRead withOrganizationId(UUID organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public ResourceActionRead withProjectId(UUID projectId) {
        this.projectId = projectId;
        return this;
    }

    public ResourceActionRead withEnvironmentId(UUID environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public ResourceActionRead withResourceId(UUID resourceId) {
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
