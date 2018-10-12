package com.viseo.starter.web.v1;

import com.viseo.starter.model.CreateUser;
import com.viseo.starter.model.User;
import com.viseo.starter.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Get all users")
    @ApiResponses({
    })
    @GetMapping
    public Iterable<User> user() {
        return userService.getAll();
    }

    @ApiOperation(value = "Create a user")
    @ApiResponses({
            @ApiResponse(code = 409, message = "If user already exists")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createUser(@RequestBody @Valid CreateUser newUser) throws EntityNotFoundException {
        try {
            return new ResponseEntity(userService.createUser(newUser), HttpStatus.CREATED);
        }
        catch (EntityExistsException e)
        {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

    }

}
