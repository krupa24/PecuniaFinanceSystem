package com.capgemini.pecunia.controller;

import java.util.List;


import com.capgemini.pecunia.exceptions.IdNotFoundException;
import com.capgemini.pecunia.entity.Customerdata;
import com.capgemini.pecunia.exceptions.CustomerNotFoundException;
import com.capgemini.pecunia.service.CustomerService;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@CrossOrigin("http://localhost:4200")
public class CustomerController {
	@Autowired
	CustomerService serviceobj;

	// Add customer
	@PostMapping("/addCustomer")
	public ResponseEntity<String> addCustomer(@RequestBody Customerdata c) {
		Customerdata e = serviceobj.addCustomer(c);
		if (e == null) {
			throw new IdNotFoundException("Enter Valid Id");
		} else {
			return new ResponseEntity<String>("User created successfully with CustomerId:"+c.getCustomerId(), new HttpHeaders(), HttpStatus.OK);
		}
	}

	
	// Get all customers
	@GetMapping("/GetAllCustomers")
	private ResponseEntity<List<Customerdata>> getAllCustomers() {
		List<Customerdata> customerlist = serviceobj.getAllCustomers();
		return new ResponseEntity<List<Customerdata>>(customerlist, new HttpHeaders(), HttpStatus.OK);

	}

	//Update Customer
	@PutMapping("/UpdateCustomer")
	public ResponseEntity<String> updateCustomer(@RequestBody Customerdata c) {
		Customerdata e = serviceobj.updateCustomer(c);
		if (e == null) {
			throw new IdNotFoundException("Update Operation Unsuccessful,Provided Id does not exist");
		} else {
			return new ResponseEntity<String>("Customer updated successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}
	
	// Delete Customer
	@DeleteMapping("/DeleteCustomer/{customerId}")
	private ResponseEntity<String> deleteUser(@PathVariable("customerId") int customerId) {
		Customerdata e = serviceobj.deleteCustomer(customerId);
		if (e == null) {
			throw new IdNotFoundException("Delete Operation Unsuccessful,Provided Id does not exist");
		} else {
			return new ResponseEntity<String>("Customer deleted successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}
	
  //LOGIN
	@PutMapping("/Login")
	public String login(@RequestBody Customerdata c)
	{
		 String flag = null;
		 String type=serviceobj.login(c);
		 return type;
	}
	
	


	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> userNotFound(CustomerNotFoundException e){
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
