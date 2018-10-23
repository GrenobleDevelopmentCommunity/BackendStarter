package com.viseo.starter.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        AntPathMatcher pm = new AntPathMatcher();
        boolean match = pm.match("/api/users/*", "/api/users/me"); //true
        boolean match2 = pm.match("/api/users/*", "/api/users"); // false
        boolean match3 = pm.match("/api/users/*", "/api/users/me/you"); // false
        boolean match4 = pm.match("/api/users/*/**", "/api/users/me/you"); //true
        boolean match5 = pm.match("/api/users/*/**", "/api/users/me"); // true
        boolean match6 = pm.match("/api/users/*/**", "/api/users"); // false
        http.
                anonymous().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/users").authenticated()
                .antMatchers("/api/users/*/**").authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}