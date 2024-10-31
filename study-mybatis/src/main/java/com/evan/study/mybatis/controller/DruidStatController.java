package com.evan.study.mybatis.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Druid
 *
 * @author Evan
 * @date 2022/1/21
 */
@RestController
@RequestMapping("/druid")
public class DruidStatController {

    @GetMapping("/stat")
    public Object druidStat() {
        // 该方法可以获取所有数据源的监控数据
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }
}