
package io.permit.sdk.openapi.models;

import java.util.List;
import javax.annotation.processing.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * PaginatedResult[UserRead]
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class PaginatedResultUserRead {

    /**
     * Data
     * <p>
     * 
     * (Required)
     * 
     */
    @SerializedName("data")
    @Expose
    public List<UserRead> data;
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
    public PaginatedResultUserRead() {
    }

    /**
     * 
     * @param data
     * @param totalCount
     */
    public PaginatedResultUserRead(List<UserRead> data, Integer totalCount) {
        super();
        this.data = data;
        this.totalCount = totalCount;
    }

    public PaginatedResultUserRead withData(List<UserRead> data) {
        this.data = data;
        return this;
    }

    public PaginatedResultUserRead withTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public PaginatedResultUserRead withPageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

}
