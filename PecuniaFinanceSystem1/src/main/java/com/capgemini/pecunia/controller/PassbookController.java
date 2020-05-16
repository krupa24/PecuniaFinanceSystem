package com.capgemini.pecunia.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecunia.exceptions.AccountIdNotFound;
import com.capgemini.pecunia.bean.Transaction;
import com.capgemini.pecunia.service.PassbookMaintenanceService;



@RestController
@RequestMapping("/bank")
@CrossOrigin(origins = "http://localhost:4200")
public class PassbookController {

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
				System.out.println("accountid:"+accountId+", startDate:"+startDate+"endDate:"+endDate);
				List<Transaction> list = service.accountSummary(accountId, startDate, endDate);
		return new ResponseEntity<List<Transaction>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	//Validating accountId whether is is present or not.
		@GetMapping("/accountValidation/{accountId}")
		public boolean accountValidation(@PathVariable int accountId) {
			return service.accountValidation(accountId);
		}


}
	
