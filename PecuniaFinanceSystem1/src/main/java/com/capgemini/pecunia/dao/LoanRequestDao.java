package com.capgemini.pecunia.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.pecunia.bean.Account;

import com.capgemini.pecunia.bean.LoanRequests;
@Repository
public interface LoanRequestDao extends JpaRepository<LoanRequests, Integer>{
	@Query("select det from Account det where accountNumber=?1")
	Optional<Account> findBank(@Param("c") int s1);
}
