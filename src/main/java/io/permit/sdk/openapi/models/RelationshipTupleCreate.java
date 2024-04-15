
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * RelationshipTupleCreate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class RelationshipTupleCreate {

    /**
     * Subject
     * <p>
     * the resource instance assigned the new relation (accepts either the resource instance id or resource_key:resource_instance_key)
     * (Required)
     * 
     */
    @SerializedName("subject")
    @Expose
    public String subject;
    /**
     * Relation
     * <p>
     * the relation to assign between the subject and object
     * (Required)
     * 
     */
    @SerializedName("relation")
    @Expose
    public String relation;
    /**
     * Object
     * <p>
     * the resource instance on which the new relation is assigned (accepts either the resource instance id or resource_key:resource_instance_key)
     * (Required)
     * 
     */
    @SerializedName("object")
    @Expose
    public String object;
    /**
     * Tenant
     * <p>
     * The tenant the subject and object belong to, if the resource instances don't exist yet, the tenant is required to create them. otherwise it is ignored
     * 
     */
    @SerializedName("tenant")
    @Expose
    public String tenant;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RelationshipTupleCreate() {
    }

    /**
     * 
     * @param subject
     * @param relation
     * @param object
     */
    public RelationshipTupleCreate(String subject, String relation, String object) {
        super();
        this.subject = subject;
        this.relation = relation;
        this.object = object;
    }

    public RelationshipTupleCreate withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public RelationshipTupleCreate withRelation(String relation) {
        this.relation = relation;
        return this;
    }

    public RelationshipTupleCreate withObject(String object) {
        this.object = object;
        return this;
    }

    public RelationshipTupleCreate withTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

}
