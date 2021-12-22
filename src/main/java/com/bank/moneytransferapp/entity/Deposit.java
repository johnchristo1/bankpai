package com.bank.moneytransferapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bankbalace")
public class Deposit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="balance")
	private int balance;
	
	@Column(name = "userid")
	private int userid;
	
//	@Column(name = "acccountnumber")
//	private String accountnumber;
//	
//	

//	public Deposit(String accountnumber) {
//		super();
//		this.accountnumber = accountnumber;
//	}
//
//	public String getAccountnumber() {
//		return accountnumber;
//	}
//
//	public void setAccountnumber(String accountnumber) {
//		this.accountnumber = accountnumber;
//	}

	public Deposit(int balance, int userid) {
		super();
		this.balance = balance;
		this.userid = userid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	
}
