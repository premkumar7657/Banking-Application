package com.firstapplication.BankingApplication.Repository;

import com.firstapplication.BankingApplication.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionalRespository extends JpaRepository<Transaction,Integer>{

    @Query("Select t from Transaction t where t.account.id =:id")
    List<Transaction> getTransactionHistory(int id);

    @Query("select t from Transaction t where t.account.id = :id AND t.type=:typee")
    List<Transaction> getTransactionHistoryByIdType(int id, String typee);

    @Query("select t from Transaction t where t.account.id=:id AND t.amount > :amount")
    List<Transaction> getTransactionHistoryGreaterThan(int id, int amount);
}
