package com.bank.moneytransferapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.moneytransferapp.entity.User;
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

}
