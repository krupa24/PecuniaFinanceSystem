package com.capgemini.pecunia.controller;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.pecunia.bean.Cheque;
import com.capgemini.pecunia.bean.Transaction;
import com.capgemini.pecunia.service.DebitService;
@RestController
@CrossOrigin
public class DebitController {
	@Autowired
	DebitService debitService;
	public void setTransactionService(DebitService debitService)
	{
		this.debitService=debitService;
	}
	@ExceptionHandler(value=NoSuchElementException.class)
	public ResponseEntity<String> accountNotFoundException()
	{
		return new ResponseEntity<>("Account not found",HttpStatus.NOT_FOUND);
	}
	//DEBIT USING SLIP
	@PostMapping(value="/debitusingslip",consumes= {"application/json","application/xml"})
    public ResponseEntity<String> debitUsingSlip(@RequestBody() Transaction transaction)
    {
		String b = debitService.debitUsingSlip(transaction);
    	
    		return new ResponseEntity<>(b,HttpStatus.OK);
    	
    }
	//DEBIT USING CCHEQUE
	@PostMapping(value="/debitusingcheque",consumes= {"application/json","application/xml"})
	public ResponseEntity<String> debitUsingCheque(@RequestBody() Cheque cheque) 
	{

		String b = debitService.debitUsingCheque(cheque);
		return new ResponseEntity<>(b,HttpStatus.OK);
	}
	//CHECKING BALANCE OF PERTICULAR ACCOUNT
	@GetMapping(value="/checkbalance/{accid}")
	public ResponseEntity<String> accountBalance(@PathVariable int accid)
	{
		String b = debitService.getbalance(accid);
		return new ResponseEntity<>(b,HttpStatus.OK);
	} 
	
}


	
		



