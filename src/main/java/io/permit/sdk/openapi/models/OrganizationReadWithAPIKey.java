
package io.permit.sdk.openapi.models;

import java.util.Date;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * OrganizationReadWithAPIKey
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class OrganizationReadWithAPIKey {

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
     * Id
     * <p>
     * Unique id of the organization
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public String id;
    /**
     * Created At
     * <p>
     * Date and time when the organization was created (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the organization was last updated/modified (ISO_8601 format).
     * (Required)
     * 
     */
    @SerializedName("updated_at")
    @Expose
    public Date updatedAt;
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
    public Settings__2 settings;
    /**
     * Api Key Id
     * <p>
     * 
     * 
     */
    @SerializedName("api_key_id")
    @Expose
    public String apiKeyId;
    /**
     * Api Key Secret
     * <p>
     * 
     * 
     */
    @SerializedName("api_key_secret")
    @Expose
    public String apiKeySecret;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OrganizationReadWithAPIKey() {
    }

    /**
     * 
     * @param createdAt
     * @param name
     * @param id
     * @param key
     * @param updatedAt
     */
    public OrganizationReadWithAPIKey(String key, String id, Date createdAt, Date updatedAt, String name) {
        super();
        this.key = key;
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
    }

    public OrganizationReadWithAPIKey withKey(String key) {
        this.key = key;
        return this;
    }

    public OrganizationReadWithAPIKey withId(String id) {
        this.id = id;
        return this;
    }

    public OrganizationReadWithAPIKey withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public OrganizationReadWithAPIKey withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public OrganizationReadWithAPIKey withName(String name) {
        this.name = name;
        return this;
    }

    public OrganizationReadWithAPIKey withSettings(Settings__2 settings) {
        this.settings = settings;
        return this;
    }

    public OrganizationReadWithAPIKey withApiKeyId(String apiKeyId) {
        this.apiKeyId = apiKeyId;
        return this;
    }

    public OrganizationReadWithAPIKey withApiKeySecret(String apiKeySecret) {
        this.apiKeySecret = apiKeySecret;
        return this;
    }

}
