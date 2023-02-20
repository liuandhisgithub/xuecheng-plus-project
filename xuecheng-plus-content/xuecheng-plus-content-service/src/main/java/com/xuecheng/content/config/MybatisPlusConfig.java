package com.xuecheng.content.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: xuecheng-plus
 * @ClassName MybatisPlusConfig
 * @description:
 * @author: liujl
 * @create: 2023-02-20 15:51
 * @Version 1.0
 **/

@Configuration
@MapperScan("com.xuecheng.content.mapper")
public class MybatisPlusConfig {

    /**
     * 分页拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
