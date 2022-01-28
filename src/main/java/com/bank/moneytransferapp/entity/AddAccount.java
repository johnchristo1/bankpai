package com.bank.moneytransferapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Accounts")
public class AddAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull(message = "not given")
	@Column(name = "toaccountnumber")
	private int toaccountnumber;

	@NotEmpty(message = "not given")
	@Column(name = "name")
	private String name;

	@NotEmpty(message = "not given")
	@Column(name = "bankname")
	private String bankname;

	@NotEmpty(message = "not given")
	@Column(name = "ifsccode")
	private String ifsccode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getToaccountnumber() {
		return toaccountnumber;
	}

	public void setToaccountnumber(int toaccountnumber) {
		this.toaccountnumber = toaccountnumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}

}
