
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ResourceAttributeCreate
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "type",
    "description"
})
@Generated("jsonschema2pojo")
public class ResourceAttributeCreate {

    /**
     * Key
     * <p>
     * A URL-friendly name of the attribute (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the attribute.
     * (Required)
     * 
     */
    @JsonProperty("key")
    @JsonPropertyDescription("A URL-friendly name of the attribute (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the attribute.")
    public String key;
    /**
     * The type of the attribute, we currently support: `bool`, `number` (ints, floats), `time` (a timestamp), `string`, and `json`.
     * (Required)
     * 
     */
    @JsonProperty("type")
    @JsonPropertyDescription("The type of the attribute, we currently support: `bool`, `number` (ints, floats), `time` (a timestamp), `string`, and `json`.")
    public Object type;
    /**
     * Description
     * <p>
     * An optional longer description of what this attribute respresents in your system
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("An optional longer description of what this attribute respresents in your system")
    public String description;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResourceAttributeCreate() {
    }

    /**
     * 
     * @param type
     * @param key
     */
    public ResourceAttributeCreate(String key, Object type) {
        super();
        this.key = key;
        this.type = type;
    }

    public ResourceAttributeCreate withKey(String key) {
        this.key = key;
        return this;
    }

    public ResourceAttributeCreate withType(Object type) {
        this.type = type;
        return this;
    }

    public ResourceAttributeCreate withDescription(String description) {
        this.description = description;
        return this;
    }

}
