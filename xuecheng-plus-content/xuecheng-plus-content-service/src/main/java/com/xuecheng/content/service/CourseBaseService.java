package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.CourseInfoDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.vo.CourseBaseVo;
import com.xuecheng.content.model.vo.CourseInfoVo;

/**
 * @program: xuecheng-plus
 * @interfaceName CourseBaseService
 * @description:
 * @author: liujl
 * @create: 2023-02-20 19:58
 * @Version 1.0
 **/
public interface CourseBaseService {

    /**
     * 课程基本信息查询
     * @param queryCourseParamsDto  可以对课程名称进行模糊查询，对审核状态和发表状态进行筛选
     * @param pageParams
     * @return
     */
    PageResult<CourseBaseVo> queryCourseBaseList(QueryCourseParamsDto queryCourseParamsDto, PageParams pageParams);

    /**
     * 新增课程接口
     * @param companyId 机构id
     * @param courseInfoDto 课程信息
     * @return
     */
    CourseInfoVo createCourseBase(Long companyId, CourseInfoDto courseInfoDto);
}
