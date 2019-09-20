package com.betterplace.dev.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class DataSourceConfig {

    @Autowired
    PropertiesAWS propertiesAWS;

    @Autowired
    PropertiesLocal propertiesLocal;

    @Autowired
    ApplicationArguments appArgs;

    @Bean
    public DataSource getDataSource() {

        String[] sourceArgs = appArgs.getSourceArgs();

        if(sourceArgs.length > 0 && sourceArgs[0].equals("aws")){ // 파라미터가 aws일때
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.driverClassName(propertiesAWS.getDriverClassName());
            dataSourceBuilder.url(propertiesAWS.getUrl());
            dataSourceBuilder.username(propertiesAWS.getUsername());
            dataSourceBuilder.password(propertiesAWS.getPassword());
            return dataSourceBuilder.build();
        }else{ // 파라미터가 주어지지 않을때
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.driverClassName(propertiesLocal.getDriverClassName());
            dataSourceBuilder.url(propertiesLocal.getUrl());
            dataSourceBuilder.username(propertiesLocal.getUsername());
            dataSourceBuilder.password(propertiesLocal.getPassword());
            return dataSourceBuilder.build();

        }
    }
}
