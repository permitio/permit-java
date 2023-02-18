
package io.permit.sdk.openapi.models;

import java.util.Date;
import java.util.UUID;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ConditionSetRuleRead
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "key",
    "user_set",
    "permission",
    "resource_set",
    "organization_id",
    "project_id",
    "environment_id",
    "created_at",
    "updated_at"
})
@Generated("jsonschema2pojo")
public class ConditionSetRuleRead {

    /**
     * Id
     * <p>
     * Unique id of the condition set rule
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("Unique id of the condition set rule")
    public UUID id;
    /**
     * Key
     * <p>
     * A unique id by which Permit will identify this condition set rule.
     * (Required)
     * 
     */
    @JsonProperty("key")
    @JsonPropertyDescription("A unique id by which Permit will identify this condition set rule.")
    public String key;
    /**
     * User Set
     * <p>
     * the userset that is currently granted permissions, i.e: all the users matching this rule are granted the permission on the resourceset
     * (Required)
     * 
     */
    @JsonProperty("user_set")
    @JsonPropertyDescription("the userset that is currently granted permissions, i.e: all the users matching this rule are granted the permission on the resourceset")
    public String userSet;
    /**
     * Permission
     * <p>
     * a permission that is currently granted to the userset *on* the resourceset.
     * (Required)
     * 
     */
    @JsonProperty("permission")
    @JsonPropertyDescription("a permission that is currently granted to the userset *on* the resourceset.")
    public String permission;
    /**
     * Resource Set
     * <p>
     * the resourceset that represents the resources that are currently granted for access, i.e: all the resources matching this rule can be accessed by the userset to perform the granted *permission*
     * (Required)
     * 
     */
    @JsonProperty("resource_set")
    @JsonPropertyDescription("the resourceset that represents the resources that are currently granted for access, i.e: all the resources matching this rule can be accessed by the userset to perform the granted *permission*")
    public String resourceSet;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the condition set rule belongs to.
     * (Required)
     * 
     */
    @JsonProperty("organization_id")
    @JsonPropertyDescription("Unique id of the organization that the condition set rule belongs to.")
    public UUID organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the condition set rule belongs to.
     * (Required)
     * 
     */
    @JsonProperty("project_id")
    @JsonPropertyDescription("Unique id of the project that the condition set rule belongs to.")
    public UUID projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the condition set rule belongs to.
     * (Required)
     * 
     */
    @JsonProperty("environment_id")
    @JsonPropertyDescription("Unique id of the environment that the condition set rule belongs to.")
    public UUID environmentId;
    /**
     * Created At
     * <p>
     * Date and time when the condition set rule was created (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("created_at")
    @JsonPropertyDescription("Date and time when the condition set rule was created (ISO_8601 format).")
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the condition set rule was last updated/modified (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("updated_at")
    @JsonPropertyDescription("Date and time when the condition set rule was last updated/modified (ISO_8601 format).")
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
    public ConditionSetRuleRead(UUID id, String key, String userSet, String permission, String resourceSet, UUID organizationId, UUID projectId, UUID environmentId, Date createdAt, Date updatedAt) {
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

    public ConditionSetRuleRead withId(UUID id) {
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

    public ConditionSetRuleRead withOrganizationId(UUID organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public ConditionSetRuleRead withProjectId(UUID projectId) {
        this.projectId = projectId;
        return this;
    }

    public ConditionSetRuleRead withEnvironmentId(UUID environmentId) {
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
