package org.spring.springboot.config.ds;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
// 扫描Mapper接口并容器管理(相当于在所有的Mapper接口上加上@Mapper注解,可以实现依赖注入)
// sqlSessionFactoryRef用来引用,数据库工厂实例对象
@MapperScan(basePackages = WorkDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "clusterTwoSqlSessionFactory")
public class WorkDataSourceConfig {

    // 精确到 cluster 目录，以便跟其他数据源隔离
    static final String PACKAGE = "org.spring.springboot.dao.work";
    static final String MAPPER_LOCATION = "classpath:mapper/work/*.xml";

    @Value("${clustertwo.datasource.url}")
    private String url;

    @Value("${clustertwo.datasource.username}")
    private String user;

    @Value("${clustertwo.datasource.password}")
    private String password;

    @Value("${clustertwo.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "clustertwoDataSource")
    public DataSource clusterDataSource() {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }


    //    配置从数据源相关的事物管理
    @Bean(name = "clusterTwoTransactionManager")
    public DataSourceTransactionManager clusterTransactionManager() {

        return new DataSourceTransactionManager(clusterDataSource());
    }



    @Bean(name = "clusterTwoSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clustertwoDataSource") DataSource clusterDataSource)
            throws Exception {

        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        设置数据源到SqlSessionFactory
        sessionFactory.setDataSource(clusterDataSource);
//        设置加载Mapper接口的xml映射文件的位置
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(WorkDataSourceConfig.MAPPER_LOCATION));

//        返回数据库工厂实例对象
        return sessionFactory.getObject();
    }
}