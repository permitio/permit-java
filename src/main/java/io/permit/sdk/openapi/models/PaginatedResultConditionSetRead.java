
package io.permit.sdk.openapi.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * PaginatedResult[ConditionSetRead]
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class PaginatedResultConditionSetRead {

    /**
     * Data
     * <p>
     * List of Condition Sets
     * (Required)
     * 
     */
    @SerializedName("data")
    @Expose
    public List<ConditionSetRead> data;
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
    public PaginatedResultConditionSetRead() {
    }

    /**
     * 
     * @param data
     * @param totalCount
     */
    public PaginatedResultConditionSetRead(List<ConditionSetRead> data, Integer totalCount) {
        super();
        this.data = data;
        this.totalCount = totalCount;
    }

    public PaginatedResultConditionSetRead withData(List<ConditionSetRead> data) {
        this.data = data;
        return this;
    }

    public PaginatedResultConditionSetRead withTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public PaginatedResultConditionSetRead withPageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

}
