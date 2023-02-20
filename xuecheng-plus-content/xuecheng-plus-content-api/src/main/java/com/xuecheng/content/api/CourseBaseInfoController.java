package com.xuecheng.content.api;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.vo.CourseBaseVo;
import com.xuecheng.content.service.CourseBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: xuecheng-plus
 * @ClassName CourseBaseInfoController
 * @description:
 * @author: liujl
 * @create: 2023-02-17 20:04
 * @Version 1.0
 **/
@Api(value = "课程信息接口", tags = "课程信息接口")
@RestController
public class CourseBaseInfoController {

    @Autowired
    CourseBaseService courseBaseService;

    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBaseVo> list(@RequestBody QueryCourseParamsDto queryCourseParamsDto, PageParams pageParams) {
        return courseBaseService.queryCourseBaseList(queryCourseParamsDto, pageParams);
    }

}
