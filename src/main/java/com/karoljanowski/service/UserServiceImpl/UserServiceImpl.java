package com.karoljanowski.service.UserServiceImpl;

import com.karoljanowski.dao.RoleDao;
import com.karoljanowski.dao.UserDao;
import com.karoljanowski.domain.User;
import com.karoljanowski.domain.security.UserRole;
import com.karoljanowski.service.AccountService;
import com.karoljanowski.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by Karol Janowski on 2017-06-06.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AccountService accountService;

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean checkUserExists(String username, String email) {
        if (checkUsernameExists(username) || checkEmailExists(email)){
            return true;
        }
        return false;
    }

    public User createUser(User user, Set<UserRole> userRoles){
        User localUser= userDao.findByUsername(user.getUsername());
        if (localUser!=null){
            LOG.info("Username {} already exists. Nothing will be done", user.getUsername());
        } else {
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);

            for (UserRole ur : userRoles){
                roleDao.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            user.setPrimaryAccount(accountService.createPrimaryAccount());
            user.setSavingsAccount(accountService.createSavingsAccount());

            localUser = userDao.save(user);
        }
        return localUser;
    }

    @Override
    public boolean checkUsernameExists(String username) {
        if(findByUsername(username)!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkEmailExists(String email) {
        if (findByEmail(email)!=null){
            return true;
        }
        return false;
    }

    @Override
    public void save(User user) {
        userDao.save(user);

    }

    @Override
    public User findById(Long id) {
        return userDao.findOne(id);
    }
}

















