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
//Spring Boot拥有嵌入式Tomcat,Spring Boot本身就集成了Tomcat
// 扫描Mapper接口并容器管理(相当于在所有的Mapper接口上加上@Mapper注解,可以实现依赖注入)
// sqlSessionFactoryRef用来引用,数据库工厂实例对象
@MapperScan(basePackages = ClusterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterDataSourceConfig {

    // 精确到 cluster 目录，以便跟其他数据源隔离
    static final String PACKAGE = "org.spring.springboot.dao.cluster";
    static final String MAPPER_LOCATION = "classpath:mapper/cluster/*.xml";

    @Value("${cluster.datasource.url}")
    private String url;

    @Value("${cluster.datasource.username}")
    private String user;

    @Value("${cluster.datasource.password}")
    private String password;

    @Value("${cluster.datasource.driverClassName}")
    private String driverClass;

    @Bean(name = "clusterDataSource")
    public DataSource clusterDataSource() {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        return dataSource;
    }


    //    配置从数据源相关的事物管理
    @Bean(name = "clusterTransactionManager")
    public DataSourceTransactionManager clusterTransactionManager() {

        return new DataSourceTransactionManager(clusterDataSource());
    }


    @Bean(name = "clusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource clusterDataSource)
            throws Exception {

        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        设置数据源到SqlSessionFactory
        sessionFactory.setDataSource(clusterDataSource);
//        设置加载Mapper接口的xml映射文件的位置
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ClusterDataSourceConfig.MAPPER_LOCATION));

//        返回数据库工厂实例对象
        return sessionFactory.getObject();
    }
}