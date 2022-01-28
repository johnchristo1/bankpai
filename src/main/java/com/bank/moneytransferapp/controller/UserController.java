package com.bank.moneytransferapp.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.List;

import javax.validation.Valid;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.moneytransferapp.entity.AddAccount;
import com.bank.moneytransferapp.entity.Deposit;
import com.bank.moneytransferapp.entity.Messages;
import com.bank.moneytransferapp.entity.Transactions;
import com.bank.moneytransferapp.entity.User;
import com.bank.moneytransferapp.repository.AddAccountnoRepository;
import com.bank.moneytransferapp.repository.DepositRepository;
import com.bank.moneytransferapp.repository.GetAccountnumberRepository;
import com.bank.moneytransferapp.repository.GetEmailidRepository;
import com.bank.moneytransferapp.repository.GetUseridRepository;
import com.bank.moneytransferapp.repository.GetamountRepository;
import com.bank.moneytransferapp.repository.TransactionRepository;
import com.bank.moneytransferapp.repository.UpdateBalanceRepository;
import com.bank.moneytransferapp.repository.UserRepository;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.*;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;




@RestController
@RequestMapping("/bankapi")
@Api(value="Banking Solutions", description="Operations for Transfer money One Account to Another Account")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository bankRepository;

	@Autowired
	private DepositRepository depositRepository;

	@Autowired
	private AddAccountnoRepository addaccountnoRepository;

	@Autowired
	private UpdateBalanceRepository updateBalance;

	@Autowired
	private TransactionRepository transactionrepository;

	@Autowired
	private GetAccountnumberRepository getaccountnumberrepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private GetamountRepository getamountrepository;

	@Autowired
	private GetUseridRepository getuseridrepository;

	@Autowired
	private GetEmailidRepository getemailidrepository;

	Deposit deposit = new Deposit();
	Messages allreports = new Messages();
	Transactions transactions = new Transactions();
	
	
	@ApiOperation(value = "View a list of available employees", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/alluser")
	
	public List<User> getAllUsers() {
		return bankRepository.findAll();
	}
	

	/**
	 * @param User to create an user by using User entity this will create a table
	 *             with name "users" which contains the details of registered user
	 * @return map json file which contains message and a status report This method
	 *         is used Here an account number and a user id is generated and given
	 *         to the user Initial balance of the account when created will be zero
	 *         Returns the response as a
	 * 
	 */

	@PostMapping("/user")
	@ApiOperation(value = "Register A User")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		Map<String, String> map = new HashMap<String, String>();
		Random random = new Random();
		int temperoryuserid = random.nextInt(899) + 100;
		Integer temperoryaccnumber = random.nextInt(8999) + 1000;
		int maximumid = 0;
		try {
			maximumid = bankRepository.maxid();
		} catch (Exception e) {

		}
		try {
			String usernewid = String.valueOf(maximumid + 1) + String.valueOf(temperoryuserid);
			String accountno = String.valueOf(maximumid + 1) + String.valueOf(temperoryaccnumber);
			user.setUserid(Integer.parseInt(usernewid));
			user.setUsername(user.getUsername());
			user.setAccounttype(user.getAccounttype());
			user.setAddress(user.getAddress());
			user.setAge(user.getAge());
			user.setBirthday(user.getBirthday());
			user.setEmail(user.getEmail());
			user.setPhonenumber(user.getPhonenumber());

			Deposit deposit = new Deposit();

			deposit.setAccountnumber(Integer.parseInt(accountno));
			deposit.setUserid(Integer.parseInt(usernewid));
			deposit.setDeposit(0);
			bankRepository.save(user);
			depositRepository.save(deposit);

			map.put(allreports.getMessage(), allreports.getNewuser());
			map.put(allreports.getStatus(), allreports.getSuccessstatus());
		}

		catch (Exception e) {
			map.put(allreports.getMessage(), allreports.getMailalreadyexist());
			map.put(allreports.getStatus(), allreports.getErrorstatus());
			logger.error(allreports.getMailalreadyexist());
		}

		return new ResponseEntity<Object>(map, HttpStatus.CREATED);
	}

	/**
	 * @param Deposit to deposit money to the above registered
	 * @return map a json file which contains message and a status report user uses
	 *         Deposit entity It also creates a table containing account number
	 *         userid and account balance of the user in the database Returns the
	 *         response as a json file which contains message and a status report
	 */
	@PutMapping("/deposit")
	@ApiOperation(value = "Deposit Amount")
	public ResponseEntity<Object> updateAmount(@RequestBody Deposit deposit) {
		updateBalance.updatebalance(deposit.getDeposit(), deposit.getAccountnumber());
		Map<String, String> map = new HashMap<String, String>();
		int searchaccountnumber = 0;
		try {
			searchaccountnumber = getaccountnumberrepository.getaccountnumber(deposit.getAccountnumber());
			int jsonaccountnumber = deposit.getAccountnumber();
			if (searchaccountnumber == jsonaccountnumber) {
				
				Transactions transactions = new Transactions();
				transactions.setDeposit(deposit.getDeposit());
				transactions.setReceiveraccountnumber(deposit.getAccountnumber());
				transactions.setSenderaccountnumber(deposit.getAccountnumber());
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				String Time = timestamp.toString();
				transactions.setTime(Time);
				transactions.setStatus(allreports.getCredited());
				transactionrepository.save(transactions);		
				map.put(allreports.getMessage(), allreports.getDepositedsuccess());
				map.put(allreports.getStatus(), allreports.getSuccessstatus());
			}
			
		} catch (Exception e) {
			map.put(allreports.getMessage(), allreports.getCheckaccountnumber());
			map.put(allreports.getStatus(), allreports.getErrorstatus());
			logger.error(allreports.getCheckaccountnumber());
		}
		return new ResponseEntity<Object>(map, HttpStatus.CREATED);
	}

	/**
	 * @param AddAccount
	 * @return map json file which contains message and a status report This method
	 *         is used to create another user to which money to be transferred Uses
	 *         AddAccount entity Returns the response as a json file which contains
	 *         message and a status report
	 */
	@PostMapping("/addaccount")
	@ApiOperation(value = "Add a Transfer account")
	public ResponseEntity<Object> createAccountno(@RequestBody AddAccount addaccountno) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			addaccountnoRepository.save(addaccountno);
			map.put(allreports.getMessage(), allreports.getAccountadded());
			map.put(allreports.getStatus(), allreports.getSuccessstatus());
		} catch (Exception e) {
			map.put(allreports.getMessage(), allreports.getMailalreadyexist());
			map.put(allreports.getStatus(), allreports.getErrorstatus());
			logger.error(allreports.getMailalreadyexist());
		}
		return new ResponseEntity<Object>(map, HttpStatus.CREATED);
	}

	/**
	 * @param Transactions
	 * @return map json file which contains message and a status report
	 */
	@PutMapping("/transferamount")
	@ApiOperation(value = " Transfer Amount")
	public ResponseEntity<Object> transfer(@RequestBody Transactions transfer) {

		Properties properties = new Properties();
		try {
			properties.load(UserController.class.getClassLoader().getResourceAsStream("kafka.properties"));
			KafkaConsumer consumer = new KafkaConsumer<>(properties);
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("IOException ", ex);
		}

		KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

		String sender = String.valueOf(transfer.getSenderaccountnumber());
		String receiver = String.valueOf(transfer.getReceiveraccountnumber());
		String amounts = String.valueOf(transfer.getDeposit());
		Map<String, String> map = new HashMap<String, String>();
		try {
			producer.send(bankTransaction(sender, amounts, receiver));
			Thread.sleep(100);
			map.put(allreports.getMessage(), allreports.getTransactionreport());
			map.put(allreports.getStatus(), allreports.getSuccessstatus());

		} catch (InterruptedException e) {
			map.put(allreports.getMessage(), allreports.getTransactioninteruptreport());
			map.put(allreports.getStatus(), allreports.getErrorstatus());
			logger.error(allreports.getTransactioninteruptreport());
		}

		producer.close();

		return new ResponseEntity<Object>(map, HttpStatus.CREATED);
	}

	public static ProducerRecord<String, String> bankTransaction(String accno, String amount, String raccno) {
		ObjectNode transaction = JsonNodeFactory.instance.objectNode();
		Instant now = Instant.now();
		transaction.put("SenderAccountnumber", accno);
		transaction.put("ReceiverAccountnumber", raccno);
		transaction.put("amount", amount);
		transaction.put("time", now.toString());
		return new ProducerRecord<>("bank1", accno, transaction.toString());
	}

	@KafkaListener(topics = "bank4")
	public void consumer(String message) throws IOException {

		JSONObject json = new JSONObject(message);
		String reportfromtransaction = json.get("Reportb").toString();
		String validss = reportfromtransaction;
		String saccountno = json.get("SenderAccountnumberb").toString();
		int sendereccno = Integer.parseInt(saccountno);
		String emailid = getemailidrepository.getemailid(getuseridrepository.getuserid(sendereccno));

		if (validss.equals(allreports.getValid())) {
			try {
				String emailreport = allreports.getEmailreport() + json.get("Amountbb") + allreports.getEmailreport2()
						+ json.get("ReceiverAccountnumber");

				sendEmail(emailreport,emailid);
			} catch (MessagingException e) {
				e.printStackTrace();
				logger.error("MessagingException ", e);
			}
		}

	}

	public void sendEmail(String rep, String mailid) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(mailid);
		msg.setSubject(allreports.getTestingtransaction());
		msg.setText(rep);
		javaMailSender.send(msg);

	}

	/**
	 * @param Deposit
	 * @return map a json file which contains message and a status report This
	 *         method uses GetMapping to fetch the balance of the user Uses Deposit
	 *         entity Returns the response as a json file which contains message and
	 *         a status report
	 */

	@GetMapping("/checkbalance")
	@ApiOperation(value = "Balance Check")
	public ResponseEntity<Object> balancec(@Valid @RequestBody Deposit deposit) {
		Map<String, String> map = new HashMap<String, String>();
		try {

			int balance = 0;
			balance = getamountrepository.getamount(deposit.getAccountnumber());

			map.put(allreports.getMessage(), allreports.getBalanceis() + balance);
			map.put(allreports.getStatus(), allreports.getSuccessstatus());

		} catch (Exception e) {
			map.put(allreports.getMessage(), allreports.getAccountnumbernotexist());
			map.put(allreports.getStatus(), allreports.getErrorstatus());
			logger.error(allreports.getAccountnumbernotexist());
		}
		return new ResponseEntity<Object>(map, HttpStatus.CREATED);
	}

}
