package com.student;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.student.*.dao")
@SpringBootApplication
@EnableCaching
public class Application {
    public static void main(String[] args) {
        try{
            SpringApplication.run(Application.class, args);
            System.out.println("启动成功！");
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
