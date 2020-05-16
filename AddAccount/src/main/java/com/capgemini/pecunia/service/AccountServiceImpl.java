package com.capgemini.pecunia.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.pecunia.dao.AccountDaoImpl;
import com.capgemini.pecunia.entity.Account;

@Service
@Transactional
public class AccountServiceImpl implements AccountService 
{
@Autowired
AccountDaoImpl dao;

@Override
public Account accountCreation(Account ac) {
	return dao.accountCreation(ac);
}

@Override
public Account getAccountByAccnum(int accountNumber) 
{
return dao.getAccountByAccnum(accountNumber);
}

@Override
public List<Account> getAllAccount() 
{
return dao.getAllAccount();
}

@Override
public Account deleteByAccountnum(int accountNumber) 
{
	return dao.deleteByAccountnum(accountNumber);
}

@Override
public Account update(Account ac) {
	return dao.update(ac);	
}

}