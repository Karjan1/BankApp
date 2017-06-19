package com.karoljanowski.dao;

import com.karoljanowski.domain.Recipient;
import com.karoljanowski.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Karol Janowski on 2017-06-08.
 */
public interface RecipientDao extends CrudRepository<Recipient, Long> {
    Recipient findByName (String name);
    List<Recipient> findAllByUser (User user);
    void deleteByName (String name);
}
