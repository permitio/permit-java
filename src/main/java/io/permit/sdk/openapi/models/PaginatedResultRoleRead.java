
package io.permit.sdk.openapi.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * PaginatedResult[RoleRead]
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class PaginatedResultRoleRead {

    /**
     * Data
     * <p>
     * List of Roles
     * (Required)
     * 
     */
    @SerializedName("data")
    @Expose
    public List<RoleRead> data;
    /**
     * Total Count
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("total_count")
    @Expose
    public Integer totalCount;
    /**
     * Page Count
     * <p>
     * 
     * 
     */
    @SerializedName("page_count")
    @Expose
    public Integer pageCount = 0;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PaginatedResultRoleRead() {
    }

    /**
     * 
     * @param data
     * @param totalCount
     */
    public PaginatedResultRoleRead(List<RoleRead> data, Integer totalCount) {
        super();
        this.data = data;
        this.totalCount = totalCount;
    }

    public PaginatedResultRoleRead withData(List<RoleRead> data) {
        this.data = data;
        return this;
    }

    public PaginatedResultRoleRead withTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public PaginatedResultRoleRead withPageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

}
