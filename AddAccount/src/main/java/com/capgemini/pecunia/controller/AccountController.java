package com.capgemini.pecunia.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

import com.capgemini.pecunia.entity.Account;
import com.capgemini.pecunia.entity.Cheque;
import com.capgemini.pecunia.entity.LoanDisbursal;
import com.capgemini.pecunia.entity.LoanRequests;
import com.capgemini.pecunia.entity.Transaction;
import com.capgemini.pecunia.exceptions.AccountIdNotFound;
import com.capgemini.pecunia.exceptions.IdNotFoundException;
import com.capgemini.pecunia.service.AccountService;
import com.capgemini.pecunia.service.CreditService;
import com.capgemini.pecunia.service.DebitService;
import com.capgemini.pecunia.service.LoanDisbursalService;
import com.capgemini.pecunia.service.LoanRequestService;
import com.capgemini.pecunia.service.PassbookMaintenanceService;
import com.capgemini.pecunia.service.UpdateBalanceService;

@RestController
@RequestMapping("/account")
@CrossOrigin("http://localhost:4200")
public class AccountController {
	@Autowired
	AccountService serviceobj;

	// Create Account
	@PostMapping("/AccountCreation")
	public ResponseEntity<String> accountCreation(@RequestBody Account ac) {
		Account a = serviceobj.accountCreation(ac);
		if (a == null) {
			throw new IdNotFoundException("Enter Valid Id");
		} else {
			return new ResponseEntity<String>("Account created successfully"+a.getAccountNumber() +"is your account number", new HttpHeaders(), HttpStatus.OK);
		}
	}

	// Get Particular Account Data
	@GetMapping("/GetAccount/{accountNumber}")
	private ResponseEntity<Account> getAccount(@PathVariable("accountNumber") int accountNumber) {
		Account a = serviceobj.getAccountByAccnum(accountNumber);
		if (a == null) {
			throw new IdNotFoundException("Id does not exist,so we couldn't fetch details");
		} else {
			return new ResponseEntity<Account>(a, new HttpHeaders(), HttpStatus.OK);
		}
	}

	// Get All Accounts Data
	@GetMapping("/GetAllAccounts")
	private ResponseEntity<List<Account>> getAllaccount() {
		List<Account> aclist = serviceobj.getAllAccount();
		return new ResponseEntity<List<Account>>(aclist, new HttpHeaders(), HttpStatus.OK);

	}

	// Updating Account data
	@PutMapping("/UpdateAccount")
	public ResponseEntity<String> update(@RequestBody Account ac) {
		Account a = serviceobj.update(ac);
		if (a == null) {
			throw new IdNotFoundException("Update Operation Unsuccessful,Provided Id does not exist");
		} else {
			return new ResponseEntity<String>("Account updated successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}

	// Deleting account
	@DeleteMapping("/DeleteAccount/{accountNumber}") 
	private ResponseEntity<String> delAc(@PathVariable("accountNumber") int accountNumber) throws AccountIdNotFound {
		Account a = serviceobj.deleteByAccountnum(accountNumber);
		if (a == null) {
			throw new IdNotFoundException("Delete Operation Unsuccessful,Provided Id does not exist");
		} else {
			return new ResponseEntity<String>("Account deleted successfully", new HttpHeaders(), HttpStatus.OK);
		}
	}

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> userNotFound(IdNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
	
	
	@Autowired
	CreditService creditService;
	public void setCreditService(CreditService transactionService)
	{
		this.creditService=transactionService;
	}
	@ExceptionHandler(value=NoSuchElementException.class)
	public ResponseEntity<String> accountNotFoundException1()
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
	
	
	
	
	@Autowired
	private PassbookMaintenanceService service;
	

	//Fetching the transactions till last updated date
		@GetMapping("/updatePassbook/{accountId}")
		public  ResponseEntity<List<Transaction>> updatePassbook(@PathVariable int accountId) throws AccountIdNotFound
		{
			
				List<Transaction> list = service.updatePassbook(accountId);
				return new ResponseEntity<List<Transaction>>(list, new HttpHeaders(), HttpStatus.OK);
			}
		

	//Updating the last updated date everytime
	@PutMapping("/lastUpdate/{accountId}")
	public void updateLastUpdated(@PathVariable int accountId){
		 service.updatelastUpdated(accountId);
		 
	}
	
	@GetMapping("/accountSummary/{accountId}/{startDate}/{endDate}")
	public ResponseEntity<List<Transaction>> accountSummary(@PathVariable int accountId, @PathVariable Date startDate, @PathVariable Date endDate)
	{
				List<Transaction> list = service.accountSummary(accountId, startDate, endDate);
		return new ResponseEntity<List<Transaction>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	//Validating accountId whether is is present or not.
		@GetMapping("/accountValidation/{accountId}")
		public boolean accountValidation(@PathVariable int accountId) {
			return service.accountValidation(accountId);
		}

		@Autowired
		LoanRequestService service3;
		@Autowired
		LoanDisbursalService service1; 
		@Autowired
		UpdateBalanceService service2;               
		//LOAN REQUEST
		@PostMapping("/request")
		public ResponseEntity<String> loanRequest(@RequestBody LoanRequests loanreq) {
			String request = service3.loanRequest(loanreq);
			return new ResponseEntity<String>(request, new HttpHeaders(), HttpStatus.OK);
		}
		//GETTING ALL LOAN REQUESTS
		@GetMapping("/getAllRequests")
		public ArrayList<LoanRequests> getAllRequests(){
			return service3.getAllRequests();
		}
		//GETTING ALL APPROVED LOAN REQUESTS
		@GetMapping("/approvedrequests")
		public ArrayList<LoanDisbursal> getApproveLoans() {
			return (ArrayList<LoanDisbursal>) service1.getApproveLoans();
		}
		//GETTING ALL REJECTED LOAN REQUESTS
		@GetMapping("/rejectedrequests")
		public ArrayList<LoanDisbursal> getRejectedLoans() {
			return (ArrayList<LoanDisbursal>) service1.getRejectedLoans();
		}
		//UPDATING BALANCE
		@PostMapping("/updateBal")
		public ResponseEntity<String> updateBal(@RequestBody LoanDisbursal loandis) {
			System.out.println(loandis);
			String update= service2.updateBalance(loandis);
			return new ResponseEntity<String>(update, new HttpHeaders(), HttpStatus.OK);
		}
}
