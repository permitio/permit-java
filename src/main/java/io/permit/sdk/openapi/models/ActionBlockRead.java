
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ActionBlockRead
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ActionBlockRead {

    /**
     * Name
     * <p>
     * a more descriptive name for the action
     * 
     */
    @SerializedName("name")
    @Expose
    public String name;
    /**
     * Description
     * <p>
     * optional description string explaining what this action represents in your system
     * 
     */
    @SerializedName("description")
    @Expose
    public String description;
    /**
     * Attributes
     * <p>
     * Arbitrary action attributes that can be used for filtering or enforcement of attribute-based access control policies.
     * 
     */
    @SerializedName("attributes")
    @Expose
    public HashMap<String, Object> attributes;
    /**
     * Id
     * <p>
     * Unique id of the action
     * (Required)
     * 
     */
    @SerializedName("id")
    @Expose
    public String id;
    /**
     * Key
     * <p>
     * action key
     * 
     */
    @SerializedName("key")
    @Expose
    public String key;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ActionBlockRead() {
    }

    /**
     * 
     * @param id
     */
    public ActionBlockRead(String id) {
        super();
        this.id = id;
    }

    public ActionBlockRead withName(String name) {
        this.name = name;
        return this;
    }

    public ActionBlockRead withDescription(String description) {
        this.description = description;
        return this;
    }

    public ActionBlockRead withAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public ActionBlockRead withId(String id) {
        this.id = id;
        return this;
    }

    public ActionBlockRead withKey(String key) {
        this.key = key;
        return this;
    }

}
