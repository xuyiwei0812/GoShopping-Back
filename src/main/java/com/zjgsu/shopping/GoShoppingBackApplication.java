package com.zjgsu.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@SpringBootApplication
public class GoShoppingBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoShoppingBackApplication.class, args);
    }

//    //解决上传文件控制层接收为null的方法
//    @Bean(name = "multipartResolver")
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        return multipartResolver;
//    }
}
