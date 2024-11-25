package com.firstapplication.BankingApplication.Services;

import com.firstapplication.BankingApplication.Model.Account;
import com.firstapplication.BankingApplication.Model.Transaction;
import com.firstapplication.BankingApplication.Repository.AccountRepository;
import com.firstapplication.BankingApplication.Repository.TransactionalRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionalRespository transactionalRespository;

    public String addAccountDetails(Account account) {
        accountRepository.save(account);
        return "Account Saved Successfully";
    }

    public String withDrawDetails(BigDecimal amount, int id) {
          Account accountDetails =accountRepository.findById(id).orElse(null); //in order to check whether data is present or not , we have to retrieve correspoding id row data need to save in account type reference, from that we can get values of current row datat using getters,setters
          if(accountDetails==null)
              return "Account not found..!";
          if(accountDetails.getBalance().compareTo(amount)<0) // both type or object , so we cant use primitive type comparators like <><=>===
             return "Insufficient balance..! Your Account Balance is  " + accountDetails.getBalance();

          accountDetails.setBalance(accountDetails.getBalance().subtract(amount));
          accountRepository.save(accountDetails);
          //Transaction table - save deposit amount transaction table
          Transaction transaction = new Transaction();
          transaction.setAccount(accountDetails);
          transaction.setTransactionTime(LocalDateTime.now());
          transaction.setAmount(amount);
          transaction.setType("Withdraw");
          transactionalRespository.save(transaction);

          return "Withdraw done..! Your cuurent Balance is : "+accountDetails.getBalance();
    }

    public String depositAmount(BigDecimal amount, int id) {
        Account accountDetails = accountRepository.findById(id).orElse(null);
        if(accountDetails==null)
            return "Account Not Found..!";
        accountDetails.setBalance(accountDetails.getBalance().add(amount));
        accountRepository.save(accountDetails);

        //Transaction table - save withdraw amount transaction table
        Transaction transaction = new Transaction();
        transaction.setAccount(accountDetails);
        transaction.setTransactionTime(LocalDateTime.now());
        transaction.setAmount(amount);
        transaction.setType("Deposit");
        transactionalRespository.save(transaction);

        return "Deposit successfully..! Now your Account balance is " +accountDetails.getBalance() + "\n Thank you..!!!";


    }

    public List<Transaction> getTransactionDetails(int id) {
        return transactionalRespository.getTransactionHistory(id);
    }

    public List<Transaction> getTransactionDetailsByIdType(int id, String typee) {
        return transactionalRespository.getTransactionHistoryByIdType(id,typee);
    }

    public List<Transaction> getTransactionGreaterThanDetails(int id, int amount) {
        return transactionalRespository.getTransactionHistoryGreaterThan(id,amount);
    }
}
