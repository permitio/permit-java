
package io.permit.sdk.openapi.models;

import java.util.Date;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ResourceRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ResourceRead__1 {

    /**
     * Key
     * <p>
     * A URL-friendly name of the resource (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the resource.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;
    /**
     * Id
     * <p>
     * Unique id of the resource
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public String id;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the resource belongs to.
     * (Required)
     * 
     */
    @SerializedName("organization_id")
    @Expose
    public String organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the resource belongs to.
     * (Required)
     * 
     */
    @SerializedName("project_id")
    @Expose
    public String projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the resource belongs to.
     * (Required)
     * 
     */
    @SerializedName("environment_id")
    @Expose
    public String environmentId;
    /**
     * Created At
     * <p>
     * Date and time when the resource was created (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the resource was last updated/modified (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("updated_at")
    @Expose
    public Date updatedAt;
    /**
     * Name
     * <p>
     * The name of the resource
     * (Required)
     * 
     */
    @SerializedName("name")
    @Expose
    public String name;
    /**
     * Urn
     * <p>
     * The [URN](https://en.wikipedia.org/wiki/Uniform_Resource_Name) (Uniform Resource Name) of the resource
     * 
     */
    @SerializedName("urn")
    @Expose
    public String urn;
    /**
     * Description
     * <p>
     * An optional longer description of what this resource respresents in your system
     * 
     */
    @SerializedName("description")
    @Expose
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
    @SerializedName("actions")
    @Expose
    public Actions__2 actions;
    /**
     * Attributes
     * <p>
     * Attributes that each resource of this type defines, and can be used in your ABAC policies.
     * 
     */
    @SerializedName("attributes")
    @Expose
    public Attributes__6 attributes;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResourceRead__1() {
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
    public ResourceRead__1(String key, String id, String organizationId, String projectId, String environmentId, Date createdAt, Date updatedAt, String name) {
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

    public ResourceRead__1 withKey(String key) {
        this.key = key;
        return this;
    }

    public ResourceRead__1 withId(String id) {
        this.id = id;
        return this;
    }

    public ResourceRead__1 withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public ResourceRead__1 withProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public ResourceRead__1 withEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public ResourceRead__1 withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ResourceRead__1 withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public ResourceRead__1 withName(String name) {
        this.name = name;
        return this;
    }

    public ResourceRead__1 withUrn(String urn) {
        this.urn = urn;
        return this;
    }

    public ResourceRead__1 withDescription(String description) {
        this.description = description;
        return this;
    }

    public ResourceRead__1 withActions(Actions__2 actions) {
        this.actions = actions;
        return this;
    }

    public ResourceRead__1 withAttributes(Attributes__6 attributes) {
        this.attributes = attributes;
        return this;
    }

}
