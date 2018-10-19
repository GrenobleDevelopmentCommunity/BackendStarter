package com.viseo.starter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = -4159081637262539147L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    private String email;

    @JsonIgnore
    private String password;

    @ManyToOne
    @JoinColumn(name="roleID", referencedColumnName = "id",  nullable=false)
    private Roles role;


    public User() {
    }

    @Builder
    public User(String email, String password) {
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

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
