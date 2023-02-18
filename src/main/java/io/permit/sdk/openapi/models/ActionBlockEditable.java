
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ActionBlockEditable
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "description"
})
@Generated("jsonschema2pojo")
public class ActionBlockEditable {

    /**
     * Name
     * <p>
     * a more descriptive name for the action
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("a more descriptive name for the action")
    public String name;
    /**
     * Description
     * <p>
     * optional description string explaining what this action represents in your system
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("optional description string explaining what this action represents in your system")
    public String description;

    public ActionBlockEditable withName(String name) {
        this.name = name;
        return this;
    }

    public ActionBlockEditable withDescription(String description) {
        this.description = description;
        return this;
    }

}
