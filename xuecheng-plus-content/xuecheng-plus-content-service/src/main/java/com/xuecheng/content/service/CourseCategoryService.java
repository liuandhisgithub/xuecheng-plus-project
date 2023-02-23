package com.xuecheng.content.service;

import com.xuecheng.content.model.vo.CourseCategoryVo;

import java.util.List;


/**
 * @program: xuecheng-plus
 * @ClassName CourseCategoryService
 * @description:
 * @author: liujl
 * @create: 2023-02-22 20:13
 * @Version 1.0
 **/
public interface CourseCategoryService {

    /**
     * 查询课程分类信息，树状结构返回
     * @param rootId
     * @return
     */
    List<CourseCategoryVo> queryTreeNode(String rootId);
}
