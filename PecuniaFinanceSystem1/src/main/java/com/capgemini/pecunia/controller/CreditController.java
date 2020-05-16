package com.capgemini.pecunia.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.pecunia.bean.Cheque;
import com.capgemini.pecunia.bean.Transaction;
import com.capgemini.pecunia.service.CreditService;
//import com.capgemini.pecunia.service;
@RestController
@CrossOrigin("http://localhost:4200")
public class CreditController {
	@Autowired
	CreditService creditService;
	public void setCreditService(CreditService transactionService)
	{
		this.creditService=transactionService;
	}
	@ExceptionHandler(value=NoSuchElementException.class)
	public ResponseEntity<String> accountNotFoundException()
	{
		return new ResponseEntity<>("Account not found",HttpStatus.NOT_FOUND);
	}
	//CREDIT USING SLIP
	@PostMapping(value="/creditusingslip",consumes= {"application/json","application/xml"})
    public ResponseEntity<String> creditUsingSlip(@RequestBody() Transaction transaction)
    {
		
    	String b = creditService.creditUsingSlip(transaction);
    	
    		return new ResponseEntity<>(b,HttpStatus.OK);
    	
    }
	//CREDIT USING CHEQUE
	@PostMapping(value="/creditusingcheque",consumes= {"application/json","application/xml"})
	public ResponseEntity<String> creditUsingCheque(@RequestBody() Cheque cheque) 
	{
		
		String b = creditService.creditUsingCheque(cheque);
		
    		return new ResponseEntity<>(b,HttpStatus.OK);
    	
	}
}






