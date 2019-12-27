package com.noc.security.user;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String username;

    private String password;

    private String permissions;

    public UserInfo buildInfo() {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(this, userInfo);
        return userInfo;
    }

    public boolean hasPermission(String method) {
        boolean result;
        if (StringUtils.equalsAnyIgnoreCase("get", method)) {
            result = StringUtils.contains(permissions, "r");
        } else {
            result = StringUtils.contains(permissions, "w");
        }
        return result;
    }
}
