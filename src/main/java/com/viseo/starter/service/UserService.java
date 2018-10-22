package com.viseo.starter.service;


import com.viseo.starter.model.CreateUser;
import com.viseo.starter.model.Role;
import com.viseo.starter.model.User;
import com.viseo.starter.repository.RoleRepository;
import com.viseo.starter.repository.UserRepository;
import com.viseo.starter.security.PreAuthorizedAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User getCurrentUser(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findOneByEmail(authentication.getName());
    }

    @Transactional
    @PreAuthorizedAdmin
    public User createUser(CreateUser createUser) throws EntityNotFoundException, EntityExistsException {

        if (userRepository.existsByEmail(createUser.getEmail())) {
            throw new EntityExistsException("User" + createUser.getEmail());
        }

        User user = new User(createUser.getEmail(), createUser.getPassword());

        Role role = roleRepository.findOneByRoleName("user");
        if(role==null){
            throw new EntityNotFoundException("Role name: user Not Found");
        }
        user.setRole(role); //ASK : not sure if I have to do it here or in CreateUser
        return userRepository.save(user);
    }
}