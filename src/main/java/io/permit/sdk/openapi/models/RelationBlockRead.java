
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * RelationBlockRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class RelationBlockRead {

    /**
     * Resource Id
     * <p>
     * Unique id of the relation
     * (Required)
     * 
     */
    @SerializedName("resource_id")
    @Expose
    public String resourceId;
    /**
     * Resource
     * <p>
     * The resource key
     * (Required)
     * 
     */
    @SerializedName("resource")
    @Expose
    public String resource;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RelationBlockRead() {
    }

    /**
     * 
     * @param resourceId
     * @param resource
     */
    public RelationBlockRead(String resourceId, String resource) {
        super();
        this.resourceId = resourceId;
        this.resource = resource;
    }

    public RelationBlockRead withResourceId(String resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public RelationBlockRead withResource(String resource) {
        this.resource = resource;
        return this;
    }

}
