package com.noc.security.server.resource;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
//import org.springframework.security.oauth2.provider.token.*;
//
//// 注释，逻辑挪到网关
////@Configuration
////@EnableWebSecurity
//public class OAuth2WebSecurity extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    UserDetailsService userDetailsService;
//
//    @Bean
//    public ResourceServerTokenServices tokenServices(){
//        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        remoteTokenServices.setClientId("orderService");
//        remoteTokenServices.setClientSecret("qwer1234");
//        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:9090/oauth/check_token");
//        // 把令牌转换成用户信息
//        remoteTokenServices.setAccessTokenConverter(getAccessTokenConverter());
//        return remoteTokenServices;
//    }
//
//    /**
//     * token转换成user对象
//     */
//    private AccessTokenConverter getAccessTokenConverter() {
//        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
//        DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
//        userAuthenticationConverter.setUserDetailsService(userDetailsService);
//        accessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
//        return accessTokenConverter;
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
//        authenticationManager.setTokenServices(tokenServices());
//        return authenticationManager;
//    }
//}
