package com.bank.moneytransferapp.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.moneytransferapp.entity.AddAccountno;
import com.bank.moneytransferapp.entity.Deposit;
import com.bank.moneytransferapp.entity.Transactions;
import com.bank.moneytransferapp.entity.User;
import com.bank.moneytransferapp.repository.AddAccountnoRepository;
import com.bank.moneytransferapp.repository.DepositRepository;
import com.bank.moneytransferapp.repository.GetAccountnumberRepository;
import com.bank.moneytransferapp.repository.TransactionRepository;
import com.bank.moneytransferapp.repository.UpdateBalanceRepository;
import com.bank.moneytransferapp.repository.UserRepository;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/bankapi")
public class UserController {
	@Autowired
	private UserRepository bankRepository;
 
		// Register a User
		@PostMapping("/user")
		public String createUser(@RequestBody User user) {
	    	bankRepository.save(user);
			return "User account created";
		}
	
	@Autowired
	private DepositRepository depositRepository;
		
		//Deposit amount and add user account
		@PostMapping("/addamount")
		public String depositAmount(@RequestBody Deposit deposit) {
			depositRepository.save(deposit);
			return "added Rs."+ deposit.getBalance();
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
    	      transactions.setSenderaccountnumber(deposit.getAccountnumber());
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
        
        @Autowired
		private AddAccountnoRepository addaccountnoRepository;
        
//		 create useraccountno
		@PostMapping("/addaccountno")
		public AddAccountno createAccountno(@RequestBody AddAccountno addaccountno) {
			return this.addaccountnoRepository.save(addaccountno);
		}
		
		
		@PutMapping("/transferamount")
		public String transfer(@RequestBody Transactions transfer) {
			
			//Kafka Producer
			
			Properties properties = new Properties();

    		  // kafka bootstrap server
    		  properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
    		  properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    		  properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    		  // producer acks
    		  properties.setProperty(ProducerConfig.ACKS_CONFIG, "all"); // strongest producing guarantee
    		  properties.setProperty(ProducerConfig.RETRIES_CONFIG, "3");
    		  properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "1");
    		  // leverage idempotent producer from Kafka 0.11 !
    		  properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true"); // ensure we don't push duplicates

    		  Producer<String, String> producer = new KafkaProducer<>(properties);
    		 
 
			String sender=String.valueOf(transfer.getSenderaccountnumber());
			String receiver=String.valueOf(transfer.getReceiveraccountnumber());
			String amounts=String.valueOf(transfer.getAmount());
			
    		  try {
		          producer.send(bankTransaction(sender,amounts,receiver));
		          Thread.sleep(100);


		      } catch (InterruptedException e) {

		      }

		  producer.close();

			return "checking";
		}
		
		
		public static ProducerRecord<String, String> bankTransaction(String accno,String amount,String raccno) {
	        // creates an empty json {}
	        ObjectNode transaction = JsonNodeFactory.instance.objectNode();			      
	        // Instant.now() is to get the current time using Java 8
	        Instant now = Instant.now();

	        // we write the data to the json document
	        transaction.put("SenderAccountnumber", accno);
	        transaction.put("ReceiverAccountnumber", raccno);
	        transaction.put("amount", amount);
	        transaction.put("time", now.toString());
	        return new ProducerRecord<>("bank1", accno, transaction.toString());
	    }
		
       	
		
}
