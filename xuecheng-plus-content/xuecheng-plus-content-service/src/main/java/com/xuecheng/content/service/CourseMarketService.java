package com.xuecheng.content.service;

import com.xuecheng.content.model.po.CourseMarket;

/**
 * @program: xuecheng-plus
 * @interfaceName CourseMarketService
 * @description:
 * @author: liujl
 * @create: 2023-02-27 23:23
 * @Version 1.0
 **/
public interface CourseMarketService {

    boolean saveOrUpdate(CourseMarket courseMarket);
}
