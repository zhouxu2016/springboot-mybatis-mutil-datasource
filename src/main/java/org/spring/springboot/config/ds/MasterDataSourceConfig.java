package org.spring.springboot.config.ds;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author zhouxu
 */
@Configuration
// 扫描Mapper接口并容器管理(相当于在所有的Mapper接口上加上@Mapper注解,可以实现依赖注入)
// sqlSessionFactoryRef用来引用,数据库工厂实例对象
@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

    /**
     * 精确到 master 目录,以便跟其他数据源隔离
     */
    static final String PACKAGE = "org.spring.springboot.dao.master";
    static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml";

    @Value("${master.datasource.url}")
    private String url;

    @Value("${master.datasource.username}")
    private String user;

    @Value("${master.datasource.password}")
    private String password;

    @Value("${master.datasource.driverClassName}")
    private String driverClass;

    /**
     * 使用@Primary注解表明为主数据源
     *
     * @return dataSource,数据源对象
     */
    @Bean(name = "masterDataSource")
    @Primary
    public DataSource masterDataSource() {

        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }

    //    配置主数据源相关的事物管理
    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    //    设置主数据源的SqlSessionFactory
    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
            throws Exception {

        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        设置数据源
        sessionFactory.setDataSource(masterDataSource);
//        设置加载Mapper接口的xml映射文件的位置
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MasterDataSourceConfig.MAPPER_LOCATION));

//        返回数据库工厂实例对象
        return sessionFactory.getObject();
    }
}