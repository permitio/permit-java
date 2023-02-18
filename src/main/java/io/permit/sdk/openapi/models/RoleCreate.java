
package io.permit.sdk.openapi.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * RoleCreate
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "name",
    "description",
    "permissions",
    "extends"
})
@Generated("jsonschema2pojo")
public class RoleCreate {

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
     * No args constructor for use in serialization
     * 
     */
    public RoleCreate() {
    }

    /**
     * 
     * @param name
     * @param key
     */
    public RoleCreate(String key, String name) {
        super();
        this.key = key;
        this.name = name;
    }

    public RoleCreate withKey(String key) {
        this.key = key;
        return this;
    }

    public RoleCreate withName(String name) {
        this.name = name;
        return this;
    }

    public RoleCreate withDescription(String description) {
        this.description = description;
        return this;
    }

    public RoleCreate withPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

    public RoleCreate withExtends(List<String> _extends) {
        this._extends = _extends;
        return this;
    }

}
