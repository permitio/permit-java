
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * EnvironmentUpdate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class EnvironmentUpdate {

    /**
     * Name
     * <p>
     * The name of the environment
     * 
     */
    @SerializedName("name")
    @Expose
    public String name;
    /**
     * Description
     * <p>
     * an optional longer description of the environment
     * 
     */
    @SerializedName("description")
    @Expose
    public String description;
    /**
     * Custom Branch Name
     * <p>
     * when using gitops feature, an optional branch name for the environment
     * 
     */
    @SerializedName("custom_branch_name")
    @Expose
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
