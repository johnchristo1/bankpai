package com.bank.moneytransferapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotEmpty(message = "not given")
	@Column(name = "username")
	private String username;

	@NotEmpty(message = "not given")
	@Column(name = "age")
	private String age;

	@NotEmpty(message = "not given")
	@Column(name = "birthday")
	private String birthday;

	@NotEmpty(message = "not given")
	@Size(min = 10, max = 10, message = "Please provide a valid phone number")
	@Column(name = "phonenumber", unique = true)
	private String phonenumber;

	@NotEmpty(message = "not given")
	@Email(message = "Please provide a valid email address")
	@Column(name = "email", unique = true)
	private String email;

	@NotEmpty(message = "not given")
	@Column(name = "address")
	private String address;

	@NotEmpty(message = "not given")
	@Column(name = "accounttype")
	private String accounttype;

	@Column(name = "userid")
	private int userid;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

}
