package com.karoljanowski.dao;

import com.karoljanowski.domain.PrimaryAccount;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Karol Janowski on 2017-06-08.
 */
public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long> {
    PrimaryAccount findByAccountNumber (int accountNumber);
}
