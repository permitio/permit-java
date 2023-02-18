
package io.permit.sdk.openapi.models;

import java.util.Date;
import java.util.UUID;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ResourceRead
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "id",
    "organization_id",
    "project_id",
    "environment_id",
    "created_at",
    "updated_at",
    "name",
    "urn",
    "description",
    "actions",
    "attributes"
})
@Generated("jsonschema2pojo")
public class ResourceRead {

    /**
     * Key
     * <p>
     * A URL-friendly name of the resource (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the resource.
     * (Required)
     * 
     */
    @JsonProperty("key")
    @JsonPropertyDescription("A URL-friendly name of the resource (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the resource.")
    public String key;
    /**
     * Id
     * <p>
     * Unique id of the resource
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("Unique id of the resource")
    public UUID id;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the resource belongs to.
     * (Required)
     * 
     */
    @JsonProperty("organization_id")
    @JsonPropertyDescription("Unique id of the organization that the resource belongs to.")
    public UUID organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the resource belongs to.
     * (Required)
     * 
     */
    @JsonProperty("project_id")
    @JsonPropertyDescription("Unique id of the project that the resource belongs to.")
    public UUID projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the resource belongs to.
     * (Required)
     * 
     */
    @JsonProperty("environment_id")
    @JsonPropertyDescription("Unique id of the environment that the resource belongs to.")
    public UUID environmentId;
    /**
     * Created At
     * <p>
     * Date and time when the resource was created (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("created_at")
    @JsonPropertyDescription("Date and time when the resource was created (ISO_8601 format).")
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the resource was last updated/modified (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("updated_at")
    @JsonPropertyDescription("Date and time when the resource was last updated/modified (ISO_8601 format).")
    public Date updatedAt;
    /**
     * Name
     * <p>
     * The name of the resource
     * (Required)
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("The name of the resource")
    public String name;
    /**
     * Urn
     * <p>
     * The [URN](https://en.wikipedia.org/wiki/Uniform_Resource_Name) (Uniform Resource Name) of the resource
     * 
     */
    @JsonProperty("urn")
    @JsonPropertyDescription("The [URN](https://en.wikipedia.org/wiki/Uniform_Resource_Name) (Uniform Resource Name) of the resource")
    public String urn;
    /**
     * Description
     * <p>
     * An optional longer description of what this resource respresents in your system
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("An optional longer description of what this resource respresents in your system")
    public String description;
    /**
     * Actions
     * <p>
     * 
     *         A actions definition block, typically contained within a resource type definition block.
     *         The actions represents the ways you can interact with a protected resource.
     *         
     * 
     */
    @JsonProperty("actions")
    @JsonPropertyDescription("\n        A actions definition block, typically contained within a resource type definition block.\n        The actions represents the ways you can interact with a protected resource.\n        ")
    public Actions actions;
    /**
     * Attributes
     * <p>
     * Attributes that each resource of this type defines, and can be used in your ABAC policies.
     * 
     */
    @JsonProperty("attributes")
    @JsonPropertyDescription("Attributes that each resource of this type defines, and can be used in your ABAC policies.")
    public Attributes attributes;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResourceRead() {
    }

    /**
     * 
     * @param organizationId
     * @param createdAt
     * @param environmentId
     * @param name
     * @param id
     * @param projectId
     * @param key
     * @param updatedAt
     */
    public ResourceRead(String key, UUID id, UUID organizationId, UUID projectId, UUID environmentId, Date createdAt, Date updatedAt, String name) {
        super();
        this.key = key;
        this.id = id;
        this.organizationId = organizationId;
        this.projectId = projectId;
        this.environmentId = environmentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
    }

    public ResourceRead withKey(String key) {
        this.key = key;
        return this;
    }

    public ResourceRead withId(UUID id) {
        this.id = id;
        return this;
    }

    public ResourceRead withOrganizationId(UUID organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public ResourceRead withProjectId(UUID projectId) {
        this.projectId = projectId;
        return this;
    }

    public ResourceRead withEnvironmentId(UUID environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public ResourceRead withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ResourceRead withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public ResourceRead withName(String name) {
        this.name = name;
        return this;
    }

    public ResourceRead withUrn(String urn) {
        this.urn = urn;
        return this;
    }

    public ResourceRead withDescription(String description) {
        this.description = description;
        return this;
    }

    public ResourceRead withActions(Actions actions) {
        this.actions = actions;
        return this;
    }

    public ResourceRead withAttributes(Attributes attributes) {
        this.attributes = attributes;
        return this;
    }

}
