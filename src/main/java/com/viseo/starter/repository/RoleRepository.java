package com.viseo.starter.repository;

import com.viseo.starter.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findOneByRole(String role_name);
}

