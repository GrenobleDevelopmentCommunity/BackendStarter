package com.viseo.starter.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Primary
@Service
public class CustomAuthorizationServerTokenService implements AuthorizationServerTokenServices, ConsumerTokenServices {

    @Autowired
    private JdbcTokkenStore jdbcTokkenStore;

    @Transactional
    @Override
    public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
        OAuth2AccessToken accessToken = (OAuth2AccessToken)(new DefaultOAuth2AccessToken(UUID.randomUUID().toString()));
        this.jdbcTokkenStore.tokenStore().storeAccessToken(accessToken, authentication);
        return accessToken;
    }

    @Transactional
    @Override
    public OAuth2AccessToken refreshAccessToken(String s, TokenRequest tokenRequest) throws AuthenticationException {
        return null;
    }

    @Transactional
    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication oAuth2Authentication) {
        return this.jdbcTokkenStore.tokenStore().getAccessToken(oAuth2Authentication);
    }

    @Override
    public boolean revokeToken(String s) {
        OAuth2AccessToken token = this.jdbcTokkenStore.tokenStore().readAccessToken(s);
        this.jdbcTokkenStore.tokenStore().removeAccessToken(token);
        return true;
    }
}