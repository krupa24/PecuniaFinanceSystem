package com.capgemini.pecunia.service;

import java.sql.Date;
import java.util.List;

import com.capgemini.pecunia.bean.Transaction;

public interface PassbookMaintenanceService {

	List<Transaction> updatePassbook(int accountId);
	
	void updatelastUpdated(int accountId);
	
	List<Transaction> accountSummary(int accountId,Date startDate,Date endDate);

	boolean accountValidation(int accountId);
	
	
	
}
