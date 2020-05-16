package com.capgemini.pecunia.dao;
import java.sql.Date;
import java.util.List;

import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.pecunia.entity.Account;
import com.capgemini.pecunia.entity.Transaction;

@Repository 
public interface PassbookMaintenanceDAO extends JpaRepository<Account, String> {
	 
	
	
	@Query("select t from Transaction t where accId=?1 and t.dateOfTrans<=(select lastUpdated from Account where accountNumber=?1) ")
	List<Transaction> updatePassbook(int accountId);

	@Modifying
	@Query("update Account set lastUpdated=?2 where  accountNumber=?1")
	void update(int accountId, Date date);
	
	@Query("select s from Transaction s where accId=?1 and s.dateOfTrans>?2 and s.dateOfTrans<?3")
	List<Transaction> accountSummary(int accountId, Date startDate,Date endDate);
	
	@Query("select u from Account u where accountNumber=?1")
	Account get(int accountId);


}
