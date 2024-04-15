
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import javax.annotation.Generated;
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
    public java.lang.String name;
    /**
     * Description
     * <p>
     * an optional longer description of the set
     * 
     */
    @SerializedName("description")
    @Expose
    public java.lang.String description;
    /**
     * Conditions
     * <p>
     * a boolean expression that consists of multiple conditions, with and/or logic.
     * 
     */
    @SerializedName("conditions")
    @Expose
    public HashMap<String, Object> conditions;
    /**
     * Parent Id
     * <p>
     * Parent Condition Set
     * 
     */
    @SerializedName("parent_id")
    @Expose
    public java.lang.String parentId;

    public ConditionSetUpdate withName(java.lang.String name) {
        this.name = name;
        return this;
    }

    public ConditionSetUpdate withDescription(java.lang.String description) {
        this.description = description;
        return this;
    }

    public ConditionSetUpdate withConditions(HashMap<String, Object> conditions) {
        this.conditions = conditions;
        return this;
    }

    public ConditionSetUpdate withParentId(java.lang.String parentId) {
        this.parentId = parentId;
        return this;
    }

}
