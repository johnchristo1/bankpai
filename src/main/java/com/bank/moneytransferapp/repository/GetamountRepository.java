package com.bank.moneytransferapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.moneytransferapp.entity.Deposit;

public interface GetamountRepository extends JpaRepository<Deposit, Long>{
	@Query(value="select deposit from accountbalace u where u.accountnumber =:Bnumber", nativeQuery=true)
	Integer getamount(@Param("Bnumber") int Bnumber);

}
