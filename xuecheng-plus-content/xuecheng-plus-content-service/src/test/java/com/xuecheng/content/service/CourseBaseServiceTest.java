package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.vo.CourseBaseVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: xuecheng-plus
 * @ClassName CourseBaseServiceTest
 * @description:
 * @author: liujl
 * @create: 2023-02-20 20:16
 * @Version 1.0
 **/
@SpringBootTest
public class CourseBaseServiceTest {

    @Autowired
    CourseBaseService courseBaseService;

    @Test
    public void testQueryCourseBaseList(){
        QueryCourseParamsDto dto = new QueryCourseParamsDto();
        dto.setCourseName("java");
        dto.setAuditStatus("");
        dto.setAuditStatus("");

        PageParams pageParams = new PageParams();

        PageResult<CourseBaseVo> result = courseBaseService.queryCourseBaseList(dto,pageParams);

        System.out.printf(result.toString());

    }
}
