
package io.permit.sdk.openapi.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * RoleUpdate
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "description",
    "permissions",
    "extends"
})
@Generated("jsonschema2pojo")
public class RoleUpdate {

    /**
     * Name
     * <p>
     * The name of the role
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

    public RoleUpdate withName(String name) {
        this.name = name;
        return this;
    }

    public RoleUpdate withDescription(String description) {
        this.description = description;
        return this;
    }

    public RoleUpdate withPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

    public RoleUpdate withExtends(List<String> _extends) {
        this._extends = _extends;
        return this;
    }

}
