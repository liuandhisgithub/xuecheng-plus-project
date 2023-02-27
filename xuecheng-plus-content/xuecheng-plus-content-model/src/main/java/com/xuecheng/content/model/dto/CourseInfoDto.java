package com.xuecheng.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @program: xuecheng-plus
 * @ClassName CourseInfoDto
 * @description: the course info that be used with add course
 * @author: liujl
 * @create: 2023-02-23 23:31
 * @Version 1.0
 **/
@Data
@ApiModel(value="CourseInfoDto", description="新增课程基本信息")
public class CourseInfoDto {

    // notEmpty 是spring-boot实现的 jsr303规范，可以在controller中使用Validated注解启动校验
    // 多接口使用同一个dto类且校验规则不同时，可以使用groups参数配置不同的类使用不同的校验规则
    @NotEmpty(message = "课程名称不能为空")
    @ApiModelProperty(value = "课程名称", required = true)
    private String name;

    @NotEmpty(message = "适用人群不能为空")
    @Size(message = "适用人群内容过少",min = 10)
    @ApiModelProperty(value = "适用人群", required = true)
    private String users;

    @ApiModelProperty(value = "课程标签")
    private String tags;

    @NotEmpty(message = "课程分类不能为空")
    @ApiModelProperty(value = "大分类", required = true)
    private String mt;

    @NotEmpty(message = "课程分类不能为空")
    @ApiModelProperty(value = "小分类", required = true)
    private String st;

    @NotEmpty(message = "课程等级不能为空")
    @ApiModelProperty(value = "课程等级", required = true)
    private String grade;

    @ApiModelProperty(value = "教学模式（普通，录播，直播等）", required = true)
    private String teachmode;

    @ApiModelProperty(value = "课程介绍")
    private String description;

    @ApiModelProperty(value = "课程图片", required = true)
    private String pic;

    @NotEmpty(message = "收费规则不能为空")
    @ApiModelProperty(value = "收费规则，对应数据字典", required = true)
    private String charge;

    @ApiModelProperty(value = "价格")
    private Float price;
    @ApiModelProperty(value = "原价")
    private Float originalPrice;


    @ApiModelProperty(value = "qq")
    private String qq;

    @ApiModelProperty(value = "微信")
    private String wechat;
    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "有效期")
    private Integer validDays;

}
