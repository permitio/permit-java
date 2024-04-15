
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * RelationCreate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class RelationCreate {

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
     * No args constructor for use in serialization
     * 
     */
    public RelationCreate() {
    }

    /**
     * 
     * @param subjectResource
     * @param name
     * @param key
     */
    public RelationCreate(String key, String name, String subjectResource) {
        super();
        this.key = key;
        this.name = name;
        this.subjectResource = subjectResource;
    }

    public RelationCreate withKey(String key) {
        this.key = key;
        return this;
    }

    public RelationCreate withName(String name) {
        this.name = name;
        return this;
    }

    public RelationCreate withDescription(String description) {
        this.description = description;
        return this;
    }

    public RelationCreate withSubjectResource(String subjectResource) {
        this.subjectResource = subjectResource;
        return this;
    }

}
