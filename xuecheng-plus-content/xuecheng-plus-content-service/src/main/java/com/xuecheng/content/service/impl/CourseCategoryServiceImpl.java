package com.xuecheng.content.service.impl;

import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.po.CourseCategory;
import com.xuecheng.content.model.vo.CourseCategoryVo;
import com.xuecheng.content.service.CourseCategoryService;
import com.xuecheng.utils.CommonBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: xuecheng-plus
 * @ClassName CourseCategoryServiceImpl
 * @description:
 * @author: liujl
 * @create: 2023-02-22 20:15
 * @Version 1.0
 **/
@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Override
    public List<CourseCategoryVo> queryTreeNode(String rootId) {

        List<CourseCategory> courseCategoryList = courseCategoryMapper.selectTreeNodes(rootId);

        List<CourseCategoryVo> result = new ArrayList<>();
        // 存储查询结果
        Map<String, CourseCategoryVo> nodeMape = courseCategoryList.stream().collect(Collectors.toMap(CourseCategory::getId, item -> CommonBeanUtils.copyProperties(item, CourseCategoryVo.class)));
        courseCategoryList.stream().forEach(courseCategory -> {
            CourseCategoryVo courseCategoryVo = nodeMape.get(courseCategory.getId());
            if (courseCategory.getParentid().equals(rootId)) {
                result.add(courseCategoryVo);
            }
            //找到节点的父节点
            String parentId = courseCategory.getParentid();
            // 找到父节点对象
            CourseCategoryVo parentNode = nodeMape.get(parentId);
            if (parentNode != null) {
                List<CourseCategoryVo> childrenTreeNode = parentNode.getChildrenTreeNode();
                if(childrenTreeNode == null){
                    parentNode.setChildrenTreeNode(new ArrayList<CourseCategoryVo>());
                }
                parentNode.getChildrenTreeNode().add(courseCategoryVo);
            }
        });

        return result;
    }
}
