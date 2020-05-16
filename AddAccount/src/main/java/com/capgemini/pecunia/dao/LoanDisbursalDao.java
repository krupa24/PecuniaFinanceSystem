package com.capgemini.pecunia.dao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.pecunia.entity.LoanDisbursal;
import com.capgemini.pecunia.entity.LoanRequests;
public interface LoanDisbursalDao extends JpaRepository<LoanDisbursal, Integer> {
	@Query("select r from LoanRequests r where creditScore>670")
	ArrayList<LoanRequests> getApprovedLoans();
	@Query("select r from LoanRequests r where creditScore<=670")
	ArrayList<LoanRequests> getRejectedLoans();
	@Query("select r from LoanDisbursal r where loanStatus='accepted'")
	List<LoanDisbursal> findAllAccepted();
	@Query("select r from LoanDisbursal r where loanStatus='rejected'")
	List<LoanDisbursal> findAllRejected();
}
