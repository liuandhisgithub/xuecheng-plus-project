package com.xuecheng.content.model.vo;

import lombok.Data;

/**
 * @program: xuecheng-plus
 * @ClassName CourseInfoVo
 * @description: the course info will be return when the be add datebase
 * @author: liujl
 * @create: 2023-02-23 23:31
 * @Version 1.0
 **/
@Data
public class CourseInfoVo {

    /**
     * 收费规则，对应数据字典 TODO 添加枚举类来表示这个数据字典，优化后端代码可读性
     */
    private String charge;

    /**
     * 价格
     */
    private Float price;


    /**
     * 原价
     */
    private Float originalPrice;

    /**
     * 咨询qq
     */
    private String qq;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 电话
     */
    private String phone;

    /**
     * 有效期天数
     */
    private Integer validDays;

    /**
     * 大分类名称
     */
    private String mtName;

    /**
     * 小分类名称
     */
    private String stName;

}
