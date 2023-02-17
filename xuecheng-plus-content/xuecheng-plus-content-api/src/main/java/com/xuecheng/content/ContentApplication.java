package com.xuecheng.content;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: xuecheng-plus
 * @ClassName ContentApplication
 * @description:
 * @author: liujl
 * @create: 2023-02-17 22:16
 * @Version 1.0
 **/
@SpringBootApplication
@EnableSwagger2Doc
public class ContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class, args);
    }
}
