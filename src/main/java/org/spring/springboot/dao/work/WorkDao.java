package org.spring.springboot.dao.work;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.Alias;
import org.spring.springboot.domain.Work;

/**
 * 城市 WorkDao 接口类
 *
 */

//@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE,
// sqlSessionFactoryRef = "masterSqlSessionFactory")
// MasterDataSourceConfig,已经配置Mapper扫描了,这里的Mapper接口就不需要加上@Mapper注解了

//@Mapper
public interface WorkDao {

    Work findByName(@Param("name") String name);
}
