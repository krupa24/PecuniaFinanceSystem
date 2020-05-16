package com.capgemini.pecunia.service;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capgemini.pecunia.dao.LoanRequestDao;
import com.capgemini.pecunia.bean.Account;
import com.capgemini.pecunia.bean.LoanRequests;
@Service
public class LoanRequestServiceImp implements LoanRequestService {
	@Autowired
	LoanRequestDao dao;
	@Override
	public String loanRequest(LoanRequests loanreq) {
		int s1 = loanreq.getAccountId();
		Optional<Account> details = dao.findBank(s1);
		if (details.isPresent()) {
			dao.save(loanreq);
			return "Your Loan Request with " + loanreq.getAccountId() + " accountid is successful";
		} else {
			return "No BankAccount found with " + loanreq.getAccountId()
					+ "\n You need to have an Bank Account to applay Loan";
		}
	}
	@Override
	public ArrayList<LoanRequests> getAllRequests() {
		return (ArrayList<LoanRequests>) dao.findAll();
	}
}
