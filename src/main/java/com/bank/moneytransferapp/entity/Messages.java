package com.bank.moneytransferapp.entity;

public class Messages {
	public String message = "message";
	public String status = "status";
	public String errorstatus = "404";
	public String successstatus = "202";
	// 202
	public String newuser = "New user created succesfully";
	public String depositedsuccess = "Amount deposited succesfully";
	public String transactionreport = "transaction initialized, You will get a mail when the transaction is complete";
	public String balanceis = "Balance is ";
	public String emailreport = "You transaction has been successfully completed. The amount ";
	public String emailreport2 = " is credited to the account number ";
	// 404
	public String accountnumbernotexist = "Account number doesn't exist";
	public String transactioninteruptreport = "Transaction interupted";
	public String accountadded = "Account added succesfully";
	public String mailalreadyexist = "Email id Or Phonenumber already exist";
	public String checkaccountnumber = "Please check accountnumber";

	public String credited = "credited";
	public String valid = "valid";
	public String testingtransaction = "Testing Transaction ";

	public String getEmailreport2() {
		return emailreport2;
	}

	public String getCredited() {
		return credited;
	}

	public String getValid() {
		return valid;
	}

	public String getTestingtransaction() {
		return testingtransaction;
	}

	public String getEmailreport() {
		return emailreport;
	}

	public String getMessage() {
		return message;
	}

	public String getStatus() {
		return status;
	}

	public String getErrorstatus() {
		return errorstatus;
	}

	public String getSuccessstatus() {
		return successstatus;
	}

	public String getNewuser() {
		return newuser;
	}

	public String getDepositedsuccess() {
		return depositedsuccess;
	}

	public String getTransactionreport() {
		return transactionreport;
	}

	public String getBalanceis() {
		return balanceis;
	}

	public String getAccountnumbernotexist() {
		return accountnumbernotexist;
	}

	public String getTransactioninteruptreport() {
		return transactioninteruptreport;
	}

	public String getAccountadded() {
		return accountadded;
	}

	public String getMailalreadyexist() {
		return mailalreadyexist;
	}

	public String getCheckaccountnumber() {
		return checkaccountnumber;
	}


}
