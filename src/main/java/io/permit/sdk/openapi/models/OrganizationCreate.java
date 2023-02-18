
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * OrganizationCreate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class OrganizationCreate {

    /**
     * Key
     * <p>
     * A URL-friendly name of the organization (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the organization.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;
    /**
     * Name
     * <p>
     * The name of the organization, usually it's your company's name.
     * (Required)
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
