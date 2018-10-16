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
    private String CLIEN_ID;
    @Value("${backend.oauth2.client_secret}")
    private String CLIENT_SECRET;
    private static final String GRANT_TYPE = "password";
    private static final String AUTHORIZATION_CODE = "authorization_code";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String IMPLICIT = "implicit";
    private static final String SCOPE_READ = "read";
    private static final String SCOPE_WRITE = "write";
    private static final String TRUST = "trust";
    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 0;

    private final JdbcTokkenStore jdbcTokkenStore;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public OAuth2Configuration(JdbcTokkenStore jdbcTokkenStore, AuthenticationManager authenticationManager) {
        this.jdbcTokkenStore = jdbcTokkenStore;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
                .jdbc(jdbcTokkenStore.dataSource())
                .withClient(CLIEN_ID)
                .secret(CLIENT_SECRET)
                .authorizedGrantTypes(GRANT_TYPE, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT )
                .autoApprove(true)
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jdbcTokkenStore.tokenStore())
                .authenticationManager(authenticationManager);
    }

}