package com.karoljanowski.service;

import com.karoljanowski.domain.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by Karol Janowski on 2017-06-10.
 */
public interface TransactionService {
    List<PrimaryTransaction> findPrimaryTransactionList(String username);
    List<SavingsTransaction> findSavingsTransactionList(String username);
    void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);
    void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);
    void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction);
    void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction);
    void betweenAccountTransfer(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception;

    Recipient findRecipientByName (String name);
    List<Recipient> findRecipientList (Principal principal);
    void deleteRecipientByName (String name);
    void saveRecipient(Recipient recipient);
    void toSomeoneElseTransfer(Recipient recipient, String accountType, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount);

}


















