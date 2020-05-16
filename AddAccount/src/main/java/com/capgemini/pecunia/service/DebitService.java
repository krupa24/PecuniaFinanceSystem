package com.capgemini.pecunia.service;

import java.time.LocalDate;
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
public class DebitService {
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
    public String  debitUsingSlip(Transaction transaction)
    {
    	Account acc= adao.getAccountByAccnum(transaction.getAccId());
    	Optional<Account> details = dao.findBank(transaction.getAccId());
		if (!details.isPresent()) {
			return "No BankAccount found";
			
		} 
    	if(acc.getAccount_balance()>=transaction.getAmount())
    	acc.setAccount_balance(acc.getAccount_balance()-transaction.getAmount());
    	else
    	{
    		return "Unable to complete transaction ,Insufficient balance, current balance:"+acc.getAccount_balance();
    	}
    	Transaction t = new Transaction();
    	t.setAmount(transaction.getAmount());
    	t.setCheque(null);
    	t.setType("Debit");
    	t.setAccId(transaction.getAccId());
    	LocalDate localDate = LocalDate.now();
    	t.setDateOfTrans(localDate);
    	if(tdao.save(t)!=null)
    		return "Transaction Sucessful";
    	else 
    		return "Transaction Failed, Please try again";
    }
	@Transactional
    public String debitUsingCheque(Cheque cheque)
    {
		Optional<Account> details = dao.findBank(cheque.getAccount_id());
		if (!details.isPresent()) {
			return "No BankAccount found";
			
		} 
    	Transaction t = new Transaction();
    	Account acc= adao.getAccountByAccnum(cheque.getAccount_id());
    	if(acc.getAccount_balance()>=cheque.getAmount())
    	acc.setAccount_balance(acc.getAccount_balance()-cheque.getAmount());
    	else
    		return "Unable to complete transaction ,Insufficient balance, current balance:"+acc.getAccount_balance();
    	cdao.save(cheque);
    	cdao.flush();
    	t.setAmount(cheque.getAmount());
    	t.setType("Debit");
    	t.setAccId(cheque.getAccount_id());
    	LocalDate localDate = LocalDate.now();
    	t.setDateOfTrans(localDate);
    	t.setCheque(cheque);
    	if(tdao.save(t)!=null)
    		return "Transaction Sucessful";
    	else 
    		return "Transaction Failed, Please try again";
    }
	public String getbalance(int accid) {
		Optional<Account> details = dao.findBank(accid);
		if (!details.isPresent()) {
			return "No BankAccount found";
			
		} 
		Account acc= adao.getAccountByAccnum(accid);
		
		return "Account balance:"+acc.getAccount_balance();
	}
}
