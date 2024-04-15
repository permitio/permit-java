
package io.permit.sdk.openapi.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * DerivedRole
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class DerivedRole {

    /**
     * Conditions
     * <p>
     * 
     * 
     */
    @SerializedName("conditions")
    @Expose
    public String conditions;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("settings")
    @Expose
    public DerivedRoleSettings settings;
    /**
     * Rules
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("rules")
    @Expose
    public List<DerivedRoleRule> rules;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DerivedRole() {
    }

    /**
     * 
     * @param settings
     * @param rules
     */
    public DerivedRole(DerivedRoleSettings settings, List<DerivedRoleRule> rules) {
        super();
        this.settings = settings;
        this.rules = rules;
    }

    public DerivedRole withConditions(String conditions) {
        this.conditions = conditions;
        return this;
    }

    public DerivedRole withSettings(DerivedRoleSettings settings) {
        this.settings = settings;
        return this;
    }

    public DerivedRole withRules(List<DerivedRoleRule> rules) {
        this.rules = rules;
        return this;
    }

}
