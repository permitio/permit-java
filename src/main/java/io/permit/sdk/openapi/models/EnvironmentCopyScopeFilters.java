
package io.permit.sdk.openapi.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * EnvironmentCopyScopeFilters
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class EnvironmentCopyScopeFilters {

    /**
     * Include
     * <p>
     * Objects to include (use * as wildcard)
     * 
     */
    @SerializedName("include")
    @Expose
    public List<String> include;
    /**
     * Exclude
     * <p>
     * Object to exclude (use * as wildcard)
     * 
     */
    @SerializedName("exclude")
    @Expose
    public List<String> exclude;

    public EnvironmentCopyScopeFilters withInclude(List<String> include) {
        this.include = include;
        return this;
    }

    public EnvironmentCopyScopeFilters withExclude(List<String> exclude) {
        this.exclude = exclude;
        return this;
    }

}
