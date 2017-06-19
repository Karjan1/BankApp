package com.karoljanowski.dao;

import com.karoljanowski.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Karol Janowski on 2017-06-08.
 */
public interface RoleDao extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
