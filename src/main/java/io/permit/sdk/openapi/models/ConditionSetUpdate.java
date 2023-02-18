
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ConditionSetUpdate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ConditionSetUpdate {

    /**
     * Name
     * <p>
     * A descriptive name for the set, i.e: 'US based employees' or 'Users behind VPN'
     * 
     */
    @SerializedName("name")
    @Expose
    public String name;
    /**
     * Description
     * <p>
     * an optional longer description of the set
     * 
     */
    @SerializedName("description")
    @Expose
    public String description;
    /**
     * Conditions
     * <p>
     * a boolean expression that consists of multiple conditions, with and/or logic.
     * 
     */
    @SerializedName("conditions")
    @Expose
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
