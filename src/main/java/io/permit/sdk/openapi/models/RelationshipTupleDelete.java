
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * RelationshipTupleDelete
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class RelationshipTupleDelete {

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
     * No args constructor for use in serialization
     * 
     */
    public RelationshipTupleDelete() {
    }

    /**
     * 
     * @param subject
     * @param relation
     * @param object
     */
    public RelationshipTupleDelete(String subject, String relation, String object) {
        super();
        this.subject = subject;
        this.relation = relation;
        this.object = object;
    }

    public RelationshipTupleDelete withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public RelationshipTupleDelete withRelation(String relation) {
        this.relation = relation;
        return this;
    }

    public RelationshipTupleDelete withObject(String object) {
        this.object = object;
        return this;
    }

}
