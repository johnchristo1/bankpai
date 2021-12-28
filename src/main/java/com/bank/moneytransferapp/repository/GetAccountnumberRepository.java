package com.bank.moneytransferapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.bank.moneytransferapp.entity.Deposit;

public interface GetAccountnumberRepository extends JpaRepository<Deposit,Long>{
	
	@Query(value="select acccountnumber from bankbalace u where u.acccountnumber =:acccountnumber", nativeQuery=true)
	Integer getaccountnumber(@Param("acccountnumber") int accountnumber);
}
