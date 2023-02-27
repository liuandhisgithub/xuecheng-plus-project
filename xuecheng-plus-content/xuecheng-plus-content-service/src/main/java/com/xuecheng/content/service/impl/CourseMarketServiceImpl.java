package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.execption.XueChengPlusException;
import com.xuecheng.content.mapper.CourseMarketMapper;
import com.xuecheng.content.model.po.CourseMarket;
import com.xuecheng.content.service.CourseMarketService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程营销信息 服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class CourseMarketServiceImpl extends ServiceImpl<CourseMarketMapper, CourseMarket> implements CourseMarketService {

    public boolean saveOrUpdate(CourseMarket courseMarket){
        String change = courseMarket.getCharge();
        if(StringUtils.isBlank(change)){
            XueChengPlusException.cast("收费规则未设置");
        }
        if(change.equals("201001")){
            Float price = courseMarket.getPrice();
            if(price == null || price <=0){
                XueChengPlusException.cast("收费课程价格不能小于零");
            }
        }
        return saveOrUpdate(courseMarket);
    }

}
