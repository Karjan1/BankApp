package com.karoljanowski.dao;

import com.karoljanowski.domain.SavingsTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Karol Janowski on 2017-06-10.
 */
public interface SavingsTransactionDao extends CrudRepository<SavingsTransaction, Long> {
    List<SavingsTransaction> findAll();
}
