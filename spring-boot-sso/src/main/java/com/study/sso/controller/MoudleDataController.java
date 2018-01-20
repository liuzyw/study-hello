package com.study.sso.controller;

import com.study.sso.service.MoudleDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018-01-20
 *
 * @author liuzhaoyuan
 */
@RestController
public class MoudleDataController {

    @Autowired
    private MoudleDataSourceService moudleDataSourceService;


    @RequestMapping("/findData")
    public String findDate() {
        moudleDataSourceService.findDate();
        return "done data";
    }
}
