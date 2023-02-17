package com.xuecheng.base.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @program: xuecheng-plus
 * @ClassName PageResult
 * @description:
 * @author: liujl
 * @create: 2023-02-17 14:59
 * @Version 1.0
 **/
@Data
@ToString
public class PageResult<T> {

    // 数据结果
    private List<T> items;

    // 总记录数
    private long counts;

    // 页码
    private long page;

    // 每页记录数
    private long pageSize;

    public PageResult(List<T> items, long counts, long page, long pageSize) {
        this.items = items;
        this.counts = counts;
        this.page = page;
        this.pageSize = pageSize;
    }
}
