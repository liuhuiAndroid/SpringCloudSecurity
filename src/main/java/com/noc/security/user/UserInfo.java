package com.noc.security.user;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
public class UserInfo {

    private Long id;

    // @NotBlank(message = "昵称不能为空")
    private String name;

    // 框架层面的校验
    @NotBlank(message = "用户名不能为空")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

}
