
package io.permit.sdk.openapi.models;

import java.util.UUID;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ActionBlockRead
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "description",
    "id",
    "key"
})
@Generated("jsonschema2pojo")
public class ActionBlockRead {

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
    /**
     * Id
     * <p>
     * Unique id of the action
     * (Required)
     * 
     */
    @JsonProperty("id")
    @JsonPropertyDescription("Unique id of the action")
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
    public ActionBlockRead() {
    }

    /**
     * 
     * @param id
     */
    public ActionBlockRead(UUID id) {
        super();
        this.id = id;
    }

    public ActionBlockRead withName(String name) {
        this.name = name;
        return this;
    }

    public ActionBlockRead withDescription(String description) {
        this.description = description;
        return this;
    }

    public ActionBlockRead withId(UUID id) {
        this.id = id;
        return this;
    }

    public ActionBlockRead withKey(String key) {
        this.key = key;
        return this;
    }

}
