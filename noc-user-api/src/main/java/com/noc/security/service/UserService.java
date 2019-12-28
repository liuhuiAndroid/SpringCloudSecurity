package com.noc.security.service;

import com.noc.security.user.UserInfo;

import java.util.List;

public interface UserService {

    UserInfo create(UserInfo userInfo);

    UserInfo update(UserInfo userInfo);

    void delete(Long id);

    UserInfo get(Long id);

    List<UserInfo> query(String name);

    UserInfo login(UserInfo userInfo);
}
