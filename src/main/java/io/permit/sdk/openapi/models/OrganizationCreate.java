
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * OrganizationCreate
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "name",
    "settings"
})
@Generated("jsonschema2pojo")
public class OrganizationCreate {

    /**
     * Key
     * <p>
     * A URL-friendly name of the organization (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the organization.
     * (Required)
     * 
     */
    @JsonProperty("key")
    @JsonPropertyDescription("A URL-friendly name of the organization (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the organization.")
    public String key;
    /**
     * Name
     * <p>
     * The name of the organization, usually it's your company's name.
     * (Required)
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
    public Settings settings;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OrganizationCreate() {
    }

    /**
     * 
     * @param name
     * @param key
     */
    public OrganizationCreate(String key, String name) {
        super();
        this.key = key;
        this.name = name;
    }

    public OrganizationCreate withKey(String key) {
        this.key = key;
        return this;
    }

    public OrganizationCreate withName(String name) {
        this.name = name;
        return this;
    }

    public OrganizationCreate withSettings(Settings settings) {
        this.settings = settings;
        return this;
    }

}
