package com.firstapplication.BankingApplication.Controller;

import com.firstapplication.BankingApplication.Model.Account;
import com.firstapplication.BankingApplication.Model.Transaction;
import com.firstapplication.BankingApplication.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/addAccount")
    public String addAccount(@RequestBody Account account)
    {
        return accountService.addAccountDetails(account);
    }

    @PutMapping("/withdraw/{id}")
    public String withDraw(@RequestParam("amount") BigDecimal amount, @PathVariable int id)
    {
        return accountService.withDrawDetails(amount, id);
    }

    @PutMapping("/deposit/{id}")
    String deposit(@RequestParam("amount") BigDecimal amount, @PathVariable int id)
    {
        return accountService.depositAmount(amount, id);
    }

    @GetMapping("/getTransaction/{id}")
    public List<Transaction> getTransaction(@PathVariable int id)
    {
        return accountService.getTransactionDetails(id);
    }

    @GetMapping("/getTransactionByIdType/{id}/{typee}")
    public List<Transaction> getTransactionByIDType(@PathVariable int id, @PathVariable String typee)
    {
        return accountService.getTransactionDetailsByIdType(id,typee);
    }

    @GetMapping("/getTransactionGreaterThan/{id}/{amount}")
    public List<Transaction> getTransactionGreaterThan(@PathVariable int id,@PathVariable int amount)
    {
        return accountService.getTransactionGreaterThanDetails(id,amount);
    }
}
