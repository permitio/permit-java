
package io.permit.sdk.openapi.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * HTTPValidationError
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "detail"
})
@Generated("jsonschema2pojo")
public class HTTPValidationError {

    /**
     * Detail
     * <p>
     * 
     * 
     */
    @JsonProperty("detail")
    public List<ValidationError> detail = new ArrayList<ValidationError>();

    public HTTPValidationError withDetail(List<ValidationError> detail) {
        this.detail = detail;
        return this;
    }

}
