package com.xuecheng.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: xuecheng-plus
 * @ClassName CourseEditDto
 * @description:
 * @author: liujl
 * @create: 2023-02-27 21:33
 * @Version 1.0
 **/

@Data
@ApiModel(value="EditCourseDto", description="修改课程基本信息")
public class CourseEditDto extends CourseInfoDto{

    @ApiModelProperty(value = "课程id", required = true)
    private Long id;

}
