package com.study.spring.entity;

import java.io.Serializable;
import java.util.List;

/**
 * <p>系统角色实体类</p>
 * Created on 2018-01-06
 *
 * @author liuzhaoyuan
 */
public class SysRole implements Serializable {

    private static final long serialVersionUID = -2486008368218914070L;

    private Integer id;

    private String role;

    // 角色描述,UI界面显示使用
    private String description;

    // 是否可用,如果不可用将不会添加给用户
    private Integer available = 1;

    // 角色 -- 权限关系：多对多关系;
    private List<SysPermission> permissions;

    // 用户 - 角色关系定义;一个角色对应多个用户
    private List<UserInfo> userInfos;

    public SysRole() {
    }

    @Override
    public String toString() {
        return "SysRole{" +
            "id=" + id +
            ", role='" + role + '\'' +
            ", description='" + description + '\'' +
            ", available=" + available +
            ", permissions=" + permissions +
            ", userInfos=" + userInfos +
            '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }
}
