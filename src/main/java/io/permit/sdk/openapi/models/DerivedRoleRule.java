
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * DerivedRoleRule
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class DerivedRoleRule {

    /**
     * Relation
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("relation")
    @Expose
    public String relation;
    /**
     * Related Resource
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("related_resource")
    @Expose
    public String relatedResource;
    /**
     * Related Role
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("related_role")
    @Expose
    public String relatedRole;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DerivedRoleRule() {
    }

    /**
     * 
     * @param relatedResource
     * @param relatedRole
     * @param relation
     */
    public DerivedRoleRule(String relation, String relatedResource, String relatedRole) {
        super();
        this.relation = relation;
        this.relatedResource = relatedResource;
        this.relatedRole = relatedRole;
    }

    public DerivedRoleRule withRelation(String relation) {
        this.relation = relation;
        return this;
    }

    public DerivedRoleRule withRelatedResource(String relatedResource) {
        this.relatedResource = relatedResource;
        return this;
    }

    public DerivedRoleRule withRelatedRole(String relatedRole) {
        this.relatedRole = relatedRole;
        return this;
    }

}
