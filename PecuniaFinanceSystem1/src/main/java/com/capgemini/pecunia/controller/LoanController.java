package com.capgemini.pecunia.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecunia.bean.LoanDisbursal;
import com.capgemini.pecunia.bean.LoanRequests;
import com.capgemini.pecunia.service.LoanDisbursalService;
import com.capgemini.pecunia.service.LoanRequestService;
import com.capgemini.pecunia.service.UpdateBalanceService;

import antlr.collections.List;

@RestController
@RequestMapping("/loan")
@CrossOrigin("http://localhost:4200")
public class LoanController {
	@Autowired
	LoanRequestService service;
	@Autowired
	LoanDisbursalService service1; 
	@Autowired
	UpdateBalanceService service2;               
	//LOAN REQUEST
	@PostMapping("/request")
	public ResponseEntity<String> loanRequest(@RequestBody LoanRequests loanreq) {
		String request = service.loanRequest(loanreq);
		return new ResponseEntity<String>(request, new HttpHeaders(), HttpStatus.OK);
	}
	//GETTING ALL LOAN REQUESTS
	@GetMapping("/getAllRequests")
	public ArrayList<LoanRequests> getAllRequests(){
		return service.getAllRequests();
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


