
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ConditionSetUpdate
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "description",
    "conditions"
})
@Generated("jsonschema2pojo")
public class ConditionSetUpdate {

    /**
     * Name
     * <p>
     * A descriptive name for the set, i.e: 'US based employees' or 'Users behind VPN'
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("A descriptive name for the set, i.e: 'US based employees' or 'Users behind VPN'")
    public String name;
    /**
     * Description
     * <p>
     * an optional longer description of the set
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("an optional longer description of the set")
    public String description;
    /**
     * Conditions
     * <p>
     * a boolean expression that consists of multiple conditions, with and/or logic.
     * 
     */
    @JsonProperty("conditions")
    @JsonPropertyDescription("a boolean expression that consists of multiple conditions, with and/or logic.")
    public Conditions__2 conditions;

    public ConditionSetUpdate withName(String name) {
        this.name = name;
        return this;
    }

    public ConditionSetUpdate withDescription(String description) {
        this.description = description;
        return this;
    }

    public ConditionSetUpdate withConditions(Conditions__2 conditions) {
        this.conditions = conditions;
        return this;
    }

}
