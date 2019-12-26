package com.noc.security.controller;

import com.noc.security.service.UserService;
import com.noc.security.user.User;
import com.noc.security.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserInfo create(@RequestBody @Validated UserInfo userInfo) {
        return userService.create(userInfo);
    }

    @PutMapping("/{id}")
    public UserInfo update(@RequestBody UserInfo userInfo) {
        return userService.update(userInfo);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}")
    public UserInfo get(@PathVariable Long id, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        if (user == null || !user.getId().equals(id)) {
            throw new RuntimeException("身份认证信息异常，获取用户信息失败");
        }
        return userService.get(id);
    }

    @GetMapping
    public List<UserInfo> query(String name) {
        return userService.query(name);
    }

}
