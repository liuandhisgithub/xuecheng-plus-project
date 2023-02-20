package com.xuecheng.content.mapper;

import com.xuecheng.content.model.po.CourseBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: xuecheng-plus
 * @ClassName CourseBaseMapperTest
 * @description:
 * @author: liujl
 * @create: 2023-02-20 15:46
 * @Version 1.0
 **/
@SpringBootTest
public class CourseBaseMapperTest {

    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Test
    public void testCourseBaseMapper() {
        CourseBase courseBase = courseBaseMapper.selectById(22);
        Assertions.assertNotNull(courseBase);
    }


}
