package com.xuecheng.content.api;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.model.dto.QueryCourseParamsDto;
import com.xuecheng.model.vo.CourseBaseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBaseVo> list(@RequestBody QueryCourseParamsDto queryCourseParamsDto, PageParams pageParams) {

        return null;
    }

}
