package com.study.spring.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created on 2018-01-06
 *
 * @author liuzhaoyuan
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 5080232449062949693L;

    private Long id;

    // 登录帐号
    private String account;

    private String username;

    private String password;

    // 加密密码的盐
    private String salt;

    // 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 ,1:正常状态,2：用户被锁定.
    private Integer state;

    // 一个用户具有多个角色
    private List<SysRole> roleList;

    /**
     * 密码盐.
     *
     * @return
     */
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }

    public UserInfo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserInfo)) {
            return false;
        }
        UserInfo user = (UserInfo) o;
        return Objects.equals(getAccount(), user.getAccount()) &&
            Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccount(), getUsername());
    }

    @Override
    public String toString() {
        return "UserInfo{" +
            "id=" + id +
            ", account='" + account + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", salt='" + salt + '\'' +
            ", state=" + state +
            ", roleList=" + roleList +
            '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }
}
