
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ConditionSetCreate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ConditionSetCreate {

    /**
     * Key
     * <p>
     * A unique id by which Permit will identify the condition set. The key will be used as the generated rego rule name.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;
    /**
     * the type of the set: UserSet or ResourceSet
     * 
     */
    @SerializedName("type")
    @Expose
    public Object type = null;
    /**
     * Autogenerated
     * <p>
     * whether the set was autogenerated by the system.
     * 
     */
    @SerializedName("autogenerated")
    @Expose
    public Boolean autogenerated = false;
    /**
     * Resource Id
     * <p>
     * For ResourceSets, the id of the base resource.
     * 
     */
    @SerializedName("resource_id")
    @Expose
    public Object resourceId;
    /**
     * Name
     * <p>
     * A descriptive name for the set, i.e: 'US based employees' or 'Users behind VPN'
     * (Required)
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
    public Conditions conditions;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ConditionSetCreate() {
    }

    /**
     * 
     * @param name
     * @param key
     */
    public ConditionSetCreate(String key, String name) {
        super();
        this.key = key;
        this.name = name;
    }

    public ConditionSetCreate withKey(String key) {
        this.key = key;
        return this;
    }

    public ConditionSetCreate withType(Object type) {
        this.type = type;
        return this;
    }

    public ConditionSetCreate withAutogenerated(Boolean autogenerated) {
        this.autogenerated = autogenerated;
        return this;
    }

    public ConditionSetCreate withResourceId(Object resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public ConditionSetCreate withName(String name) {
        this.name = name;
        return this;
    }

    public ConditionSetCreate withDescription(String description) {
        this.description = description;
        return this;
    }

    public ConditionSetCreate withConditions(Conditions conditions) {
        this.conditions = conditions;
        return this;
    }

}
