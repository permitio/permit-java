
package io.permit.sdk.openapi.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * PaginatedResult[RelationRead]
 * <p>
 * 
 * 
 */
@Generated("jsonschema2pojo")
public class PaginatedResultRelationRead {

    /**
     * Data
     * <p>
     * List of Relations
     * (Required)
     * 
     */
    @SerializedName("data")
    @Expose
    public List<RelationRead> data;
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
    public PaginatedResultRelationRead() {
    }

    /**
     * 
     * @param data
     * @param totalCount
     */
    public PaginatedResultRelationRead(List<RelationRead> data, Integer totalCount) {
        super();
        this.data = data;
        this.totalCount = totalCount;
    }

    public PaginatedResultRelationRead withData(List<RelationRead> data) {
        this.data = data;
        return this;
    }

    public PaginatedResultRelationRead withTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public PaginatedResultRelationRead withPageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

}
