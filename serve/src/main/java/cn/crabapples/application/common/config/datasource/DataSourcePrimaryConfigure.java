package cn.crabapples.application.common.config.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

/**
 * 主数据源配置
 */

@Configuration
@EnableJpaRepositories(basePackages = "cn.crabapples.application",
        entityManagerFactoryRef = "localContainerEntityManagerFactoryBeanPrimary",
        transactionManagerRef = "platformTransactionManagerPrimary")
//@EntityScan("cn.crabapples.system.entity")
public class DataSourcePrimaryConfigure {
    private final DataSource dataSource;

    private final JpaProperties jpaProperties;

    private final HibernateProperties hibernateProperties;

    public DataSourcePrimaryConfigure(@Qualifier(value = "firstDataSource") DataSource dataSource,
                                      JpaProperties jpaProperties,
                                      HibernateProperties hibernateProperties) {
        this.dataSource = dataSource;
        this.jpaProperties = jpaProperties;
        this.hibernateProperties = hibernateProperties;
    }


    @Bean
    @Primary
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBeanPrimary(EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder
                .dataSource(dataSource)
                .properties(properties)
                .packages("cn.crabapples.application")
                .persistenceUnit("mainDatabase").build();
    }

    @Bean
    @Primary
    PlatformTransactionManager platformTransactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = localContainerEntityManagerFactoryBeanPrimary(builder);
        return new JpaTransactionManager(Objects.requireNonNull(localContainerEntityManagerFactoryBean.getObject()));
    }
}
