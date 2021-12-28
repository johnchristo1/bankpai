package com.bank.moneytransferapp.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bank.moneytransferapp.entity.Deposit;
import com.bank.moneytransferapp.entity.Transactions;
import com.bank.moneytransferapp.entity.User;
import com.bank.moneytransferapp.repository.DepositRepository;
import com.bank.moneytransferapp.repository.GetAccountnumberRepository;
import com.bank.moneytransferapp.repository.TransactionRepository;
import com.bank.moneytransferapp.repository.UpdateBalanceRepository;
import com.bank.moneytransferapp.repository.UserRepository;

@RestController
@RequestMapping("/bankapi")
public class UserController {
	@Autowired
	private UserRepository bankRepository;
 
		// Register a User
		@PostMapping("/user")
		public User createUser(@RequestBody User user) {
	    	bankRepository.save(user);
			return user;
		}
	
	@Autowired
	private DepositRepository depositRepository;
		
		//Deposit amount and add user account
		@PostMapping("/addamount")
		public Deposit depositAmount(@RequestBody Deposit deposit) {
			depositRepository.save(deposit);
			return deposit;
		  }	
		
		
    @Autowired
	private UpdateBalanceRepository updateBalance;
    
    @Autowired
    private TransactionRepository transactionrepository;
    
    @Autowired
    private GetAccountnumberRepository getaccountnumberrepository;
    
    
      
       //update balance
        @PutMapping("/deposit")
        public String updateAmount(@RequestBody Deposit deposit) {
    	      updateBalance.updatebalance(deposit.getBalance(), deposit.getAccountnumber());
    	     
    	     int searchaccountnumber = 0;
    	     try {
    	    	 searchaccountnumber =  getaccountnumberrepository.getaccountnumber(deposit.getAccountnumber());
    	    	 
    	     }
    	     catch(Exception e){System.out.println(e.getMessage());
    	    	 
    	     }
    	     
    	     int jsonaccountnumber = deposit.getAccountnumber();
    	     if(searchaccountnumber == jsonaccountnumber) {
    	      
    	      Transactions transactions = new Transactions();
    	      transactions.setAmount(deposit.getBalance());
    	      transactions.setReceiveraccountnumber(deposit.getAccountnumber());
    	      transactions.setSenteraccountnumber(deposit.getAccountnumber());
    	      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	      String Time = timestamp.toString();
    	      transactions.setTime(Time);
    	      transactions.setStatus("credited");
    	      transactionrepository.save(transactions);
    	      return "credited";
    	     }
    	     
    	     else {
    	 
    	 return "Please check accountnumber";   	 
    	    	 
    	     }

    }
       	
		
}
