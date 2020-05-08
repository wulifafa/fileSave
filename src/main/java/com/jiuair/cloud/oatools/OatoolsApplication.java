package com.jiuair.cloud.oatools;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jiuair.cloud.oatools.mapper")
public class OatoolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OatoolsApplication.class, args);
    }

}
