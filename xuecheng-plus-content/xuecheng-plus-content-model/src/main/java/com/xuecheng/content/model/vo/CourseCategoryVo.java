package com.xuecheng.content.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: xuecheng-plus
 * @ClassName CourseCategoryVo
 * @description:
 * @author: liujl
 * @create: 2023-02-21 16:17
 * @Version 1.0
 **/
@Data
public class CourseCategoryVo {

    /**
     * 主键
     */
    private String id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类标签默认和名称一样
     */
    private String label;

    /**
     * 父结点id（第一级的父节点是0，自关联字段id）
     */
    private String parentid;

    /**
     * 是否显示
     */
    private Integer isShow;

    /**
     * 排序字段
     */
    private Integer orderby;

    /**
     * 是否叶子
     */
    private Integer isLeaf;

    /**
     * 子节点
     */
    private List<CourseCategoryVo> childrenTreeNode;
}
