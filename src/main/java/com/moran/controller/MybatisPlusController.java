package com.moran.controller;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Types;
import java.util.Collections;

public class MybatisPlusController {

    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3306/moran?remarks=true&useInformationSchema=true";
        String USER_NAME = "root";
        String PASSWORD = "xiyang";
        Path path = Paths.get(System.getProperty("user.dir"));
        FastAutoGenerator.create(URL, USER_NAME, PASSWORD)
                .globalConfig(builder -> builder
                        .author("MyBatis-Plus Generator")
                        .outputDir(path + "/src/main/java")
                        .commentDate("yyyy-MM-dd")
                        .disableOpenDir()
                )
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.TINYINT) {
                                // 自定义类型转换
                                return DbColumnType.BOOLEAN;
                            }else if (typeCode == Types.SMALLINT) {
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder -> builder
                        .parent("com.moran")
                        .entity("model")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .pathInfo(Collections.singletonMap(OutputFile.xml, path +  "/src/main/resources/mappers"))
                )
                .strategyConfig(builder -> {
                    builder.addInclude("sys_operate_log") // 设置需要生成的表名
                            .entityBuilder()
                            .enableLombok() // 启用 Lombok
                            .logicDeleteColumnName("deleted")
                            .logicDeletePropertyName("deleted")
                            .addTableFills(
                                    new Column("create_time", FieldFill.INSERT),
                                    new Column("update_time", FieldFill.INSERT_UPDATE),
                                    new Column("create_by", FieldFill.INSERT),
                                    new Column("update_by", FieldFill.INSERT_UPDATE)
                            )
                            .controllerBuilder()
                            .disable() // 进制生成controller
                            .serviceBuilder()
                            .formatServiceFileName("%sService");
                })
                .execute();
    }
}
