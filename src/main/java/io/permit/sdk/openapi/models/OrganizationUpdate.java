
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * OrganizationUpdate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class OrganizationUpdate {

    /**
     * Name
     * <p>
     * The name of the organization, usually it's your company's name.
     * 
     */
    @SerializedName("name")
    @Expose
    public String name;
    /**
     * Settings
     * <p>
     * the settings for this project
     * 
     */
    @SerializedName("settings")
    @Expose
    public Settings__3 settings;

    public OrganizationUpdate withName(String name) {
        this.name = name;
        return this;
    }

    public OrganizationUpdate withSettings(Settings__3 settings) {
        this.settings = settings;
        return this;
    }

}
