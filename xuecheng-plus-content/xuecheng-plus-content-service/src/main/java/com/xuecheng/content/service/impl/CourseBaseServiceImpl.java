package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.vo.CourseBaseVo;
import com.xuecheng.content.service.CourseBaseService;
import com.xuecheng.utils.CommonBeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: xuecheng-plus
 * @ClassName CourseBaseServiceImpl
 * @description:
 * @author: liujl
 * @create: 2023-02-20 20:00
 * @Version 1.0
 **/
@Service
public class CourseBaseServiceImpl implements CourseBaseService {
    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Override
    public PageResult<CourseBaseVo> queryCourseBaseList(QueryCourseParamsDto queryCourseParamsDto, PageParams pageParams) {

        LambdaQueryWrapper<CourseBase> query = new LambdaQueryWrapper<>();

        // 查询条件
        query.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()), CourseBase::getName, queryCourseParamsDto.getCourseName());
        query.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()), CourseBase::getAuditStatus, queryCourseParamsDto.getAuditStatus());
        query.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getPublishStatus()), CourseBase::getStatus, queryCourseParamsDto.getPublishStatus());

        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());

        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, query);

        return new PageResult<CourseBaseVo>(
                CommonBeanUtils.copyListProperties(pageResult.getRecords(), CourseBaseVo.class),
                pageResult.getTotal(),
                pageResult.getCurrent(),
                pageResult.getSize()
        );
    }
}
