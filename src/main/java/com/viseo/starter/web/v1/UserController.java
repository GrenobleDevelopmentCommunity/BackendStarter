package com.viseo.starter.web.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.viseo.starter.web.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    public UserController() {
    }

    @GetMapping("/me")
    @JsonView(Views.Summary.class)
    public String user() {
        return "hello";
    }

}
