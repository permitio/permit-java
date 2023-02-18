
package io.permit.sdk.openapi.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * RemoveRolePermissions
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "permissions"
})
@Generated("jsonschema2pojo")
public class RemoveRolePermissions {

    /**
     * Permissions
     * <p>
     * List of permissions to remove from the role. If a permission is not found it is skipped. Each permission can be either a resource action id, or `{resource_key}:{action_key}`,i.e: the "permission name".
     * (Required)
     * 
     */
    @JsonProperty("permissions")
    @JsonPropertyDescription("List of permissions to remove from the role. If a permission is not found it is skipped. Each permission can be either a resource action id, or `{resource_key}:{action_key}`,i.e: the \"permission name\".")
    public List<String> permissions = new ArrayList<String>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public RemoveRolePermissions() {
    }

    /**
     * 
     * @param permissions
     */
    public RemoveRolePermissions(List<String> permissions) {
        super();
        this.permissions = permissions;
    }

    public RemoveRolePermissions withPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

}
