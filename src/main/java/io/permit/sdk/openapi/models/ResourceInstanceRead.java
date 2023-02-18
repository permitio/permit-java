
package io.permit.sdk.openapi.models;

import java.util.Date;
import java.util.UUID;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ResourceInstanceRead
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "tenant",
    "resource",
    "id",
    "organization_id",
    "project_id",
    "environment_id",
    "created_at",
    "updated_at",
    "resource_id",
    "tenant_id",
    "attributes"
})
@Generated("jsonschema2pojo")
public class ResourceInstanceRead {

    /**
     * Key
     * <p>
     * A unique identifier by which Permit will identify the resource instance for permission checks. You will later pass this identifier to the `permit.check()` API. A key can be anything: for example the resource db id, a url slug, a UUID or anything else as long as it's unique on your end. The resource instance key must be url-friendly.
     * (Required)
     * 
     */
    @JsonProperty("key")
    @JsonPropertyDescription("A unique identifier by which Permit will identify the resource instance for permission checks. You will later pass this identifier to the `permit.check()` API. A key can be anything: for example the resource db id, a url slug, a UUID or anything else as long as it's unique on your end. The resource instance key must be url-friendly.")
    public String key;
    /**
     * Tenant
     * <p>
     * the *key* of the tenant that this resource belongs to, used to enforce tenant boundaries in multi-tenant apps.
     * 
     */
    @JsonProperty("tenant")
    @JsonPropertyDescription("the *key* of the tenant that this resource belongs to, used to enforce tenant boundaries in multi-tenant apps.")
    public String tenant;
    /**
     * Resource
     * <p>
     * the *key* of the resource (type) of this resource instance. For example: if this resource instance is the annual budget document, the key of the resource might be `document`.
     * (Required)
     * 
     */
    @JsonProperty("resource")
    @JsonPropertyDescription("the *key* of the resource (type) of this resource instance. For example: if this resource instance is the annual budget document, the key of the resource might be `document`.")
    public String resource;
    /**
     * Id
     * <p>
     * Unique id of the resource instance
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("Unique id of the resource instance")
    public UUID id;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the resource instance belongs to.
     * (Required)
     * 
     */
    @JsonProperty("organization_id")
    @JsonPropertyDescription("Unique id of the organization that the resource instance belongs to.")
    public UUID organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the resource instance belongs to.
     * (Required)
     * 
     */
    @JsonProperty("project_id")
    @JsonPropertyDescription("Unique id of the project that the resource instance belongs to.")
    public UUID projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the resource instance belongs to.
     * (Required)
     * 
     */
    @JsonProperty("environment_id")
    @JsonPropertyDescription("Unique id of the environment that the resource instance belongs to.")
    public UUID environmentId;
    /**
     * Created At
     * <p>
     * Date and time when the resource instance was created (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("created_at")
    @JsonPropertyDescription("Date and time when the resource instance was created (ISO_8601 format).")
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the resource instance was last updated/modified (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("updated_at")
    @JsonPropertyDescription("Date and time when the resource instance was last updated/modified (ISO_8601 format).")
    public Date updatedAt;
    /**
     * Resource Id
     * <p>
     * the id of the resource (type) of this resource instance.
     * (Required)
     * 
     */
    @JsonProperty("resource_id")
    @JsonPropertyDescription("the id of the resource (type) of this resource instance.")
    public UUID resourceId;
    /**
     * Tenant Id
     * <p>
     * the id of the tenant of this resource instance.
     * 
     */
    @JsonProperty("tenant_id")
    @JsonPropertyDescription("the id of the tenant of this resource instance.")
    public UUID tenantId;
    /**
     * Attributes
     * <p>
     * Arbitraty resource attributes that will be used to enforce attribute-based access control policies.
     * 
     */
    @JsonProperty("attributes")
    @JsonPropertyDescription("Arbitraty resource attributes that will be used to enforce attribute-based access control policies.")
    public Attributes__4 attributes;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResourceInstanceRead() {
    }

    /**
     * 
     * @param organizationId
     * @param createdAt
     * @param resourceId
     * @param environmentId
     * @param resource
     * @param id
     * @param projectId
     * @param key
     * @param updatedAt
     */
    public ResourceInstanceRead(String key, String resource, UUID id, UUID organizationId, UUID projectId, UUID environmentId, Date createdAt, Date updatedAt, UUID resourceId) {
        super();
        this.key = key;
        this.resource = resource;
        this.id = id;
        this.organizationId = organizationId;
        this.projectId = projectId;
        this.environmentId = environmentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.resourceId = resourceId;
    }

    public ResourceInstanceRead withKey(String key) {
        this.key = key;
        return this;
    }

    public ResourceInstanceRead withTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public ResourceInstanceRead withResource(String resource) {
        this.resource = resource;
        return this;
    }

    public ResourceInstanceRead withId(UUID id) {
        this.id = id;
        return this;
    }

    public ResourceInstanceRead withOrganizationId(UUID organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public ResourceInstanceRead withProjectId(UUID projectId) {
        this.projectId = projectId;
        return this;
    }

    public ResourceInstanceRead withEnvironmentId(UUID environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public ResourceInstanceRead withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ResourceInstanceRead withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public ResourceInstanceRead withResourceId(UUID resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public ResourceInstanceRead withTenantId(UUID tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public ResourceInstanceRead withAttributes(Attributes__4 attributes) {
        this.attributes = attributes;
        return this;
    }

}
