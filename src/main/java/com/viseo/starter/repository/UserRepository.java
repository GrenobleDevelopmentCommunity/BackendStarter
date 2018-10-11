package com.viseo.starter.repository;

import com.viseo.starter.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findOneByEmail(String email);
    boolean existsByEmail(String email);
}
