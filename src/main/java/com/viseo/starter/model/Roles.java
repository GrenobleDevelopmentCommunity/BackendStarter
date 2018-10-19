package com.viseo.starter.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="roles")
public class Roles {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String roleName;

    @OneToMany(mappedBy="role")
    private Set<User> users;

    public Roles(final String roleName){
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(final Set<User> users) {
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
