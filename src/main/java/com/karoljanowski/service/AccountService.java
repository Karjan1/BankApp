package com.karoljanowski.service;

import com.karoljanowski.domain.PrimaryAccount;
import com.karoljanowski.domain.SavingsAccount;

import java.security.Principal;

/**
 * Created by Karol Janowski on 2017-06-08.
 */
public interface AccountService {
    PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();
    void deposit(String accountType, double amount, Principal principal);
    void withdraw(String accountType, double amount, Principal principal);
}
