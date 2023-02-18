
package io.permit.sdk.openapi.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * ValidationError
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "loc",
    "msg",
    "type"
})
@Generated("jsonschema2pojo")
public class ValidationError {

    /**
     * Location
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("loc")
    public List<Object> loc = new ArrayList<Object>();
    /**
     * Message
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("msg")
    public String msg;
    /**
     * Error Type
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("type")
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
