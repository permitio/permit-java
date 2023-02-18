
package io.permit.sdk.openapi.models;

import java.util.Date;
import java.util.UUID;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * OrganizationRead
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
    "settings"
})
@Generated("jsonschema2pojo")
public class OrganizationRead {

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
    public Settings__1 settings;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OrganizationRead() {
    }

    /**
     * 
     * @param createdAt
     * @param name
     * @param id
     * @param key
     * @param updatedAt
     */
    public OrganizationRead(String key, UUID id, Date createdAt, Date updatedAt, String name) {
        super();
        this.key = key;
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
    }

    public OrganizationRead withKey(String key) {
        this.key = key;
        return this;
    }

    public OrganizationRead withId(UUID id) {
        this.id = id;
        return this;
    }

    public OrganizationRead withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public OrganizationRead withUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public OrganizationRead withName(String name) {
        this.name = name;
        return this;
    }

    public OrganizationRead withSettings(Settings__1 settings) {
        this.settings = settings;
        return this;
    }

}
