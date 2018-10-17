package com.viseo.starter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

    @Value("${backend.oauth2.client_id}")
    private String client_id;
    @Value("${backend.oauth2.client_secret}")
    private String client_secret;

    private final JdbcTokkenStore jdbcTokkenStore;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public OAuth2Configuration(JdbcTokkenStore jdbcTokkenStore, AuthenticationManager authenticationManager) {
        this.jdbcTokkenStore = jdbcTokkenStore;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer.inMemory()
                .withClient(client_id)
                .secret(client_secret)
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("read", "write");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jdbcTokkenStore.tokenStore())
                .authenticationManager(authenticationManager);
    }

}