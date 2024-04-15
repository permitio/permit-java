
package io.permit.sdk.openapi.models;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * RoleRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class RoleRead {

    /**
     * Name
     * <p>
     * The name of the role
     * (Required)
     * 
     */
    @SerializedName("name")
    @Expose
    public String name;
    /**
     * Description
     * <p>
     * optional description string explaining what this role represents, or what permissions are granted to it.
     * 
     */
    @SerializedName("description")
    @Expose
    public String description;
    /**
     * Permissions
     * <p>
     * list of action keys that define what actions this resource role is permitted to do
     * 
     */
    @SerializedName("permissions")
    @Expose
    public List<String> permissions;
    /**
     * Attributes
     * <p>
     * optional dictionary of key-value pairs that can be used to store arbitrary metadata about this role. This metadata can be used to filter role using query parameters with attr_ prefix, currently supports only 'equals' operator
     * 
     */
    @SerializedName("attributes")
    @Expose
    public HashMap<String, Object> attributes;
    /**
     * Extends
     * <p>
     * list of role keys that define what roles this role extends. In other words: this role will automatically inherit all the permissions of the given roles in this list.
     * 
     */
    @SerializedName("extends")
    @Expose
    public List<String> _extends;
    /**
     * Granted To
     * <p>
     * A derived role defintion block, typically contained whithin a role definition.
     *         The derived role is a role that is derived from the role definition.
     * 
     */
    @SerializedName("granted_to")
    @Expose
    public DerivedRoleBlockRead grantedTo;
    /**
     * Key
     * <p>
     * A URL-friendly name of the role (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the role.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;
    /**
     * Id
     * <p>
     * Unique id of the role
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public String id;
    /**
     * Organization Id
     * <p>
     * Unique id of the organization that the role belongs to.
     * (Required)
     * 
     */
    @SerializedName("organization_id")
    @Expose
    public String organizationId;
    /**
     * Project Id
     * <p>
     * Unique id of the project that the role belongs to.
     * (Required)
     * 
     */
    @SerializedName("project_id")
    @Expose
    public String projectId;
    /**
     * Environment Id
     * <p>
     * Unique id of the environment that the role belongs to.
     * (Required)
     * 
     */
    @SerializedName("environment_id")
    @Expose
    public String environmentId;
    /**
     * Created At
     * <p>
     * Date and time when the role was created (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the role was last updated/modified (ISO_8601 format).
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
    public RoleRead(String name, String key, String id, String organizationId, String projectId, String environmentId, Date createdAt, Date updatedAt) {
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

    public RoleRead withAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public RoleRead withExtends(List<String> _extends) {
        this._extends = _extends;
        return this;
    }

    public RoleRead withGrantedTo(DerivedRoleBlockRead grantedTo) {
        this.grantedTo = grantedTo;
        return this;
    }

    public RoleRead withKey(String key) {
        this.key = key;
        return this;
    }

    public RoleRead withId(String id) {
        this.id = id;
        return this;
    }

    public RoleRead withOrganizationId(String organizationId) {
        this.organizationId = organizationId;
        return this;
    }

    public RoleRead withProjectId(String projectId) {
        this.projectId = projectId;
        return this;
    }

    public RoleRead withEnvironmentId(String environmentId) {
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
