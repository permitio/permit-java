
package io.permit.sdk.openapi.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * RoleRead
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "description",
    "permissions",
    "extends",
    "key",
    "id",
    "organization_id",
    "project_id",
    "environment_id",
    "created_at",
    "updated_at"
})
@Generated("jsonschema2pojo")
public class RoleRead {

    /**
     * Name
     * <p>
     * The name of the role
     * (Required)
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("The name of the role")
    public String name;
    /**
     * Description
     * <p>
     * optional description string explaining what this role represents, or what permissions are granted to it.
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("optional description string explaining what this role represents, or what permissions are granted to it.")
    public String description;
    /**
     * Permissions
     * <p>
     * list of action keys that define what actions this resource role is permitted to do
     * 
     */
    @JsonProperty("permissions")
    @JsonPropertyDescription("list of action keys that define what actions this resource role is permitted to do")
    public List<String> permissions = new ArrayList<String>();
    /**
     * Extends
     * <p>
     * list of role keys that define what roles this role extends. In other words: this role will automatically inherit all the permissions of the given roles in this list.
     * 
     */
    @JsonProperty("extends")
    @JsonPropertyDescription("list of role keys that define what roles this role extends. In other words: this role will automatically inherit all the permissions of the given roles in this list.")
    public List<String> _extends = new ArrayList<String>();
    /**
     * Key
     * <p>
     * A URL-friendly name of the role (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the role.
     * (Required)
     * 
     */
    @JsonProperty("key")
    @JsonPropertyDescription("A URL-friendly name of the role (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the role.")
    public String key;
    /**
     * Id
     * <p>
     * Unique id of the role
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("Unique id of the role")
    public UUID id;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the role belongs to.
     * (Required)
     * 
     */
    @JsonProperty("organization_id")
    @JsonPropertyDescription("Unique id of the organization that the role belongs to.")
    public UUID organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the role belongs to.
     * (Required)
     * 
     */
    @JsonProperty("project_id")
    @JsonPropertyDescription("Unique id of the project that the role belongs to.")
    public UUID projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the role belongs to.
     * (Required)
     * 
     */
    @JsonProperty("environment_id")
    @JsonPropertyDescription("Unique id of the environment that the role belongs to.")
    public UUID environmentId;
    /**
     * Created At
     * <p>
     * Date and time when the role was created (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("created_at")
    @JsonPropertyDescription("Date and time when the role was created (ISO_8601 format).")
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the role was last updated/modified (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("updated_at")
    @JsonPropertyDescription("Date and time when the role was last updated/modified (ISO_8601 format).")
    public Date updatedAt;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RoleRead() {
    }

    /**
     * 
     * @param organizationId
     * @param createdAt
     * @param environmentId
     * @param name
     * @param id
     * @param projectId
     * @param key
     * @param updatedAt
     */
    public RoleRead(String name, String key, UUID id, UUID organizationId, UUID projectId, UUID environmentId, Date createdAt, Date updatedAt) {
        super();
        this.name = name;
        this.key = key;
        this.id = id;
        this.organizationId = organizationId;
        this.projectId = projectId;
        this.environmentId = environmentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public RoleRead withName(String name) {
        this.name = name;
        return this;
    }

    public RoleRead withDescription(String description) {
        this.description = description;
        return this;
    }

    public RoleRead withPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

    public RoleRead withExtends(List<String> _extends) {
        this._extends = _extends;
        return this;
    }

    public RoleRead withKey(String key) {
        this.key = key;
        return this;
    }

    public RoleRead withId(UUID id) {
        this.id = id;
        return this;
    }

    public RoleRead withOrganizationId(UUID organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public RoleRead withProjectId(UUID projectId) {
        this.projectId = projectId;
        return this;
    }

    public RoleRead withEnvironmentId(UUID environmentId) {
        this.environmentId = environmentId;
        return this;
    }

    public RoleRead withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public RoleRead withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

}
