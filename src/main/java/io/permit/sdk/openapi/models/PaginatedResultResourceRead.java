
package io.permit.sdk.openapi.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * PaginatedResult[ResourceRead]
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class PaginatedResultResourceRead {

    /**
     * Data
     * <p>
     * List of Resources
     * (Required)
     * 
     */
    @SerializedName("data")
    @Expose
    public List<ResourceRead> data;
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
    public PaginatedResultResourceRead() {
    }

    /**
     * 
     * @param data
     * @param totalCount
     */
    public PaginatedResultResourceRead(List<ResourceRead> data, Integer totalCount) {
        super();
        this.data = data;
        this.totalCount = totalCount;
    }

    public PaginatedResultResourceRead withData(List<ResourceRead> data) {
        this.data = data;
        return this;
    }

    public PaginatedResultResourceRead withTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public PaginatedResultResourceRead withPageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

}
