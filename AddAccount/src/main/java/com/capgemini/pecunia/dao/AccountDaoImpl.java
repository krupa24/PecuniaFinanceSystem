package com.capgemini.pecunia.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.pecunia.entity.Account;
@Repository
public class AccountDaoImpl implements AccountDao {

	@PersistenceContext	
	 EntityManager em;
	
	@Override
	public Account accountCreation(Account ac) {
		Account a=em.merge(ac);
		return a;
	}
	
	@Override
	public Account getAccountByAccnum(int accountNumber) {
		
		return em.find(Account.class,accountNumber);
	}
	
	@Override
	public List<Account> getAllAccount() {
		Query q=em.createQuery("select m from Account m");
		List<Account> aclist=q.getResultList();
		return aclist;
	}
	
	@Override
	public Account update(Account ac) {
		Account a=em.find(Account.class,ac.getAccountNumber());
		if(a!=null)
		{
			a.setAccountNumber(ac.getAccountNumber());
			a.setCust_name(ac.getCust_name());
			a.setContact(ac.getContact());
			a.setAddressLine1(ac.getAddressLine1());
			a.setAddressLine2(ac.getAddressLine2());
			a.setCity(ac.getCity());
			a.setCountry(ac.getCountry());
			a.setState(ac.getState());
			a.setZipCode(ac.getZipCode());
			a.setAadhar(ac.getAadhar());
		}
		return a;
			
	}
	@Override	
	public Account deleteByAccountnum(int accountNumber) {
		Account a=em.find(Account.class,accountNumber);
		if(a!=null)
			{em.remove(a);
			}
        return a;
	}
}
