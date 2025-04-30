package com.moran.conf.bean;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class PageParam implements Serializable {
    private static final Integer PAGE_NO = 1;
    private static final Integer PAGE_SIZE = 10;

    /**
     * Number of items per page - No pagination
     *
     * For example, for export interfaces, you can set {@link #pageSize} to -1 to query all data without pagination.
     */
    public static final Integer PAGE_SIZE_NONE = -1;

    @NotNull(message = "Page number cannot be empty")
    @Min(value = 1, message = "The minimum value for the page number is 1")
    private Integer pageNo = PAGE_NO;

    @NotNull(message = "Number of items per page cannot be empty")
    @Min(value = 1, message = "The minimum value for items per page is 1")
    @Max(value = 100, message = "The maximum value for items per page is 100")
    private Integer pageSize = PAGE_SIZE;

}
