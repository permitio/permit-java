
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ConditionSetRuleRemove
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "user_set",
    "permission",
    "resource_set",
    "is_role",
    "is_resource"
})
@Generated("jsonschema2pojo")
public class ConditionSetRuleRemove {

    /**
     * User Set
     * <p>
     * The userset that will be unassigned these permission, i.e: all the users matching this rule will lose the specified permission
     * (Required)
     * 
     */
    @JsonProperty("user_set")
    @JsonPropertyDescription("The userset that will be unassigned these permission, i.e: all the users matching this rule will lose the specified permission")
    public String userSet;
    /**
     * Permission
     * <p>
     * The permission that will be removed from the userset *on* the resourceset. The permission can be either a resource action id, or `{resource_key}:{action_key}`, i.e: the "permission name".
     * (Required)
     * 
     */
    @JsonProperty("permission")
    @JsonPropertyDescription("The permission that will be removed from the userset *on* the resourceset. The permission can be either a resource action id, or `{resource_key}:{action_key}`, i.e: the \"permission name\".")
    public String permission;
    /**
     * Resource Set
     * <p>
     * The resourceset that represents the resources that are no longer granted for access, i.e: all the resources matching this rule can no longer be accessed by the userset, and will be revoked the specified *permission*
     * (Required)
     * 
     */
    @JsonProperty("resource_set")
    @JsonPropertyDescription("The resourceset that represents the resources that are no longer granted for access, i.e: all the resources matching this rule can no longer be accessed by the userset, and will be revoked the specified *permission*")
    public String resourceSet;
    /**
     * Is Role
     * <p>
     * if True, will set the condition set rule to the role's autogen user-set.
     * 
     */
    @JsonProperty("is_role")
    @JsonPropertyDescription("if True, will set the condition set rule to the role's autogen user-set.")
    public Boolean isRole = false;
    /**
     * Is Resource
     * <p>
     * if True, will set the condition set rule to the resource's autogen resource-set.
     * 
     */
    @JsonProperty("is_resource")
    @JsonPropertyDescription("if True, will set the condition set rule to the resource's autogen resource-set.")
    public Boolean isResource = false;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ConditionSetRuleRemove() {
    }

    /**
     * 
     * @param resourceSet
     * @param permission
     * @param userSet
     */
    public ConditionSetRuleRemove(String userSet, String permission, String resourceSet) {
        super();
        this.userSet = userSet;
        this.permission = permission;
        this.resourceSet = resourceSet;
    }

    public ConditionSetRuleRemove withUserSet(String userSet) {
        this.userSet = userSet;
        return this;
    }

    public ConditionSetRuleRemove withPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public ConditionSetRuleRemove withResourceSet(String resourceSet) {
        this.resourceSet = resourceSet;
        return this;
    }

    public ConditionSetRuleRemove withIsRole(Boolean isRole) {
        this.isRole = isRole;
        return this;
    }

    public ConditionSetRuleRemove withIsResource(Boolean isResource) {
        this.isResource = isResource;
        return this;
    }

}
