
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * AttributeBlockEditable
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "description"
})
@Generated("jsonschema2pojo")
public class AttributeBlockEditable {

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
     * No args constructor for use in serialization
     * 
     */
    public AttributeBlockEditable() {
    }

    /**
     * 
     * @param type
     */
    public AttributeBlockEditable(Object type) {
        super();
        this.type = type;
    }

    public AttributeBlockEditable withType(Object type) {
        this.type = type;
        return this;
    }

    public AttributeBlockEditable withDescription(String description) {
        this.description = description;
        return this;
    }

}
