package com.noc.security.user;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

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

    private String permissions;

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
