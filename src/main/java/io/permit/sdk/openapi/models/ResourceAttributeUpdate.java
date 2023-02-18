
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ResourceAttributeUpdate
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
public class ResourceAttributeUpdate {

    /**
     * The type of the attribute, we currently support: `bool`, `number` (ints, floats), `time` (a timestamp), `string`, and `json`.
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

    public ResourceAttributeUpdate withType(Object type) {
        this.type = type;
        return this;
    }

    public ResourceAttributeUpdate withDescription(String description) {
        this.description = description;
        return this;
    }

}
