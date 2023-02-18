
package io.permit.sdk.openapi.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * AddRolePermissions
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "permissions"
})
@Generated("jsonschema2pojo")
public class AddRolePermissions {

    /**
     * Permissions
     * <p>
     * List of permissions to assign to the role. If a permission is already granted to the role it is skipped. Each permission can be either a resource action id, or `{resource_key}:{action_key}`, i.e: the "permission name".
     * (Required)
     * 
     */
    @JsonProperty("permissions")
    @JsonPropertyDescription("List of permissions to assign to the role. If a permission is already granted to the role it is skipped. Each permission can be either a resource action id, or `{resource_key}:{action_key}`, i.e: the \"permission name\".")
    public List<String> permissions = new ArrayList<String>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public AddRolePermissions() {
    }

    /**
     * 
     * @param permissions
     */
    public AddRolePermissions(List<String> permissions) {
        super();
        this.permissions = permissions;
    }

    public AddRolePermissions withPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

}
