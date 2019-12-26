package com.noc.security.user;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactoryUtils;

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

    public UserInfo buildInfo() {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(this, userInfo);
        return userInfo;
    }
}
