
package io.permit.sdk.openapi.models;

import java.util.Date;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * RelationRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class RelationRead {

    /**
     * Description
     * <p>
     * An optional longer description of what this relation respresents in your system
     * 
     */
    @SerializedName("description")
    @Expose
    public String description;
    /**
     * Subject Resource
     * <p>
     * The subject resource ID or key
     * (Required)
     * 
     */
    @SerializedName("subject_resource")
    @Expose
    public String subjectResource;
    /**
     * Key
     * <p>
     * A URL-friendly name of the relation (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the relation.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;
    /**
     * Name
     * <p>
     * The name of the relation
     * (Required)
     * 
     */
    @SerializedName("name")
    @Expose
    public String name;
    /**
     * Id
     * <p>
     * Unique id of the relation
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public String id;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the relation belongs to.
     * (Required)
     * 
     */
    @SerializedName("organization_id")
    @Expose
    public String organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the relation belongs to.
     * (Required)
     * 
     */
    @SerializedName("project_id")
    @Expose
    public String projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the relation belongs to.
     * (Required)
     * 
     */
    @SerializedName("environment_id")
    @Expose
    public String environmentId;
    /**
     * Created At
     * <p>
     * Date and time when the relation was created (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the relation was last updated/modified (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("updated_at")
    @Expose
    public Date updatedAt;
    /**
     * Object Resource Id
     * <p>
     * The object resource id
     * (Required)
     * 
     */
    @SerializedName("object_resource_id")
    @Expose
    public String objectResourceId;
    /**
     * Object Resource
     * <p>
     * The object resource key
     * (Required)
     * 
     */
    @SerializedName("object_resource")
    @Expose
    public String objectResource;
    /**
     * Subject Resource Id
     * <p>
     * The subject resource id
     * (Required)
     * 
     */
    @SerializedName("subject_resource_id")
    @Expose
    public String subjectResourceId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RelationRead() {
    }

    /**
     * 
     * @param organizationId
     * @param createdAt
     * @param environmentId
     * @param subjectResource
     * @param objectResource
     * @param name
     * @param subjectResourceId
     * @param id
     * @param projectId
     * @param objectResourceId
     * @param key
     * @param updatedAt
     */
    public RelationRead(String subjectResource, String key, String name, String id, String organizationId, String projectId, String environmentId, Date createdAt, Date updatedAt, String objectResourceId, String objectResource, String subjectResourceId) {
        super();
        this.subjectResource = subjectResource;
        this.key = key;
        this.name = name;
        this.id = id;
        this.organizationId = organizationId;
        this.projectId = projectId;
        this.environmentId = environmentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.objectResourceId = objectResourceId;
        this.objectResource = objectResource;
        this.subjectResourceId = subjectResourceId;
    }

    public RelationRead withDescription(String description) {
        this.description = description;
        return this;
    }

    public RelationRead withSubjectResource(String subjectResource) {
        this.subjectResource = subjectResource;
        return this;
    }

    public RelationRead withKey(String key) {
        this.key = key;
        return this;
    }

    public RelationRead withName(String name) {
        this.name = name;
        return this;
    }

    public RelationRead withId(String id) {
        this.id = id;
        return this;
    }

    public RelationRead withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public RelationRead withProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public RelationRead withEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public RelationRead withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public RelationRead withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public RelationRead withObjectResourceId(String objectResourceId) {
        this.objectResourceId = objectResourceId;
        return this;
    }

    public RelationRead withObjectResource(String objectResource) {
        this.objectResource = objectResource;
        return this;
    }

    public RelationRead withSubjectResourceId(String subjectResourceId) {
        this.subjectResourceId = subjectResourceId;
        return this;
    }

}
