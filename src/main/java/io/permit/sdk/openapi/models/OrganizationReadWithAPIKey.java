
package io.permit.sdk.openapi.models;

import java.util.Date;
import java.util.UUID;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * OrganizationReadWithAPIKey
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "id",
    "created_at",
    "updated_at",
    "name",
    "settings",
    "api_key_id",
    "api_key_secret"
})
@Generated("jsonschema2pojo")
public class OrganizationReadWithAPIKey {

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
     * Id
     * <p>
     * Unique id of the organization
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("Unique id of the organization")
    public UUID id;
    /**
     * Created At
     * <p>
     * Date and time when the organization was created (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("created_at")
    @JsonPropertyDescription("Date and time when the organization was created (ISO_8601 format).")
    public Date createdAt;
    /**
     * Updated At
     * <p>
     * Date and time when the organization was last updated/modified (ISO_8601 format).
     * (Required)
     * 
     */
    @JsonProperty("updated_at")
    @JsonPropertyDescription("Date and time when the organization was last updated/modified (ISO_8601 format).")
    public Date updatedAt;
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
    public Settings__2 settings;
    /**
     * Api Key Id
     * <p>
     * 
     * 
     */
    @JsonProperty("api_key_id")
    public UUID apiKeyId;
    /**
     * Api Key Secret
     * <p>
     * 
     * 
     */
    @JsonProperty("api_key_secret")
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
    public OrganizationReadWithAPIKey(String key, UUID id, Date createdAt, Date updatedAt, String name) {
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

    public OrganizationReadWithAPIKey withId(UUID id) {
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

    public OrganizationReadWithAPIKey withApiKeyId(UUID apiKeyId) {
        this.apiKeyId = apiKeyId;
        return this;
    }

    public OrganizationReadWithAPIKey withApiKeySecret(String apiKeySecret) {
        this.apiKeySecret = apiKeySecret;
        return this;
    }

}
