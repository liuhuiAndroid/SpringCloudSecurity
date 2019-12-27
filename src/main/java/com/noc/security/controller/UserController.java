package com.noc.security.controller;

import com.noc.security.service.UserService;
import com.noc.security.user.User;
import com.noc.security.user.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    /**
     * 测试：http://localhost:8080/users/login?username=qwer&password=1234
     */
    @GetMapping("/login")
    public void login(@Validated UserInfo userInfo, HttpServletRequest request) {
        UserInfo newUserInfo = userService.login(userInfo);
        // 登录服务验证用户密码后，生成一个有时效的sessionId，同时在内存中创建一个空间（session）来存放与sessionId相关的信息
        // 返回一个Set-Cookie的header，将sessionId存入浏览器
        HttpSession httpSession = request.getSession(false);
        if(httpSession != null){
            // 防止Session Fixation攻击
            httpSession.invalidate();
        }
        httpSession.setAttribute("user", newUserInfo);
    }
}
