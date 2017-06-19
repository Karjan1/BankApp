package com.karoljanowski.dao;

import com.karoljanowski.domain.PrimaryTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Karol Janowski on 2017-06-10.
 */
public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long> {
    List<PrimaryTransaction> findAll();
}
