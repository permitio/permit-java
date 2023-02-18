
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ResourceActionCreate
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "name",
    "description"
})
@Generated("jsonschema2pojo")
public class ResourceActionCreate {

    /**
     * Key
     * <p>
     * A URL-friendly name of the action (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the action.
     * (Required)
     * 
     */
    @JsonProperty("key")
    @JsonPropertyDescription("A URL-friendly name of the action (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the action.")
    public String key;
    /**
     * Name
     * <p>
     * The name of the action
     * (Required)
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("The name of the action")
    public String name;
    /**
     * Description
     * <p>
     * An optional longer description of what this action respresents in your system
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("An optional longer description of what this action respresents in your system")
    public String description;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResourceActionCreate() {
    }

    /**
     * 
     * @param name
     * @param key
     */
    public ResourceActionCreate(String key, String name) {
        super();
        this.key = key;
        this.name = name;
    }

    public ResourceActionCreate withKey(String key) {
        this.key = key;
        return this;
    }

    public ResourceActionCreate withName(String name) {
        this.name = name;
        return this;
    }

    public ResourceActionCreate withDescription(String description) {
        this.description = description;
        return this;
    }

}
