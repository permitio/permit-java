
package io.permit.sdk.openapi.models;

import java.util.Date;
import java.util.HashMap;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * TenantRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class TenantRead {

    /**
     * Key
     * <p>
     * A unique id by which Permit will identify the tenant. The tenant key must be url-friendly (slugified).
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public java.lang.String key;
    /**
     * Id
     * <p>
     * Unique id of the tenant
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public java.lang.String id;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the tenant belongs to.
     * (Required)
     * 
     */
    @SerializedName("organization_id")
    @Expose
    public java.lang.String organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the tenant belongs to.
     * (Required)
     * 
     */
    @SerializedName("project_id")
    @Expose
    public java.lang.String projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the tenant belongs to.
     * (Required)
     * 
     */
    @SerializedName("environment_id")
    @Expose
    public java.lang.String environmentId;
    /**
     * Created At
     * <p>
     * Date and time when the tenant was created (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the tenant was last updated/modified (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("updated_at")
    @Expose
    public Date updatedAt;
    /**
     * Last Action At
     * <p>
     * Date and time when the tenant was last active (ISO_8601 format). In other words, this is the last time a permission check was done on a resource belonging to this tenant.
     * (Required)
     * 
     */
    @SerializedName("last_action_at")
    @Expose
    public Date lastActionAt;
    /**
     * Name
     * <p>
     * A descriptive name for the tenant
     * (Required)
     * 
     */
    @SerializedName("name")
    @Expose
    public java.lang.String name;
    /**
     * Description
     * <p>
     * an optional longer description of the tenant
     * 
     */
    @SerializedName("description")
    @Expose
    public java.lang.String description;
    /**
     * Attributes
     * <p>
     * Arbitraty tenant attributes that will be used to enforce attribute-based access control policies.
     * 
     */
    @SerializedName("attributes")
    @Expose
    public HashMap<String, Object> attributes;

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
    public TenantRead(java.lang.String key, java.lang.String id, java.lang.String organizationId, java.lang.String projectId, java.lang.String environmentId, Date createdAt, Date updatedAt, Date lastActionAt, java.lang.String name) {
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

    public TenantRead withKey(java.lang.String key) {
        this.key = key;
        return this;
    }

    public TenantRead withId(java.lang.String id) {
        this.id = id;
        return this;
    }

    public TenantRead withOrganizationId(java.lang.String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public TenantRead withProjectId(java.lang.String projectId) {
        this.projectId = projectId;
        return this;
    }

    public TenantRead withEnvironmentId(java.lang.String environmentId) {
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

    public TenantRead withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    public TenantRead withDescription(java.lang.String description) {
        this.description = description;
        return this;
    }

    public TenantRead withAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

}
