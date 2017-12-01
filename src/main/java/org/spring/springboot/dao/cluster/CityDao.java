package org.spring.springboot.dao.cluster;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.City;

/**
 * 城市 DAO 接口类
 *
 */

//@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE,
// sqlSessionFactoryRef = "masterSqlSessionFactory")
// MasterDataSourceConfig,已经配置Mapper扫描了,这里的Mapper接口就不需要加上@Mapper注解了

//@Mapper
public interface CityDao {

    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
    City findByName(@Param("cityName") String cityName);
}
