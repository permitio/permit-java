
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * EnvironmentUpdate
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "description",
    "custom_branch_name"
})
@Generated("jsonschema2pojo")
public class EnvironmentUpdate {

    /**
     * Name
     * <p>
     * The name of the environment
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

    public EnvironmentUpdate withName(String name) {
        this.name = name;
        return this;
    }

    public EnvironmentUpdate withDescription(String description) {
        this.description = description;
        return this;
    }

    public EnvironmentUpdate withCustomBranchName(String customBranchName) {
        this.customBranchName = customBranchName;
        return this;
    }

}
