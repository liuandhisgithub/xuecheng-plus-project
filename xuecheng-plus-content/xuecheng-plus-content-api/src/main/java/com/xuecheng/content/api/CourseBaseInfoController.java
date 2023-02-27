package com.xuecheng.content.api;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.CourseEditDto;
import com.xuecheng.content.model.dto.CourseInfoDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.vo.CourseBaseVo;
import com.xuecheng.content.model.vo.CourseInfoVo;
import com.xuecheng.content.service.CourseBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("新增课程基础信息")
    @PostMapping("/course")
    public CourseInfoVo createCourseBase(@RequestBody @Validated CourseInfoDto courseInfoDto){

        // TODO 获取培训机构的id
        Long companyId = 1l;
        return courseBaseService.createCourseBase(companyId,courseInfoDto);
    }

    @ApiOperation("根据课程id查询课程基础信息")
    @GetMapping("/course/{courseId}")
    public CourseInfoVo getCourseBaseById(@PathVariable Long courseId){
        return courseBaseService.getCourseBaseInfo(courseId);
    }

    @ApiOperation("根据id修改课程信息")
    @PutMapping("/course")
    public CourseInfoVo editCourseInfo(@RequestBody @Validated CourseEditDto courseEditDto){
        //TODO 获取机构的id
        Long companyId = 1l;
        return null;
    }

}
