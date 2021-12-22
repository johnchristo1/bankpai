package com.bank.moneytransferapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.moneytransferapp.entity.Deposit;

@Repository
public interface DepositRepository extends JpaRepository<Deposit,Long>{

}
