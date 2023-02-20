
package io.permit.sdk.openapi.models;

import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ResourceAttributeUpdate
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ResourceAttributeUpdate {

    @SerializedName("type")
    @Expose
    public AttributeType type;
    /**
     * Description
     * <p>
     * An optional longer description of what this attribute respresents in your system
     * 
     */
    @SerializedName("description")
    @Expose
    public String description;

    public ResourceAttributeUpdate withType(AttributeType type) {
        this.type = type;
        return this;
    }

    public ResourceAttributeUpdate withDescription(String description) {
        this.description = description;
        return this;
    }

}
