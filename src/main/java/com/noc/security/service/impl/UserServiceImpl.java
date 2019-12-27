package com.noc.security.service.impl;

import com.lambdaworks.crypto.SCryptUtil;
import com.noc.security.repository.UserRepository;
import com.noc.security.service.UserService;
import com.noc.security.user.User;
import com.noc.security.user.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    public UserInfo create(@RequestBody UserInfo userInfo) {
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        user.setPassword(SCryptUtil.scrypt(user.getPassword(), 32768, 8, 1));
        userRepository.save(user);
        userInfo.setId(user.getId());
        return userInfo;
    }

    public UserInfo update(@RequestBody UserInfo userInfo) {
        return new UserInfo();
    }

    public void delete(@PathVariable Long id) {

    }

    public UserInfo get(@PathVariable Long id) {
        return userRepository.findById(id).get().buildInfo();
    }

    /**
     * http://localhost:8080/users?name=' or 1=1 or name=' 注入攻击
     */
    public List<UserInfo> query(String name) {
//        String sql = "select id,name from user where name='" + name + "'";
//        List data = jdbcTemplate.queryForList(sql);
//        return userRepository.findByName(name);
        return null;
    }

}
