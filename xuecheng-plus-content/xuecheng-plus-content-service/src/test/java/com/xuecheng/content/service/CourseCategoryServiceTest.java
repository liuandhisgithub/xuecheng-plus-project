package com.xuecheng.content.service;

import com.xuecheng.content.model.vo.CourseCategoryVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @program: xuecheng-plus
 * @ClassName CourseCategoryServiceTest
 * @description:
 * @author: liujl
 * @create: 2023-02-22 20:54
 * @Version 1.0
 **/

@SpringBootTest
public class CourseCategoryServiceTest {

    @Autowired
    CourseCategoryService courseCategoryService;

    @Test
    public void testQueryCourseBaseList(){

        List<CourseCategoryVo> result = courseCategoryService.queryTreeNode("1");

        System.out.printf(result.toString());

    }

}
