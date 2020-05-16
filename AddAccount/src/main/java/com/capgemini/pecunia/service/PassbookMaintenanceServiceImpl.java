package com.capgemini.pecunia.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.pecunia.dao.AccountDaoImpl;
import com.capgemini.pecunia.dao.PassbookMaintenanceDAO;
import com.capgemini.pecunia.entity.Account;
import com.capgemini.pecunia.entity.Transaction;


@Service
@Transactional
public class PassbookMaintenanceServiceImpl implements PassbookMaintenanceService {

	@Autowired
	private PassbookMaintenanceDAO dao;
	@Autowired
	AccountDaoImpl adao;

	Account account=new Account();
	long millis=System.currentTimeMillis();  
	Date date=new Date(millis); 
	
	
	
public List<Transaction> updatePassbook(int accountId){
		
		List<Transaction> result=dao.updatePassbook(accountId);
		if(result==null)
		{
			return null;
		}
		else {
			updatelastUpdated(accountId);
			return result;	
		}
}
@Override
public void updatelastUpdated(int accountId) {
	dao.update(accountId,date);
}
	
	
	@Override
	public List<Transaction> accountSummary(int accountId, Date startDate, Date endDate) {
		return dao.accountSummary(accountId, startDate, endDate);
	}

	//Implementation of Account validation method. 
		@Override
		public boolean accountValidation(int accountId) {
			Account account=adao.getAccountByAccnum(accountId);
			if(account==null) {
				return false;
			}
			else {
				return true;
			}
			
		}
		
}
