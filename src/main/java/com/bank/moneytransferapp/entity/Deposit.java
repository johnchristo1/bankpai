package com.bank.moneytransferapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "accountbalace")
public class Deposit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull(message = "not given")
	@Column(name = "deposit")
	private int deposit;

	@NotNull(message = "not given")
	@Column(name = "accountnumber")
	private int accountnumber;

	@Column(name = "userid")
	private int userid;

	public Deposit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public int getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(int accountnumber) {
		this.accountnumber = accountnumber;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
