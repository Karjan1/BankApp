package com.karoljanowski.service.UserServiceImpl;

import com.karoljanowski.dao.*;
import com.karoljanowski.domain.*;
import com.karoljanowski.service.TransactionService;
import com.karoljanowski.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * Created by Karol Janowski on 2017-06-10.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    UserService userService;

    @Autowired
    PrimaryTransactionDao primaryTransactionDao;

    @Autowired
    SavingsTransactionDao savingsTransactionDao;

    @Autowired
    PrimaryAccountDao primaryAccountDao;

    @Autowired
    SavingsAccountDao savingsAccountDao;

    @Autowired
    RecipientDao recipientDao;

    public List<PrimaryTransaction> findPrimaryTransactionList(String username){
        User user = userService.findByUsername(username);
        List<PrimaryTransaction> primaryTransactionList = user.getPrimaryAccount().getPrimaryTransactionList();
        return primaryTransactionList;
    }

    public List<SavingsTransaction> findSavingsTransactionList(String username){
        User user = userService.findByUsername(username);
        List<SavingsTransaction> savingsTransactionList = user.getSavingsAccount().getSavingsTransactionList();
        return savingsTransactionList;
    }

    public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction){
        primaryTransactionDao.save(primaryTransaction);
    }

    public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction){
        savingsTransactionDao.save(savingsTransaction);
    }

    public void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction){
        primaryTransactionDao.save(primaryTransaction);
    }

    public void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction){
        savingsTransactionDao.save(savingsTransaction);
    }

    public void betweenAccountTransfer(String transferFrom, String transferTo, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception{
        if (transferFrom.equalsIgnoreCase("Primary") && transferTo.equalsIgnoreCase("Savings")){
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date,"Transfer from Primary to Savings", "Account","Finished",Double.parseDouble(amount), primaryAccount.getAccountBalance(), primaryAccount);
            SavingsTransaction savingsTransaction = new SavingsTransaction(date,"Transfer from Primary to Savings", "Account","Finished",Double.parseDouble(amount), savingsAccount.getAccountBalance(), savingsAccount);
            primaryTransactionDao.save(primaryTransaction);
            savingsTransactionDao.save(savingsTransaction);
        } else if (transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("Primary")){
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date,"Transfer from Savings to Primary", "Account","Finished",Double.parseDouble(amount), primaryAccount.getAccountBalance(), primaryAccount);
            SavingsTransaction savingsTransaction = new SavingsTransaction(date,"Transfer from Savings to Primary", "Account","Finished",Double.parseDouble(amount), savingsAccount.getAccountBalance(), savingsAccount);
            primaryTransactionDao.save(primaryTransaction);
            savingsTransactionDao.save(savingsTransaction);
        } else throw new Exception("Invalid transfer");
    }

    @Override
    public Recipient findRecipientByName(String name) {
        return recipientDao.findByName(name);
    }

    @Override
    public List<Recipient> findRecipientList(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return recipientDao.findAllByUser(user);
    }

    @Override
    @Transactional
    public void deleteRecipientByName(String name) {
        recipientDao.deleteByName(name);
    }

    @Override
    public void saveRecipient(Recipient recipient) {
        recipientDao.save(recipient);
    }

    @Override
    public void toSomeoneElseTransfer(Recipient recipient, String accountType, String amount, PrimaryAccount primaryAccount, SavingsAccount savingsAccount) {
        if (accountType.equalsIgnoreCase("Primary")){
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);

            Date date = new Date();

            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Transfer to recipient "+recipient.getName(), "Transfer", "Finished", Double.parseDouble(amount), primaryAccount.getAccountBalance(), primaryAccount);
            primaryTransactionDao.save(primaryTransaction);
        } else if (accountType.equalsIgnoreCase("Savings")){
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccountDao.save(savingsAccount);

            Date date = new Date();

            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Transfer to recipient "+recipient.getName(), "Transfer", "Finished", Double.parseDouble(amount), savingsAccount.getAccountBalance(), savingsAccount);
            savingsTransactionDao.save(savingsTransaction);
        }
    }
}


















