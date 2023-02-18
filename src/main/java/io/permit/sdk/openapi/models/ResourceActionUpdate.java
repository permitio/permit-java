
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ResourceActionUpdate
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
public class ResourceActionUpdate {

    /**
     * Name
     * <p>
     * The name of the action
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

    public ResourceActionUpdate withName(String name) {
        this.name = name;
        return this;
    }

    public ResourceActionUpdate withDescription(String description) {
        this.description = description;
        return this;
    }

}
