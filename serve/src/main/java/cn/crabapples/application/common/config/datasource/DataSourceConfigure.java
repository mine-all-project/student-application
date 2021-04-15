package cn.crabapples.application.common.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


/**
 * 数据源配置
 */
@Slf4j
@Configuration
public class DataSourceConfigure {

    @Primary
    @Bean(value = "firstDataSource")
    @ConfigurationProperties("spring.datasource.druid.first")
    public DataSource dataSourceOne() {
        log.info("Init DataSourceOne");
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        DataSourceContextHolder.dataSourceIds.add("firstDataSource");
        return dataSource;
    }

//    @Bean(value = "secondDataSource")
//    @ConfigurationProperties("spring.datasource.druid.second")
//    public DataSource dataSourceTwo() {
//        log.info("Init DataSourceTwo");
//        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
//        DataSourceContextHolder.dataSourceIds.add("secondDataSource");
//        return dataSource;
//    }
}
