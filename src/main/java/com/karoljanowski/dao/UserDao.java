package com.karoljanowski.dao;

import com.karoljanowski.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Karol Janowski on 2017-06-06.
 */
public interface UserDao extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
