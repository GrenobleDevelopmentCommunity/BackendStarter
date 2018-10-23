package com.viseo.starter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="roles")
public class Role implements Serializable {


    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotEmpty
    @Column(name="role_name", length = 10, unique = true, nullable = false)
    private String role;


    @OneToMany(mappedBy="role")
    @JsonIgnore
    private Set<User> users;

    public Role(){}

    public String getRole() {
        return role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public int getId() {
        return id;
    }


}
