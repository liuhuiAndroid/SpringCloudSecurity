package com.noc.security.controller;

import com.noc.security.repository.UserRepository;
import com.noc.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User create(@RequestBody User user) {
        return user;
    }

    @PutMapping("/{id}")
    public User update(@RequestBody User user) {
        return user;
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return new User();
    }

    /**
     * http://localhost:8080/users?name=' or 1=1 or name=' 注入攻击
     */
    @GetMapping
    public List<User> query(String name) {
        String sql = "select id,name from user where name='" + name + "'";
        List data = jdbcTemplate.queryForList(sql);

        return userRepository.findByName(name);
    }

}
