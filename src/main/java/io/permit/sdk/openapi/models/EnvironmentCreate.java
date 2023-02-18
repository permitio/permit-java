
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * EnvironmentCreate
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "name",
    "description",
    "custom_branch_name"
})
@Generated("jsonschema2pojo")
public class EnvironmentCreate {

    /**
     * Key
     * <p>
     * A URL-friendly name of the environment (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the environment.
     * (Required)
     * 
     */
    @JsonProperty("key")
    @JsonPropertyDescription("A URL-friendly name of the environment (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the environment.")
    public String key;
    /**
     * Name
     * <p>
     * The name of the environment
     * (Required)
     * 
     */
    @JsonProperty("name")
    @JsonPropertyDescription("The name of the environment")
    public String name;
    /**
     * Description
     * <p>
     * an optional longer description of the environment
     * 
     */
    @JsonProperty("description")
    @JsonPropertyDescription("an optional longer description of the environment")
    public String description;
    /**
     * Custom Branch Name
     * <p>
     * when using gitops feature, an optional branch name for the environment
     * 
     */
    @JsonProperty("custom_branch_name")
    @JsonPropertyDescription("when using gitops feature, an optional branch name for the environment")
    public String customBranchName;

    /**
     * No args constructor for use in serialization
     * 
     */
    public EnvironmentCreate() {
    }

    /**
     * 
     * @param name
     * @param key
     */
    public EnvironmentCreate(String key, String name) {
        super();
        this.key = key;
        this.name = name;
    }

    public EnvironmentCreate withKey(String key) {
        this.key = key;
        return this;
    }

    public EnvironmentCreate withName(String name) {
        this.name = name;
        return this;
    }

    public EnvironmentCreate withDescription(String description) {
        this.description = description;
        return this;
    }

    public EnvironmentCreate withCustomBranchName(String customBranchName) {
        this.customBranchName = customBranchName;
        return this;
    }

}
