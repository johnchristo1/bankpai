package com.bank.moneytransferapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.moneytransferapp.entity.Deposit;


@Repository
public interface GetAccountnumberRepository extends JpaRepository<Deposit,Long>{
	
	@Query(value="select accountnumber from accountbalace u where u.accountnumber =:accountnumber", nativeQuery=true)
	Integer getaccountnumber(@Param("accountnumber") int accountnumber);
	
}
