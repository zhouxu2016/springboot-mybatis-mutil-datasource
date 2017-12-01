package org.spring.springboot.dao.master;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.User;

/**
 * 用户 DAO 接口类
 *
 */

//@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE,
// sqlSessionFactoryRef = "masterSqlSessionFactory")
// MasterDataSourceConfig,已经配置Mapper扫描了,这里的Mapper接口就不需要加上@Mapper注解了

//@Mapper
public interface UserDao {

    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    User findByName(@Param("userName") String userName);
}
