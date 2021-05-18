package com.example.demo.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PostgresDatasource {
    @Bean
    @ConfigurationProperties("app.datasource")
    public HikariDataSource hikariDataSource(){ //instantiaing this hikari source as a bean
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class) //Hikari is the recommend datasource for springboot as it is reliable and fast
                .build();
    }
}
