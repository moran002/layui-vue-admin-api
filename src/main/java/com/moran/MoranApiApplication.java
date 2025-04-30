package com.moran;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动项
 * @author :moran
 **/
@SpringBootApplication
@MapperScan("com.moran.mapper")
public class MoranApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoranApiApplication.class, args);
    }

}
