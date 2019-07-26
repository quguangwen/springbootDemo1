package com.demo.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
  * springboot集成mybatis的基本入口 1）创建数据源(如果采用的是默认的tomcat-jdbc数据源，则不需要)
  * 2）创建SqlSessionFactory 3）配置事务管理器，除非需要使用事务，否则不用配置
  */
@Slf4j
@Configuration
public class MybatisConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource lendappDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName",env.getProperty("lend.jdbc.driverClassName"));
        props.put("url",env.getProperty("lend.jdbc.url"));
        props.put("username",env.getProperty("lend.jdbc.username"));
        props.put("password",env.getProperty("lend.jdbc.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    public DataSource finupDataSource() throws Exception {
        Properties props = new Properties();
        props.put("driverClassName",env.getProperty("finup.jdbc.driverClassName"));
        props.put("url",env.getProperty("finup.jdbc.url"));
        props.put("username",env.getProperty("finup.jdbc.username"));
        props.put("password",env.getProperty("finup.jdbc.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    /**
     *
     */
    @Bean
   // @Primary
    public DynamicDataSource dataSource(@Qualifier("lendappDataSource") DataSource lendappDataSource,
                                 @Qualifier("finupDataSource") DataSource finupDataSource){
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.lend_app,lendappDataSource);
        targetDataSources.put(DatabaseType.finup_lend,finupDataSource);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(lendappDataSource); // 默认的datasource设置为lendappDataSource
        return dataSource;
    }

    /***
     *
     * @param lendappDataSource
     * @param finupDataSource
     * @return
     * @throws Exception
     */
//    @Bean
//    public org.apache.ibatis.session.Configuration globalConfiguration(){
//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//        configuration.setMapUnderscoreToCamelCase(true);
//        return configuration;
//    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("lendappDataSource") DataSource lendappDataSource,
                                               @Qualifier("finupDataSource") DataSource finupDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(this.dataSource(lendappDataSource,finupDataSource)); //指定数据源
        sqlSessionFactoryBean.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
//        sqlSessionFactoryBean.setConfiguration(globalConfiguration());
        //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try{
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e){
            log.error("Error to initalize mybatis ");
            throw new RuntimeException(e);
        }
    }


    @Bean
    public DataSourceTransactionManager  transactionManager(DynamicDataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

}
