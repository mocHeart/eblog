package com.moc.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.moc.mapper")
public class MybatisPlusConfig {
}
