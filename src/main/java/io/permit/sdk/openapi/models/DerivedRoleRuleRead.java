
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * DerivedRoleRuleRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class DerivedRoleRuleRead {

    /**
     * Role Id
     * <p>
     * the role id that needs to exist on the related resource (from the relation)
     * (Required)
     * 
     */
    @SerializedName("role_id")
    @Expose
    public String roleId;
    /**
     * Resource Id
     * <p>
     * the resource id that needs to exist on the related role (from the relation)
     * (Required)
     * 
     */
    @SerializedName("resource_id")
    @Expose
    public String resourceId;
    /**
     * Relation Id
     * <p>
     * the relation id that needs to exist between the resource and the related resource
     * (Required)
     * 
     */
    @SerializedName("relation_id")
    @Expose
    public String relationId;
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
    public DerivedRoleRuleRead() {
    }

    /**
     * 
     * @param resourceId
     * @param role
     * @param roleId
     * @param linkedByRelation
     * @param onResource
     * @param relationId
     */
    public DerivedRoleRuleRead(String roleId, String resourceId, String relationId, String role, String onResource, String linkedByRelation) {
        super();
        this.roleId = roleId;
        this.resourceId = resourceId;
        this.relationId = relationId;
        this.role = role;
        this.onResource = onResource;
        this.linkedByRelation = linkedByRelation;
    }

    public DerivedRoleRuleRead withRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public DerivedRoleRuleRead withResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public DerivedRoleRuleRead withRelationId(String relationId) {
        this.relationId = relationId;
        return this;
    }

    public DerivedRoleRuleRead withRole(String role) {
        this.role = role;
        return this;
    }

    public DerivedRoleRuleRead withOnResource(String onResource) {
        this.onResource = onResource;
        return this;
    }

    public DerivedRoleRuleRead withLinkedByRelation(String linkedByRelation) {
        this.linkedByRelation = linkedByRelation;
        return this;
    }

}
