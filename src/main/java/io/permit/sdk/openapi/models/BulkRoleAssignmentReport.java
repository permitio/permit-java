
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * BulkRoleAssignmentReport
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class BulkRoleAssignmentReport {

    /**
     * Assignments Created
     * <p>
     * 
     * 
     */
    @SerializedName("assignments_created")
    @Expose
    public Integer assignmentsCreated = 0;

    public BulkRoleAssignmentReport withAssignmentsCreated(Integer assignmentsCreated) {
        this.assignmentsCreated = assignmentsCreated;
        return this;
    }

}
