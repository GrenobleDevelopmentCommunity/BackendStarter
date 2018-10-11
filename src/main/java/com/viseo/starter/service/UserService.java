package com.viseo.starter.service;


import com.viseo.starter.model.CreateUser;
import com.viseo.starter.model.User;
import com.viseo.starter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User getCurrentUser(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findOneByEmail(authentication.getName());
    }

    @Transactional
    public User createUser(CreateUser createUser) throws EntityNotFoundException, EntityExistsException {

        if (userRepository.existsByEmail(createUser.getEmail())) {
            throw new EntityExistsException("User" + createUser.getEmail());
        }

        User user = new User(createUser.getEmail(), createUser.getPassword());
        return userRepository.save(user);
    }
}