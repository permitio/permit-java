
package io.permit.sdk.openapi.models;

import java.util.Date;
import java.util.UUID;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * TenantRead
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
    "last_action_at",
    "name",
    "description",
    "attributes"
})
@Generated("jsonschema2pojo")
public class TenantRead {

    /**
     * Key
     * <p>
     * A unique id by which Permit will identify the tenant. The tenant key must be url-friendly (slugified).
     * (Required)
     * 
     */
    @JsonProperty("key")
    @JsonPropertyDescription("A unique id by which Permit will identify the tenant. The tenant key must be url-friendly (slugified).")
    public String key;
    /**
     * Id
     * <p>
     * Unique id of the tenant
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("Unique id of the tenant")
    public UUID id;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the tenant belongs to.
     * (Required)
     * 
     */
    @JsonProperty("organization_id")
    @JsonPropertyDescription("Unique id of the organization that the tenant belongs to.")
    public UUID organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the tenant belongs to.
     * (Required)
     * 
     */
    @JsonProperty("project_id")
    @JsonPropertyDescription("Unique id of the project that the tenant belongs to.")
    public UUID projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the tenant belongs to.
     * (Required)
     * 
     */
    @JsonProperty("environment_id")
    @JsonPropertyDescription("Unique id of the environment that the tenant belongs to.")
    public UUID environmentId;
    /**
     * Created At
     * <p>
     * Date and time when the tenant was created (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("created_at")
    @JsonPropertyDescription("Date and time when the tenant was created (ISO_8601 format).")
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the tenant was last updated/modified (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("updated_at")
    @JsonPropertyDescription("Date and time when the tenant was last updated/modified (ISO_8601 format).")
    public Date updatedAt;
    /**
     * Last Action At
     * <p>
     * Date and time when the tenant was last active (ISO_8601 format). In other words, this is the last time a permission check was done on a resource belonging to this tenant.
     * (Required)
     * 
     */
    @JsonProperty("last_action_at")
    @JsonPropertyDescription("Date and time when the tenant was last active (ISO_8601 format). In other words, this is the last time a permission check was done on a resource belonging to this tenant.")
    public Date lastActionAt;
    /**
     * Name
     * <p>
     * A descriptive name for the tenant
     * (Required)
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("A descriptive name for the tenant")
    public String name;
    /**
     * Description
     * <p>
     * an optional longer description of the tenant
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("an optional longer description of the tenant")
    public String description;
    /**
     * Attributes
     * <p>
     * Arbitraty tenant attributes that will be used to enforce attribute-based access control policies.
     * 
     */
    @JsonProperty("attributes")
    @JsonPropertyDescription("Arbitraty tenant attributes that will be used to enforce attribute-based access control policies.")
    public Attributes__10 attributes;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TenantRead() {
    }

    /**
     * 
     * @param organizationId
     * @param createdAt
     * @param environmentId
     * @param lastActionAt
     * @param name
     * @param id
     * @param projectId
     * @param key
     * @param updatedAt
     */
    public TenantRead(String key, UUID id, UUID organizationId, UUID projectId, UUID environmentId, Date createdAt, Date updatedAt, Date lastActionAt, String name) {
        super();
        this.key = key;
        this.id = id;
        this.organizationId = organizationId;
        this.projectId = projectId;
        this.environmentId = environmentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastActionAt = lastActionAt;
        this.name = name;
    }

    public TenantRead withKey(String key) {
        this.key = key;
        return this;
    }

    public TenantRead withId(UUID id) {
        this.id = id;
        return this;
    }

    public TenantRead withOrganizationId(UUID organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public TenantRead withProjectId(UUID projectId) {
        this.projectId = projectId;
        return this;
    }

    public TenantRead withEnvironmentId(UUID environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public TenantRead withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public TenantRead withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public TenantRead withLastActionAt(Date lastActionAt) {
        this.lastActionAt = lastActionAt;
        return this;
    }

    public TenantRead withName(String name) {
        this.name = name;
        return this;
    }

    public TenantRead withDescription(String description) {
        this.description = description;
        return this;
    }

    public TenantRead withAttributes(Attributes__10 attributes) {
        this.attributes = attributes;
        return this;
    }

}
