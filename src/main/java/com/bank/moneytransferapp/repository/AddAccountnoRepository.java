package com.bank.moneytransferapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.moneytransferapp.entity.AddAccount;

@Repository
public interface AddAccountnoRepository extends JpaRepository<AddAccount, Long>{

}
