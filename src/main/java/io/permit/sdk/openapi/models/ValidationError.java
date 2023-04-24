
package io.permit.sdk.openapi.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * ValidationError
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class ValidationError {

    /**
     * Location
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("loc")
    @Expose
    public List<Object> loc;
    /**
     * Message
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("msg")
    @Expose
    public String msg;
    /**
     * Error Type
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("type")
    @Expose
    public String type;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ValidationError() {
    }

    /**
     * 
     * @param msg
     * @param loc
     * @param type
     */
    public ValidationError(List<Object> loc, String msg, String type) {
        super();
        this.loc = loc;
        this.msg = msg;
        this.type = type;
    }

    public ValidationError withLoc(List<Object> loc) {
        this.loc = loc;
        return this;
    }

    public ValidationError withMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ValidationError withType(String type) {
        this.type = type;
        return this;
    }

}
