package com.bank.moneytransferapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bank.moneytransferapp.entity.Transactions;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions,Long>{


}
