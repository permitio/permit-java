
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * DerivedRoleSettings
 * <p>
 * Settings for a derived role
 * 
 */
@Generated("jsonschema2pojo")
public class DerivedRoleSettings {

    /**
     * No Direct Roles On Object
     * <p>
     * If true, the derived role will not take action if the resource has any direct role
     * 
     */
    @SerializedName("no_direct_roles_on_object")
    @Expose
    public Boolean noDirectRolesOnObject = false;

    public DerivedRoleSettings withNoDirectRolesOnObject(Boolean noDirectRolesOnObject) {
        this.noDirectRolesOnObject = noDirectRolesOnObject;
        return this;
    }

}
