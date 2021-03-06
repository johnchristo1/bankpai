package com.bank.moneytransferapp.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bank.moneytransferapp.entity.Deposit;

@Repository
public interface UpdateBalanceRepository extends JpaRepository<Deposit, Long>{
	@Transactional
	@Modifying
	@Query("UPDATE Deposit SET deposit = deposit + :deposit WHERE accountnumber = :accountnumber")
	Integer updatebalance(int deposit, int accountnumber);


}
