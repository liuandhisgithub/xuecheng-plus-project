package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.execption.XueChengPlusException;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.mapper.CourseMarketMapper;
import com.xuecheng.content.model.dto.CourseEditDto;
import com.xuecheng.content.model.dto.CourseInfoDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.po.CourseCategory;
import com.xuecheng.content.model.po.CourseMarket;
import com.xuecheng.content.model.vo.CourseBaseVo;
import com.xuecheng.content.model.vo.CourseInfoVo;
import com.xuecheng.content.service.CourseBaseService;
import com.xuecheng.content.service.CourseMarketService;
import com.xuecheng.utils.CommonBeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

    @Autowired
    CourseMarketMapper courseMarketMapper;

    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Autowired
    CourseMarketService courseMarketService;

    @Override
    public PageResult<CourseBaseVo> queryCourseBaseList(QueryCourseParamsDto queryCourseParamsDto, PageParams pageParams) {

        LambdaQueryWrapper<CourseBase> query = new LambdaQueryWrapper<>();

        // ????????????
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

    @Transactional
    @Override
    public CourseInfoVo createCourseBase(Long companyId, CourseInfoDto courseInfoDto) {

        // ?????????????????????????????????
        CourseBase courseBase = new CourseBase();
        // ??????
        BeanUtils.copyProperties(courseInfoDto, courseBase);
        // ?????????????????? TODO ???????????????????????????????????????????????????????????????
        courseBase.setAuditStatus("202002");
        // ??????????????????
        courseBase.setStatus("203001");
        courseBase.setCompanyId(companyId);
        courseBase.setCreateDate(LocalDateTime.now());
        // ???????????????
        int courseBaseInsert = courseBaseMapper.insert(courseBase);
        // ??????id
        Long courseId = courseBase.getId();
        // ??????????????????
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(courseInfoDto, courseMarket);
        courseMarket.setId(courseId);

        // ??????????????????
        String charge = courseInfoDto.getCharge();

        // ???????????????????????? ??????????????????????????????
        if (charge.equals("201001")) {
            Float price = courseInfoDto.getPrice();
            // Float ????????? Comparable ???????????????????????????
            if (price == null || price <= 0) {
                XueChengPlusException.cast("??????????????????????????????????????????????????????0");
            }
        }

        int courseMarketInsert = courseMarketMapper.insert(courseMarket);

        if (courseBaseInsert <= 0 || courseMarketInsert <= 0) {
            throw new RuntimeException("??????????????????????????????");
        }

        return getCourseBaseInfo(courseId);
    }

    @Override
    public CourseInfoVo getCourseBaseInfo(Long courseId) {

        // ?????????????????????
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);

        // ????????????
        if (courseBase == null || courseMarket == null) {
            return null;
        }

        CourseInfoVo courseInfoVo = new CourseInfoVo();
        // ????????????
        BeanUtils.copyProperties(courseBase, courseInfoVo);
        BeanUtils.copyProperties(courseMarket, courseInfoVo);

        // ??????????????????
        CourseCategory stCourseCategory = courseCategoryMapper.selectById(courseBase.getSt());
        if (stCourseCategory != null) {
            courseInfoVo.setStName(stCourseCategory.getName());
        }
        CourseCategory mtCourseCategory = courseCategoryMapper.selectById(courseBase.getMt());
        if (mtCourseCategory != null) {
            courseInfoVo.setMtName(mtCourseCategory.getName());
        }
        return courseInfoVo;
    }

    @Transactional
    @Override
    public CourseInfoVo modifyCourseInfo(Long companyId, CourseEditDto courseEditDto) {
        // ????????????
        Long courseId = courseEditDto.getId();
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if(courseBase == null){
            XueChengPlusException.cast("???????????????");
        }
        if(companyId != courseBase.getCompanyId()){
            XueChengPlusException.cast("??????id??????");
        }
        // ??????pojo
        BeanUtils.copyProperties(courseEditDto,courseBase);
        int courseBaseUpdate = courseBaseMapper.updateById(courseBase);

        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(courseEditDto,courseMarket);
        boolean courseMarktUpdate= courseMarketService.saveOrUpdate(courseMarket);
        if(!courseMarktUpdate || courseBaseUpdate <=0){
            XueChengPlusException.cast("????????????");
        }
        return getCourseBaseInfo(courseId);
    }
}
