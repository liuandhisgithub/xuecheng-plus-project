package com.xuecheng.base.model;


import lombok.Data;
import lombok.ToString;

/**
 * 分页参数模型
 */
@Data
@ToString
public class PageParams {

    // 当前页码默认值
    public static final long DEFAULT_PAGE_CURRENT = 1L;

    // 页大小默认值
    public static final long DEFAULT_PAGE_SIZE = 10L;

    private Long pageNo = DEFAULT_PAGE_CURRENT;

    private Long pageSize = DEFAULT_PAGE_SIZE;

    public PageParams(){

    }

    public PageParams(long pageNo, long pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

}
