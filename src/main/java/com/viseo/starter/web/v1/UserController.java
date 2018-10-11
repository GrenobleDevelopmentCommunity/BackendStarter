package com.viseo.starter.web.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.viseo.starter.model.CreateUser;
import com.viseo.starter.model.User;
import com.viseo.starter.service.UserService;
import com.viseo.starter.web.Views;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PostRemove;
import javax.validation.Valid;
import javax.xml.ws.http.HTTPException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public UserController() {
    }

    @ApiOperation(value = "Get all users")
    @ApiResponses({
    })
    @GetMapping("/")
    @JsonView(Views.Summary.class)
    public Iterable<User> user() {
        return userService.getAll();
    }

    @ApiOperation(value = "Create a user")
    @ApiResponses({
            @ApiResponse(code = 409, message = "If user already exists")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody @Valid CreateUser newUser) throws EntityNotFoundException {
        try {
            return new ResponseEntity(userService.createUser(newUser), HttpStatus.CREATED);
        }
        catch (EntityExistsException e)
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

    }

}
