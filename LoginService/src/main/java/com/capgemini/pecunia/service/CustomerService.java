package com.capgemini.pecunia.service;

import java.util.List;

import com.capgemini.pecunia.entity.Customerdata;



public interface CustomerService {

	Customerdata addCustomer(Customerdata c);

	String login(Customerdata c);

	List<Customerdata> getAllCustomers();

	Customerdata deleteCustomer(int customerId);

	Customerdata updateCustomer(Customerdata c);
	
	Boolean adminLoginCustomer(String customerName,String customerPassword);

}