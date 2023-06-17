
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * DerivedRoleRuleDelete
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class DerivedRoleRuleDelete {

    /**
     * Role
     * <p>
     * the role key that needs to exist on the related resource (from the relation)
     * (Required)
     * 
     */
    @SerializedName("role")
    @Expose
    public String role;
    /**
     * On Resource
     * <p>
     * the resource key that needs to exist on the related role (from the relation)
     * (Required)
     * 
     */
    @SerializedName("on_resource")
    @Expose
    public String onResource;
    /**
     * Linked By Relation
     * <p>
     * the relation key that needs to exist between the resource and the related resource
     * (Required)
     * 
     */
    @SerializedName("linked_by_relation")
    @Expose
    public String linkedByRelation;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DerivedRoleRuleDelete() {
    }

    /**
     * 
     * @param role
     * @param linkedByRelation
     * @param onResource
     */
    public DerivedRoleRuleDelete(String role, String onResource, String linkedByRelation) {
        super();
        this.role = role;
        this.onResource = onResource;
        this.linkedByRelation = linkedByRelation;
    }

    public DerivedRoleRuleDelete withRole(String role) {
        this.role = role;
        return this;
    }

    public DerivedRoleRuleDelete withOnResource(String onResource) {
        this.onResource = onResource;
        return this;
    }

    public DerivedRoleRuleDelete withLinkedByRelation(String linkedByRelation) {
        this.linkedByRelation = linkedByRelation;
        return this;
    }

}
