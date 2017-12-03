package org.spring.springboot.service.impl;

import org.spring.springboot.dao.cluster.CityDao;
import org.spring.springboot.dao.master.UserDao;
import org.spring.springboot.dao.work.WorkDao;
import org.spring.springboot.domain.City;
import org.spring.springboot.domain.User;
import org.spring.springboot.domain.Work;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务实现层
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao; // 主数据源

    @Autowired
    private CityDao cityDao; // 从数据源

    @Autowired
    private WorkDao workDao; // 从数据源

    @Override
    public User findByName(String userName) {

       /* User user = userDao.findByName(userName);

        City city = cityDao.findByName("上海市");
        Work work = workDao.findByName("周旭");

        user.setCity(city);
        user.setWork(work);*/
//      Long id, String userName, String description, City city, Work work)
        City city = new City(1L, 1L, "北京", "北京工作");
        Work work = new Work(1, "北京", "北京", "常远");
        User user = new User(1L, "常远", "北京工作", city, work);

        return user;
    }
}
