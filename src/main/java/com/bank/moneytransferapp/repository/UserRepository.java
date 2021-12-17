package com.bank.moneytransferapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bank.moneytransferapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	

}
