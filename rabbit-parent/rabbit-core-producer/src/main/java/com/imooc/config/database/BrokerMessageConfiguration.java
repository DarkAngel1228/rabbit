package com.imooc.config.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class BrokerMessageConfiguration {
    @Autowired
    private DataSource rabbitProducerDataSource;

    @Value("classpath:rabbit-producer-message-schema.sql")
    private Resource schemaScript;


    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        System.err.println("----------------------rabbitProducerDataSource---------------------" + rabbitProducerDataSource);
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(rabbitProducerDataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(schemaScript);
        return populator;
    }
}
