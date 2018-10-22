package com.viseo.starter.repository;

import com.viseo.starter.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findOneByRoleName(String role_name);
}

