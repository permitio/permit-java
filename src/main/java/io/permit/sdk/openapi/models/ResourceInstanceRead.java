
package io.permit.sdk.openapi.models;

import java.util.Date;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ResourceInstanceRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ResourceInstanceRead {

    /**
     * Key
     * <p>
     * A unique identifier by which Permit will identify the resource instance for permission checks. You will later pass this identifier to the `permit.check()` API. A key can be anything: for example the resource db id, a url slug, a UUID or anything else as long as it's unique on your end. The resource instance key must be url-friendly.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;
    /**
     * Tenant
     * <p>
     * the *key* of the tenant that this resource belongs to, used to enforce tenant boundaries in multi-tenant apps.
     * 
     */
    @SerializedName("tenant")
    @Expose
    public String tenant;
    /**
     * Resource
     * <p>
     * the *key* of the resource (type) of this resource instance. For example: if this resource instance is the annual budget document, the key of the resource might be `document`.
     * (Required)
     * 
     */
    @SerializedName("resource")
    @Expose
    public String resource;
    /**
     * Id
     * <p>
     * Unique id of the resource instance
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public String id;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the resource instance belongs to.
     * (Required)
     * 
     */
    @SerializedName("organization_id")
    @Expose
    public String organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the resource instance belongs to.
     * (Required)
     * 
     */
    @SerializedName("project_id")
    @Expose
    public String projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the resource instance belongs to.
     * (Required)
     * 
     */
    @SerializedName("environment_id")
    @Expose
    public String environmentId;
    /**
     * Created At
     * <p>
     * Date and time when the resource instance was created (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the resource instance was last updated/modified (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("updated_at")
    @Expose
    public Date updatedAt;
    /**
     * Resource Id
     * <p>
     * the id of the resource (type) of this resource instance.
     * (Required)
     * 
     */
    @SerializedName("resource_id")
    @Expose
    public String resourceId;
    /**
     * Tenant Id
     * <p>
     * the id of the tenant of this resource instance.
     * 
     */
    @SerializedName("tenant_id")
    @Expose
    public String tenantId;
    /**
     * Attributes
     * <p>
     * Arbitraty resource attributes that will be used to enforce attribute-based access control policies.
     * 
     */
    @SerializedName("attributes")
    @Expose
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
    public ResourceInstanceRead(String key, String resource, String id, String organizationId, String projectId, String environmentId, Date createdAt, Date updatedAt, String resourceId) {
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

    public ResourceInstanceRead withId(String id) {
        this.id = id;
        return this;
    }

    public ResourceInstanceRead withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public ResourceInstanceRead withProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public ResourceInstanceRead withEnvironmentId(String environmentId) {
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

    public ResourceInstanceRead withResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public ResourceInstanceRead withTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public ResourceInstanceRead withAttributes(Attributes__4 attributes) {
        this.attributes = attributes;
        return this;
    }

}
