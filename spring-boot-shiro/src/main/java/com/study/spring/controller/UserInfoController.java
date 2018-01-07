package com.study.spring.controller;

import com.study.spring.entity.UserInfo;
import com.study.spring.service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2018-01-07
 *
 * @author liuzhaoyuan
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userService;

//    @RequestMapping("/findUserByPage")
//    @RequiresPermissions("userInfo:view") // 权限管理;
//    @ResponseBody
//    public Page<UserInfo> getStudents(Pageable pageInfo) {
//        return userService.findUserByPage(pageInfo);
//    }

    @RequestMapping("/getUserInfoById")
    @RequiresPermissions("userInfo:view")
    @ResponseBody
    protected UserInfo getUserInfoById(Long id) {
        return userService.findUserInfoById(id);
    }

    @RequestMapping("/getUserInfoByAccount")
    @RequiresPermissions("userInfo:view") // 权限管理;
    @ResponseBody
    protected UserInfo getUserInfoByAccount(String account) {
        return userService.findUserInfoByAccount(account);
    }

    @RequestMapping("/deleteUserInfoByAccount")
    @RequiresPermissions("userInfo:del") // 权限管理;
    protected void deleteUserInfoByAccount(String account) {
        userService.deleteUserInfoByAccount(account);
    }

    @RequestMapping(value = "/saveUserInfo")
    @RequiresPermissions("userInfo:add") // 权限管理;
    @ResponseBody
    protected Long saveUserInfo(UserInfo user) {
        return userService.saveUserInfo(user);
    }

    @RequestMapping(value = "/view")
    protected String view() {
        return "userInfo";
    }
}
