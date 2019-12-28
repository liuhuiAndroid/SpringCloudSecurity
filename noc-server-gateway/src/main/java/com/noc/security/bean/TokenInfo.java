package com.noc.security.bean;

import lombok.Data;

import java.util.Date;

@Data
public class TokenInfo {

    // 令牌是否可用
    private boolean active;

    // 发给哪个客户端应用
    private String client_id;

    // Scope
    private String[] scope;

    // 令牌用户
    private String username;

    // 可访问的资源服务器Id
    private String[] aud;

    // 过期时间
    private Date exp;

    // 权限
    private String[] authorities;

}
