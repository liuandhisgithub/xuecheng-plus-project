package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.mapper.CourseMarketMapper;
import com.xuecheng.content.model.dto.CourseInfoDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.po.CourseCategory;
import com.xuecheng.content.model.po.CourseMarket;
import com.xuecheng.content.model.vo.CourseBaseVo;
import com.xuecheng.content.model.vo.CourseInfoVo;
import com.xuecheng.content.service.CourseBaseService;
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

    @Transactional
    @Override
    public CourseInfoVo createCourseBase(Long companyId, CourseInfoDto courseInfoDto) {

        // 参数合法性校验
        if (StringUtils.isBlank(courseInfoDto.getName())) {
            throw new RuntimeException("课程名称为空");
        }

        if (StringUtils.isBlank(courseInfoDto.getMt())) {
            throw new RuntimeException("课程分类为空");
        }

        if (StringUtils.isBlank(courseInfoDto.getSt())) {
            throw new RuntimeException("课程分类为空");
        }

        if (StringUtils.isBlank(courseInfoDto.getGrade())) {
            throw new RuntimeException("课程等级为空");
        }

        if (StringUtils.isBlank(courseInfoDto.getTeachmode())) {
            throw new RuntimeException("教育模式为空");
        }

        if (StringUtils.isBlank(courseInfoDto.getUsers())) {
            throw new RuntimeException("适应人群为空");
        }

        if (StringUtils.isBlank(courseInfoDto.getCharge())) {
            throw new RuntimeException("收费规则为空");
        }

        // 课程基本信息表数据对象
        CourseBase courseBase = new CourseBase();
        // 赋值
        BeanUtils.copyProperties(courseInfoDto, courseBase);
        // 设置审核状态 TODO 之后添加枚举类使用枚举类赋值，而不是魔法值
        courseBase.setAuditStatus("202002");
        // 设置发布状态
        courseBase.setStatus("203001");
        courseBase.setCompanyId(companyId);
        courseBase.setCreateDate(LocalDateTime.now());
        // 存入数据库
        int courseBaseInsert = courseBaseMapper.insert(courseBase);
        // 获取id
        Long courseId = courseBase.getId();
        // 课程营销信息
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(courseInfoDto, courseMarket);
        courseMarket.setId(courseId);

        // 收费规则校验
        String charge = courseInfoDto.getCharge();

        // 课程为收费课程时 课程价格必填且大于零
        if (charge.equals("201001")) {
            Float price = courseInfoDto.getPrice();
            // Float 实现了 Comparable 接口，可以直接比较
            if (price == null || price <= 0) {
                throw new RuntimeException("课程设置了收费价格不能为空且必须大于0");
            }
        }

        int courseMarketInsert = courseMarketMapper.insert(courseMarket);

        if (courseBaseInsert <= 0 || courseMarketInsert <= 0) {
            throw new RuntimeException("新增课程基本信息失败");
        }

        return getCourseBaseInfo(courseId);
    }

    private CourseInfoVo getCourseBaseInfo(Long courseId) {

        // 获取数据库数据
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);

        // 非空校验
        if (courseBase == null || courseMarket == null) {
            return null;
        }

        CourseInfoVo courseInfoVo = new CourseInfoVo();
        // 数据赋值
        BeanUtils.copyProperties(courseBase, courseInfoVo);
        BeanUtils.copyProperties(courseMarket, courseInfoVo);

        // 获取分类名称
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
}
