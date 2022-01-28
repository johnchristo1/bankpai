package com.bank.moneytransferapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.moneytransferapp.entity.User;

public interface GetEmailidRepository extends JpaRepository<User,Long> {
	@Query(value="select email from users u where u.userid =:userid", nativeQuery=true)
	String getemailid(@Param("userid") int userid);
}
