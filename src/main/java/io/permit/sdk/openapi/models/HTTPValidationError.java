
package io.permit.sdk.openapi.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * HTTPValidationError
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class HTTPValidationError {

    /**
     * Detail
     * <p>
     * 
     * 
     */
    @SerializedName("detail")
    @Expose
    public List<ValidationError> detail;

    public HTTPValidationError withDetail(List<ValidationError> detail) {
        this.detail = detail;
        return this;
    }

}
