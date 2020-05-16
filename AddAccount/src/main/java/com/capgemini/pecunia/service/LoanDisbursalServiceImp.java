package com.capgemini.pecunia.service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.pecunia.dao.LoanDisbursalDao;
import com.capgemini.pecunia.entity.LoanDisbursal;
import com.capgemini.pecunia.entity.LoanRequests;
@Service
public class LoanDisbursalServiceImp implements LoanDisbursalService {
	@Autowired
	LoanDisbursalDao dao;
	LoanDisbursal disburse = new LoanDisbursal();
	@SuppressWarnings("rawtypes")
	@Override
	public List<LoanDisbursal> getApproveLoans() {
		ArrayList<LoanRequests> approve = dao.getApprovedLoans();
		Iterator iter = approve.iterator();
		while (iter.hasNext()) {
			LoanRequests l = (LoanRequests) iter.next();
			if(!(dao.existsById(l.getLoanId()))) {
			disburse.setAccountId(l.getAccountId());
			disburse.setCreditScore(l.getCreditScore());
			disburse.setLoanAmount(l.getLoanAmount());
			disburse.setLoanId(l.getLoanId());
			disburse.setLoanRoi(l.getLoanRoi());
			disburse.setLoanStatus("accepted");
			disburse.setLoanTenure(l.getLoanTenure());
			disburse.setLoanType(l.getLoanType());
			double interest = (l.getLoanAmount() * l.getLoanTenure() * l.getLoanRoi() / (100 * 12));
			double emi = ((l.getLoanAmount() + interest) / l.getLoanTenure());
			emi=Math.round(emi*100)/100;
			disburse.setEmi(emi);
			System.out.println(disburse);
			dao.save(disburse);
		}else {
			continue;
		}
			}

		return dao.findAllAccepted();
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List<LoanDisbursal> getRejectedLoans() {
		ArrayList<LoanRequests> approve = dao.getRejectedLoans();
		Iterator iter = approve.iterator();
		while (iter.hasNext()) {
			LoanRequests l = (LoanRequests) iter.next();			
			disburse.setAccountId(l.getAccountId());
			disburse.setCreditScore(l.getCreditScore());
			disburse.setLoanAmount(0);
			disburse.setLoanId(l.getLoanId());
			disburse.setLoanRoi(l.getLoanRoi());
			disburse.setLoanStatus("rejected");
			disburse.setLoanTenure(l.getLoanTenure());
			disburse.setLoanType(l.getLoanType());
			disburse.setEmi(0);
			System.out.println(disburse);
			dao.save(disburse);			
		}
		return dao.findAllRejected();
	}
}
