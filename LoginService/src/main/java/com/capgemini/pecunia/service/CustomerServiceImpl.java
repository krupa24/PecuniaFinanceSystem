package com.capgemini.pecunia.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.capgemini.pecunia.dao.CustomerDaoImpl;
import com.capgemini.pecunia.entity.Customerdata;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService 
{
@Autowired
CustomerDaoImpl dao;

@Override
public Customerdata addCustomer(Customerdata c) {
	return dao.addCustomer(c);
}

@Override
public List<Customerdata> getAllCustomers() 
{
return dao.getAllCustomers();
}


@Override
public Customerdata deleteCustomer(int customerId) 
{
	return dao.deleteCustomer(customerId);
}

@Override
public Customerdata updateCustomer(Customerdata c) {
	return dao.updateCustomer(c);	
}

@Override
public Boolean adminLoginCustomer(String customerName,String customerPassword)
{
	return dao.adminLoginCustomer(customerName,customerPassword);
}

@Override
public String login(Customerdata c)
{
	return dao.login(c);
}
}