
package io.permit.sdk.openapi.models;

import java.util.Date;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * RelationshipTupleRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class RelationshipTupleRead {

    /**
     * Id
     * <p>
     * Unique id of the relationship tuple
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public String id;
    /**
     * Subject
     * <p>
     * resource_key:resource_instance_key of the subject
     * (Required)
     * 
     */
    @SerializedName("subject")
    @Expose
    public String subject;
    /**
     * Relation
     * <p>
     * key of the assigned relation
     * (Required)
     * 
     */
    @SerializedName("relation")
    @Expose
    public String relation;
    /**
     * Object
     * <p>
     * resource_key:resource_instance_key of the object
     * (Required)
     * 
     */
    @SerializedName("object")
    @Expose
    public String object;
    /**
     * Tenant
     * <p>
     * The tenant the relationship tuple is associated with
     * (Required)
     * 
     */
    @SerializedName("tenant")
    @Expose
    public String tenant;
    /**
     * Subject Id
     * <p>
     * Unique id of the subject
     * (Required)
     * 
     */
    @SerializedName("subject_id")
    @Expose
    public String subjectId;
    /**
     * Relation Id
     * <p>
     * Unique id of the relation
     * (Required)
     * 
     */
    @SerializedName("relation_id")
    @Expose
    public String relationId;
    /**
     * Object Id
     * <p>
     * Unique id of the object
     * (Required)
     * 
     */
    @SerializedName("object_id")
    @Expose
    public String objectId;
    /**
     * Tenant Id
     * <p>
     * Unique id of the tenant
     * (Required)
     * 
     */
    @SerializedName("tenant_id")
    @Expose
    public String tenantId;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the relationship tuple belongs to.
     * (Required)
     * 
     */
    @SerializedName("organization_id")
    @Expose
    public String organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the relationship tuple belongs to.
     * (Required)
     * 
     */
    @SerializedName("project_id")
    @Expose
    public String projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the relationship tuple belongs to.
     * (Required)
     * 
     */
    @SerializedName("environment_id")
    @Expose
    public String environmentId;
    /**
     * Created At
     * <p>
     * Date and time when the relationship tuple was created (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the relationship tuple was created (ISO_8601 format).
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
    public RelationshipTupleRead() {
    }

    /**
     * 
     * @param subject
     * @param relationId
     * @param subjectId
     * @param relation
     * @param organizationId
     * @param createdAt
     * @param environmentId
     * @param tenantId
     * @param id
     * @param projectId
     * @param tenant
     * @param objectId
     * @param object
     * @param updatedAt
     */
    public RelationshipTupleRead(String id, String subject, String relation, String object, String tenant, String subjectId, String relationId, String objectId, String tenantId, String organizationId, String projectId, String environmentId, Date createdAt, Date updatedAt) {
        super();
        this.id = id;
        this.subject = subject;
        this.relation = relation;
        this.object = object;
        this.tenant = tenant;
        this.subjectId = subjectId;
        this.relationId = relationId;
        this.objectId = objectId;
        this.tenantId = tenantId;
        this.organizationId = organizationId;
        this.projectId = projectId;
        this.environmentId = environmentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public RelationshipTupleRead withId(String id) {
        this.id = id;
        return this;
    }

    public RelationshipTupleRead withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public RelationshipTupleRead withRelation(String relation) {
        this.relation = relation;
        return this;
    }

    public RelationshipTupleRead withObject(String object) {
        this.object = object;
        return this;
    }

    public RelationshipTupleRead withTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public RelationshipTupleRead withSubjectId(String subjectId) {
        this.subjectId = subjectId;
        return this;
    }

    public RelationshipTupleRead withRelationId(String relationId) {
        this.relationId = relationId;
        return this;
    }

    public RelationshipTupleRead withObjectId(String objectId) {
        this.objectId = objectId;
        return this;
    }

    public RelationshipTupleRead withTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public RelationshipTupleRead withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public RelationshipTupleRead withProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public RelationshipTupleRead withEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public RelationshipTupleRead withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public RelationshipTupleRead withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

}
