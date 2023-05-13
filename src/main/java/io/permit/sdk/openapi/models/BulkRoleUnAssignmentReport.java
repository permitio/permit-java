
package io.permit.sdk.openapi.models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * BulkRoleUnAssignmentReport
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class BulkRoleUnAssignmentReport {

    /**
     * Assignments Removed
     * <p>
     * 
     * 
     */
    @SerializedName("assignments_removed")
    @Expose
    public Integer assignmentsRemoved = 0;

    public BulkRoleUnAssignmentReport withAssignmentsRemoved(Integer assignmentsRemoved) {
        this.assignmentsRemoved = assignmentsRemoved;
        return this;
    }

}
