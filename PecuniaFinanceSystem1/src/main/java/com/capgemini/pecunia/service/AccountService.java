package com.capgemini.pecunia.service;

import java.util.List;

import com.capgemini.pecunia.bean.Account;

public interface AccountService {

	Account accountCreation(Account ac);

	Account getAccountByAccnum(int accuntNumber);

	List<Account> getAllAccount();

	Account deleteByAccountnum(int accountNumber);

	Account update(Account ac);

}