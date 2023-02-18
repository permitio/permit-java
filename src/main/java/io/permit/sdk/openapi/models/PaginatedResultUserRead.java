
package io.permit.sdk.openapi.models;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * PaginatedResult[UserRead]
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "total_count",
    "page_count"
})
@Generated("jsonschema2pojo")
public class PaginatedResultUserRead {

    /**
     * Data
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("data")
    public List<UserRead> data = new ArrayList<UserRead>();
    /**
     * Total Count
     * <p>
     * 
     * (Required)
     * 
     */
    @JsonProperty("total_count")
    public Integer totalCount;
    /**
     * Page Count
     * <p>
     * 
     * 
     */
    @JsonProperty("page_count")
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
