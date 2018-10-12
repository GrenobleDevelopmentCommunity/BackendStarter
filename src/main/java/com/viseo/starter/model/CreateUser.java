package com.viseo.starter.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonCreator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;


@Getter
@ToString
public class CreateUser {

    @NotBlank
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank
    @Size(min = 6, max = 16, message = "Password should be between 6 and 16 characters")
    private String password;

    @JsonCreator
    public CreateUser() {
    }

    @Builder
    public CreateUser(@NotBlank String email, @NotBlank String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
