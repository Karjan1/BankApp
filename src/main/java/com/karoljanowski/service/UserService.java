package com.karoljanowski.service;

import com.karoljanowski.domain.User;
import com.karoljanowski.domain.security.UserRole;

import java.util.Set;

/**
 * Created by Karol Janowski on 2017-06-06.
 */
public interface UserService {
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(Long id);
    boolean checkUserExists(String username, String email);
    boolean checkUsernameExists(String username);
    boolean checkEmailExists(String email);
    void save(User user);
    User createUser(User user, Set<UserRole> userRoles);

}
