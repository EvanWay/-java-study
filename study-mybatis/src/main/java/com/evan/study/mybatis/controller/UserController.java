package com.evan.study.mybatis.controller;

import com.evan.study.mybatis.entity.User;
import com.evan.study.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Evan
 * @date 2022/1/21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * select
     */
    @RequestMapping("getUser/{id}")
    public String getUser(@PathVariable int id) {
        return userService.sel(id).toString();
    }

    /**
     * insert
     */
    @GetMapping("insert")
    public String insert() throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            TimeUnit.SECONDS.sleep(5);
            User user = new User();
            user.setUserName("username" + i);
            user.setPassWord("password" + i);
            user.setRealName("realname" + i);
            userService.insert(user);
            System.out.println("add:" + i);
        }
        return "请求成功";
    }


    /**
     * 通过SqlSessionFactory，直接调用Mapper
     */
    @GetMapping("test1")
    public String test1()  {
        userService.test1();
        return "请求成功";
    }
    /**
     * 通过SqlSessionFactory，直接调用Mapper
     * 批量插入batch
     */
    @GetMapping("test2")
    public String test2()  {
        userService.test2();
        return "请求成功";
    }
    /**
     * 通过SqlSession，直接调用Mapper
     */
    @GetMapping("test3")
    public String test3()  {
        userService.test3();
        return "请求成功";
    }
}
