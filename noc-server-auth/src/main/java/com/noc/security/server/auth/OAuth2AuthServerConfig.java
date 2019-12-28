package com.noc.security.server.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
// 作为授权服务器
@EnableAuthorizationServer
public class OAuth2AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    /**
     * 将Token持久化到数据库中
     *
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    /**
     * ???
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }

    public static void main(String[] args) {
        System.out.println("password:" + new BCryptPasswordEncoder().encode("qwer1234"));
    }

    /**
     * 客户端应用的详细信息
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 方案二：客户端信息持久化到数据库中
        clients.jdbc(dataSource);

        // 方案一：放在内存中 过时
//        clients.inMemory()
//                // 声明客户端应用
//                .withClient("orderApp")
//                // 客户端应用密码，需要加密
//                .secret(passwordEncoder.encode("qwer1234"))
//                // 权限
//                .scopes("read", "write")
//                // token有效期
//                .accessTokenValiditySeconds(36000)
//                // 可以访问的资源服务器
//                .resourceIds("order-server")
//                // 授权类型
//                .authorizedGrantTypes("password")
//
//                .and()
//                // 声明客户端应用
//                .withClient("orderService")
//                // 客户端应用密码，需要加密
//                .secret(passwordEncoder.encode("qwer1234"))
//                // 权限
//                .scopes("read")
//                // token有效期
//                .accessTokenValiditySeconds(36000)
//                // 可以访问的资源服务器
//                .resourceIds("order-server")
//                // 授权类型
//                .authorizedGrantTypes("password");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager);
    }
}
