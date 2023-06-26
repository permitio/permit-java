
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * RelationshipTupleObj
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class RelationshipTupleObj {

    /**
     * Subject Str
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("subject_str")
    @Expose
    public String subjectStr;
    /**
     * Relation Str
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("relation_str")
    @Expose
    public String relationStr;
    /**
     * Object Str
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("object_str")
    @Expose
    public String objectStr;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RelationshipTupleObj() {
    }

    /**
     * 
     * @param relationStr
     * @param objectStr
     * @param subjectStr
     */
    public RelationshipTupleObj(String subjectStr, String relationStr, String objectStr) {
        super();
        this.subjectStr = subjectStr;
        this.relationStr = relationStr;
        this.objectStr = objectStr;
    }

    public RelationshipTupleObj withSubjectStr(String subjectStr) {
        this.subjectStr = subjectStr;
        return this;
    }

    public RelationshipTupleObj withRelationStr(String relationStr) {
        this.relationStr = relationStr;
        return this;
    }

    public RelationshipTupleObj withObjectStr(String objectStr) {
        this.objectStr = objectStr;
        return this;
    }

}
