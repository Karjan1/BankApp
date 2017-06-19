package com.karoljanowski.dao;

import com.karoljanowski.domain.SavingsAccount;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Karol Janowski on 2017-06-08.
 */
public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long> {
    SavingsAccount findByAccountNumber(int accountNumber);
}
