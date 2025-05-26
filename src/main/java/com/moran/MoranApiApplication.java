package com.moran;

import com.mzt.logapi.starter.annotation.EnableLogRecord;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动项
 * @author :moran
 **/
@SpringBootApplication
@MapperScan("com.moran.mapper")
@EnableLogRecord(tenant = "com.moran")
public class MoranApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoranApiApplication.class, args);
    }

}
