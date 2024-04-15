
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * EnvironmentCreate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class EnvironmentCreate {

    /**
     * Key
     * <p>
     * A URL-friendly name of the environment (i.e: slug). You will be able to query later using this key instead of the id (UUID) of the environment.
     * (Required)
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;
    /**
     * Name
     * <p>
     * The name of the environment
     * (Required)
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
