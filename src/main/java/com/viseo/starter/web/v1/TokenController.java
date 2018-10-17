package com.viseo.starter.web.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

@RestController
public class TokenController {

    private final ConsumerTokenServices consumerTokenServices;

    @Autowired
    public TokenController(@Qualifier("consumerTokenServices") ConsumerTokenServices consumerTokenServices) {
        this.consumerTokenServices = consumerTokenServices;
    }

    @PostMapping("/oauth/token/revoke")
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestParam("token") String value) throws InvalidClientException {
        consumerTokenServices.revokeToken(value);
    }
}

