
package io.permit.sdk.openapi.models;

import java.util.Date;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ConditionSetRuleRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ConditionSetRuleRead {

    /**
     * Id
     * <p>
     * Unique id of the condition set rule
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public String id;
    /**
     * Key
     * <p>
     * A unique id by which Permit will identify this condition set rule.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;
    /**
     * User Set
     * <p>
     * the userset that is currently granted permissions, i.e: all the users matching this rule are granted the permission on the resourceset
     * (Required)
     * 
     */
    @SerializedName("user_set")
    @Expose
    public String userSet;
    /**
     * Permission
     * <p>
     * a permission that is currently granted to the userset *on* the resourceset.
     * (Required)
     * 
     */
    @SerializedName("permission")
    @Expose
    public String permission;
    /**
     * Resource Set
     * <p>
     * the resourceset that represents the resources that are currently granted for access, i.e: all the resources matching this rule can be accessed by the userset to perform the granted *permission*
     * (Required)
     * 
     */
    @SerializedName("resource_set")
    @Expose
    public String resourceSet;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the condition set rule belongs to.
     * (Required)
     * 
     */
    @SerializedName("organization_id")
    @Expose
    public String organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the condition set rule belongs to.
     * (Required)
     * 
     */
    @SerializedName("project_id")
    @Expose
    public String projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the condition set rule belongs to.
     * (Required)
     * 
     */
    @SerializedName("environment_id")
    @Expose
    public String environmentId;
    /**
     * Created At
     * <p>
     * Date and time when the condition set rule was created (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the condition set rule was last updated/modified (ISO_8601 format).
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
    public ConditionSetRuleRead() {
    }

    /**
     * 
     * @param organizationId
     * @param resourceSet
     * @param createdAt
     * @param environmentId
     * @param permission
     * @param id
     * @param userSet
     * @param projectId
     * @param key
     * @param updatedAt
     */
    public ConditionSetRuleRead(String id, String key, String userSet, String permission, String resourceSet, String organizationId, String projectId, String environmentId, Date createdAt, Date updatedAt) {
        super();
        this.id = id;
        this.key = key;
        this.userSet = userSet;
        this.permission = permission;
        this.resourceSet = resourceSet;
        this.organizationId = organizationId;
        this.projectId = projectId;
        this.environmentId = environmentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ConditionSetRuleRead withId(String id) {
        this.id = id;
        return this;
    }

    public ConditionSetRuleRead withKey(String key) {
        this.key = key;
        return this;
    }

    public ConditionSetRuleRead withUserSet(String userSet) {
        this.userSet = userSet;
        return this;
    }

    public ConditionSetRuleRead withPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public ConditionSetRuleRead withResourceSet(String resourceSet) {
        this.resourceSet = resourceSet;
        return this;
    }

    public ConditionSetRuleRead withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public ConditionSetRuleRead withProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public ConditionSetRuleRead withEnvironmentId(String environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public ConditionSetRuleRead withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ConditionSetRuleRead withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

}
