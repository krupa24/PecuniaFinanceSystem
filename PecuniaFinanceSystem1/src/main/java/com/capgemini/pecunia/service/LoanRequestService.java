package com.capgemini.pecunia.service;
import java.util.ArrayList;
import com.capgemini.pecunia.bean.LoanRequests;
public interface LoanRequestService {
	public String loanRequest(LoanRequests loanreq);
	public ArrayList<LoanRequests> getAllRequests();
}
