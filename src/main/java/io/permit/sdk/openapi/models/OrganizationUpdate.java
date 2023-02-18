
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * OrganizationUpdate
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "settings"
})
@Generated("jsonschema2pojo")
public class OrganizationUpdate {

    /**
     * Name
     * <p>
     * The name of the organization, usually it's your company's name.
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("The name of the organization, usually it's your company's name.")
    public String name;
    /**
     * Settings
     * <p>
     * the settings for this project
     * 
     */
    @JsonProperty("settings")
    @JsonPropertyDescription("the settings for this project")
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
