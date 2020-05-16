package com.capgemini.pecunia.service;

import java.util.List;

import com.capgemini.pecunia.bean.LoanDisbursal;

public interface LoanDisbursalService {
	public List<LoanDisbursal> getApproveLoans();

	public List<LoanDisbursal> getRejectedLoans();

	

}
