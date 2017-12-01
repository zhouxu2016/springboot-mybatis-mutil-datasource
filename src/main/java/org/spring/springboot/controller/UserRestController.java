package org.spring.springboot.controller;

import org.spring.springboot.domain.User;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 用户控制层
 *
 */
@RestController
public class UserRestController {

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

}
