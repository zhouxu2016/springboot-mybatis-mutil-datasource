package org.spring.springboot.controller;

import org.apache.log4j.Logger;
import org.spring.springboot.domain.User;
import org.spring.springboot.domain.Work;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 用户控制层
 */
@RestController
public class UserRestController {

    private static Logger log = Logger.getLogger(UserRestController.class);

    //    @RequestParam(value = "userName", required = true)
    @Autowired
    private UserService userService;

    /**
     * 根据用户名获取用户信息，包括从库的地址信息
     *
     * @param name
     * @return
     */
    @GetMapping(value = "/api/user/{userName}")
    public User findByName(@PathVariable(value = "userName") String name) {
        return userService.findByName(name);
    }


    /*@PostMapping(value = "/api/user")
    public User findUserByName(@RequestParam(value = "name") String name, @RequestParam(value = "age") Integer age) {

//        System.out.println("name>>>>>>>>" + name + "    age>>>>>>>>" + age);
        log.info("name>>>>>>>>" + name + "    age>>>>>>>>" + age);
        return userService.findByName(name);
    }*/


//    produces的作用是指定返回值类型,不但可以设置返回值类型还可以设定返回值的字符编码
    @PostMapping(value = "/api/user", produces = "application/json;charset=utf-8")
    public User findUserByName(@RequestBody Work work) {

        log.info("work>>>>>   " + work);
        return userService.findByName("");
    }

}
