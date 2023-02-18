
package io.permit.sdk.openapi.models;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * UserStatus
 * <p>
 * An enumeration.
 * 
 */
@Generated("jsonschema2pojo")
public enum UserStatus {

    ACTIVE("active"),
    PENDING("pending");
    private final String value;
    private final static Map<String, UserStatus> CONSTANTS = new HashMap<String, UserStatus>();

    static {
        for (UserStatus c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    UserStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @JsonValue
    public String value() {
        return this.value;
    }

    @JsonCreator
    public static UserStatus fromValue(String value) {
        UserStatus constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }

}
