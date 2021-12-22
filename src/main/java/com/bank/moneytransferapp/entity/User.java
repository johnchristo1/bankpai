package com.bank.moneytransferapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 private long id;
	
	@Column(name = "username")
	 private String username;
	
	@Column(name = "age")
	 private String age;
	
	@Column(name = "birthday")
	 private String birthday;
	
	@Column(name = "phonenumber", unique = true)
	 private String phonenumber;
	
	@Column(name = "email",unique = true)
	 private String email;
	
	@Column(name = "address")
	 private String address;
	

	 
	 
	public User(String username, String age, String birthday, String phonenumber, String email, String address) {
		super();
		this.username = username;
		this.age = age;
		this.birthday = birthday;
		this.phonenumber = phonenumber;
		this.email = email;
		this.address = address;
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
	 
	 
	 

}
