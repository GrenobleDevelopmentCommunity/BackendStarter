package com.viseo.starter.web.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TokenController {
    @Qualifier("consumerTokenServices")
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @RequestMapping(value = "/oauth/token/revoke", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestParam("token") String value) throws InvalidClientException {
        consumerTokenServices.revokeToken(value);
    }
}

