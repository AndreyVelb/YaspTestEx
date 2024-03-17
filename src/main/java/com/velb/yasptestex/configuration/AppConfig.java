package com.velb.yasptestex.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class AppConfig {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUserName;

    @Value("${spring.datasource.password}")
    private String datasourcePassword;


    @Bean
    public Connection connection() {
        try {
            return DriverManager.getConnection(datasourceUrl, datasourceUserName, datasourcePassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}