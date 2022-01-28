
package com.bank.moneytransferapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.moneytransferapp.entity.Deposit;

public interface GetUseridRepository extends JpaRepository<Deposit,Long>{
	@Query(value="select userid from accountbalace u where u.accountnumber =:accountnumber", nativeQuery=true)
	Integer getuserid(@Param("accountnumber") int accountnumber);
}

