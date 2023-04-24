
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ConditionSetRuleCreate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ConditionSetRuleCreate {

    /**
     * User Set
     * <p>
     * The userset that will be given permission, i.e: all the users matching this rule will be given the specified permission
     * (Required)
     * 
     */
    @SerializedName("user_set")
    @Expose
    public String userSet;
    /**
     * Permission
     * <p>
     * The permission that will be granted to the userset *on* the resourceset. The permission can be either a resource action id, or `{resource_key}:{action_key}`, i.e: the "permission name".
     * (Required)
     * 
     */
    @SerializedName("permission")
    @Expose
    public String permission;
    /**
     * Resource Set
     * <p>
     * The resourceset that represents the resources that are granted for access, i.e: all the resources matching this rule can be accessed by the userset to perform the granted *permission*
     * (Required)
     * 
     */
    @SerializedName("resource_set")
    @Expose
    public String resourceSet;
    /**
     * Is Role
     * <p>
     * if True, will set the condition set rule to the role's autogen user-set.
     * 
     */
    @SerializedName("is_role")
    @Expose
    public Boolean isRole = false;
    /**
     * Is Resource
     * <p>
     * if True, will set the condition set rule to the resource's autogen resource-set.
     * 
     */
    @SerializedName("is_resource")
    @Expose
    public Boolean isResource = false;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ConditionSetRuleCreate() {
    }

    /**
     * 
     * @param resourceSet
     * @param permission
     * @param userSet
     */
    public ConditionSetRuleCreate(String userSet, String permission, String resourceSet) {
        super();
        this.userSet = userSet;
        this.permission = permission;
        this.resourceSet = resourceSet;
    }

    public ConditionSetRuleCreate withUserSet(String userSet) {
        this.userSet = userSet;
        return this;
    }

    public ConditionSetRuleCreate withPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public ConditionSetRuleCreate withResourceSet(String resourceSet) {
        this.resourceSet = resourceSet;
        return this;
    }

    public ConditionSetRuleCreate withIsRole(Boolean isRole) {
        this.isRole = isRole;
        return this;
    }

    public ConditionSetRuleCreate withIsResource(Boolean isResource) {
        this.isResource = isResource;
        return this;
    }

}
