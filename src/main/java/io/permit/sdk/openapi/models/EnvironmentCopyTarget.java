
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * EnvironmentCopyTarget
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class EnvironmentCopyTarget {

    /**
     * Existing
     * <p>
     * Identifier of an existing environment to copy into
     * 
     */
    @SerializedName("existing")
    @Expose
    public String existing;
    /**
     * New
     * <p>
     * Description of the environment to create. This environment must not already exist.
     * 
     */
    @SerializedName("new")
    @Expose
    public EnvironmentCreate _new;

    public EnvironmentCopyTarget withExisting(String existing) {
        this.existing = existing;
        return this;
    }

    public EnvironmentCopyTarget withNew(EnvironmentCreate _new) {
        this._new = _new;
        return this;
    }

}
