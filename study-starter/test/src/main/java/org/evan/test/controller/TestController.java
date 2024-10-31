package org.evan.test.controller;

import org.evan.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Evan
 * @date 2021/12/27
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping(value = "get")
    public String test(){
        return testService.stringConvert("str");
    }
}
