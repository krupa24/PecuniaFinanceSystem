package com.capgemini.pecunia.dao;

import java.util.List;

import com.capgemini.pecunia.entity.Account;

public interface AccountDao {

	Account deleteByAccountnum(int accountNumber);

	List<Account> getAllAccount();

	Account getAccountByAccnum(int accountNumber);

	Account accountCreation(Account ac);

	Account update(Account ac);
}
