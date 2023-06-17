
package io.permit.sdk.openapi.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * PaginatedResult[TenantRead]
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class PaginatedResultTenantRead {

    /**
     * Data
     * <p>
     * List of Tenants
     * (Required)
     * 
     */
    @SerializedName("data")
    @Expose
    public List<TenantRead> data;
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
    public PaginatedResultTenantRead() {
    }

    /**
     * 
     * @param data
     * @param totalCount
     */
    public PaginatedResultTenantRead(List<TenantRead> data, Integer totalCount) {
        super();
        this.data = data;
        this.totalCount = totalCount;
    }

    public PaginatedResultTenantRead withData(List<TenantRead> data) {
        this.data = data;
        return this;
    }

    public PaginatedResultTenantRead withTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public PaginatedResultTenantRead withPageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

}
