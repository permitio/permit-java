
package io.permit.sdk.openapi.models;

import java.util.UUID;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * AttributeBlockRead
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "description",
    "id",
    "key"
})
@Generated("jsonschema2pojo")
public class AttributeBlockRead {

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
     * optional description string explaining what data this attribute will store
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("optional description string explaining what data this attribute will store")
    public String description;
    /**
     * Id
     * <p>
     * Unique id of the attribute
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("Unique id of the attribute")
    public UUID id;
    /**
     * Key
     * <p>
     * action key
     * 
     */
    @JsonProperty("key")
    @JsonPropertyDescription("action key")
    public String key;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AttributeBlockRead() {
    }

    /**
     * 
     * @param id
     * @param type
     */
    public AttributeBlockRead(Object type, UUID id) {
        super();
        this.type = type;
        this.id = id;
    }

    public AttributeBlockRead withType(Object type) {
        this.type = type;
        return this;
    }

    public AttributeBlockRead withDescription(String description) {
        this.description = description;
        return this;
    }

    public AttributeBlockRead withId(UUID id) {
        this.id = id;
        return this;
    }

    public AttributeBlockRead withKey(String key) {
        this.key = key;
        return this;
    }

}
