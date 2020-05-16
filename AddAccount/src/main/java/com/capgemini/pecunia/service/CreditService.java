package com.capgemini.pecunia.service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.pecunia.dao.AccountDao;
import com.capgemini.pecunia.dao.ChequeDAO;
import com.capgemini.pecunia.dao.LoanRequestDao;
import com.capgemini.pecunia.dao.TransactionDAO;
import com.capgemini.pecunia.entity.Account;
import com.capgemini.pecunia.entity.Cheque;
import com.capgemini.pecunia.entity.Transaction;

@Service
public class CreditService {
    @Autowired
    TransactionDAO tdao;
    @Autowired
	LoanRequestDao dao;
    @Autowired
	ChequeDAO cdao;
    @Autowired
    AccountDao adao;
    public void setTdao(TransactionDAO tdao) { this.tdao=tdao;} 
    public void setCdao(ChequeDAO cdao){this.cdao=cdao;}
    public void setAdao(AccountDao adao) {this.adao = adao;}
	@Transactional
    public String creditUsingSlip(Transaction transaction)
    {
		Optional<Account> details = dao.findBank(transaction.getAccId());
		if (!details.isPresent()) {
			return "No BankAccount found";
			
		} 
    	Account acc= adao.getAccountByAccnum(transaction.getAccId());    	
    	acc.setAccount_balance(acc.getAccount_balance()+transaction.getAmount());
    	Transaction t = new Transaction();
    	t.setAmount(transaction.getAmount());
    	t.setCheque(null);
    	t.setType("Credit");
    	t.setAccId(transaction.getAccId());
    	LocalDate localDate = LocalDate.now();
    	t.setDateOfTrans(localDate);
    	if(tdao.save(t)!=null)
    		return "Successful";
    	else 
    		return "Failed";
    }
	@Transactional
    public String creditUsingCheque(Cheque cheque)
    {
		Optional<Account> details = dao.findBank(cheque.getAccount_id());
		if (!details.isPresent()) {
			return "No BankAccount found";
			
		} 
    	Transaction t = new Transaction();
    	Account acc= adao.getAccountByAccnum(cheque.getAccount_id());
    	acc.setAccount_balance(acc.getAccount_balance()+cheque.getAmount());
    	cdao.save(cheque);
    	cdao.flush();
    	t.setAmount(cheque.getAmount());
    	t.setType("Credit");
    	t.setAccId(cheque.getAccount_id());
    	LocalDate localDate = LocalDate.now();
    	t.setDateOfTrans(localDate);
    	t.setCheque(cheque);
    	if(tdao.save(t)!=null)
    		return "Successful";
    	else 
    		return "Failed";
    }
}
