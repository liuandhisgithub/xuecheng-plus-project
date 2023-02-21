package com.xuecheng.content.api;

import com.xuecheng.content.model.vo.CourseCategoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: xuecheng-plus
 * @ClassName CourseCategoryController
 * @description:
 * @author: liujl
 * @create: 2023-02-21 15:49
 * @Version 1.0
 **/

@Slf4j
@RestController
@Api(value = "课程分类接口",tags = "课程分类接口")
public class CourseCategoryController {

    @ApiOperation("课程分类查询接口")
    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryVo> queryTreeNode(){
        return null;
    }

}
