package com.example.transactiontest.config.database.common;

import com.example.transactiontest.config.common.YamlLoadFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.example"})
@PropertySources({
        /////
        @PropertySource(value = "classpath:/application.properties")
        /////
})
public class CommonDatabaseConfiguration {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            ////
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource") DataSource dataSource
    ) {

        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = builder
                .dataSource(dataSource)   //
                .packages("com.example").build();
        log.info("init LocalContainerEntityManagerFactoryBean.. {}", localContainerEntityManagerFactoryBean.hashCode());
        return localContainerEntityManagerFactoryBean;
    }

//    @Primary
//    @Bean(name = "entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            ////
//            final JpaProperties customerJpaProperties,
//            ////
//            @Qualifier("dataSource") DataSource dataSource
//            ////
//    ) {
//
//        customerJpaProperties.setGenerateDdl(true);
//        Map<String, String> properties = customerJpaProperties.getProperties();
//
//        properties.put("ddl-auto", "update");
//
//        log.info("customerJpaProperties = " + customerJpaProperties);
//        log.info("customerJpaProperties " + gson.toJson(properties));
//
//        EntityManagerFactoryBuilder builder ////
//                = new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(),  ////
//                properties,  ////
//                null ////
//        );
//
//        return builder ////
//                .dataSource(dataSource) ////
//                .packages("com.example") ////
////                .persistenceUnit("userEntityManager") ////
//                .build();
//    }


    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            ////
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactory);
        log.info("init jpaTransactionManager.. {}", jpaTransactionManager.hashCode());
        return jpaTransactionManager;
    }

}
