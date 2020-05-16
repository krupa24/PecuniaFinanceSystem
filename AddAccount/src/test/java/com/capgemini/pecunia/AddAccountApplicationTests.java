package com.capgemini.pecunia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.pecunia.dao.AccountDaoImpl;
import com.capgemini.pecunia.dao.ChequeDAO;
import com.capgemini.pecunia.dao.LoanDisbursalDao;
import com.capgemini.pecunia.dao.LoanRequestDao;
import com.capgemini.pecunia.dao.TransactionDAO;
import com.capgemini.pecunia.dao.UpdateBalanceDao;
import com.capgemini.pecunia.entity.Cheque;
import com.capgemini.pecunia.entity.LoanDisbursal;
import com.capgemini.pecunia.entity.LoanRequests;
import com.capgemini.pecunia.entity.Transaction;
import com.capgemini.pecunia.service.AccountService;
import com.capgemini.pecunia.service.CreditService;
import com.capgemini.pecunia.service.DebitService;
import com.capgemini.pecunia.service.LoanDisbursalService;
import com.capgemini.pecunia.service.LoanRequestService;
import com.capgemini.pecunia.service.UpdateBalanceService;

@SpringBootTest
class AddAccountApplicationTests {
	@Autowired
	private LoanRequestService service;
	@MockBean
	private LoanRequestDao dao;
	@Autowired
	private LoanDisbursalService service1;
	@MockBean
	private LoanDisbursalDao dao1;
	@Autowired
	private UpdateBalanceService service2;
	@MockBean
	private UpdateBalanceDao dao2;
	
	@Autowired
	private AccountService service4;
	@MockBean
	private AccountDaoImpl dao4;

	@MockBean
	private CreditService service41;
	@MockBean
	private TransactionDAO repository4;

	@MockBean
	private ChequeDAO repository5;

	@MockBean
	private DebitService service6;

/* negative test case for getAllRequestsTest*/
	@Test
	public void getAllRequestsTest() {
		when(dao.findAll()).thenReturn(Stream.of(new LoanRequests(123,500.0,12,200,5.0,"Accepted","Business",40),new LoanRequests(123,500.0,12,200,5.0,"Accepted","Business",40)).collect(Collectors.toList()));
		assertEquals(3,service.getAllRequests().size());
	}
	@Test
	public void testgetAllRequests(){
		ArrayList<LoanRequests> LoanRequestsList = new ArrayList<LoanRequests>();
		LoanRequestsList.add(new LoanRequests(123,500.0,12,200,5.0,"Accepted","Business",40));
		LoanRequestsList.add(new LoanRequests(123,500.0,12,200,5.0,"Accepted","Business",40));
		when(dao.findAll()).thenReturn(LoanRequestsList);	
		ArrayList<LoanRequests> result = service.getAllRequests();
		assertEquals(2, result.size());
		
	}

	@Test
	public void requestTest() {
		LoanRequests loanreq=new LoanRequests(1111111111,100.0,33,900,5.0,"Pending","Car",1);
		when(dao.save(loanreq)).thenReturn(loanreq);
		assertEquals(loanreq,dao.save(loanreq));
	}
	@Test
	public void testgetApproveLoans() {
		LoanDisbursal disburse=new LoanDisbursal(111,10.0,35,800,8.0,"Accepted","Education",3.0,9);
		when(dao1.save(disburse)).thenReturn(disburse);
		assertEquals(disburse,dao1.save(disburse));
	}
	@Test
	public void testgetRejectedLoans() {
		LoanDisbursal disburse=new LoanDisbursal(111,100.0,33,300,5.0,"Rejected","Car",1.0,7);
		when(dao1.save(disburse)).thenReturn(disburse);
		assertEquals(disburse,dao1.save(disburse));
	}
	@Test
	public void testUpdateBalance(){
		LoanDisbursal loandis=new LoanDisbursal(11,100.0,33,900,5.0,"Pending","Car",1.0,7);
		dao2.save(loandis);
	    verify(dao2,times(1)).save(loandis);
	}
	@Test
	public void testcreditSlip() {
		 Transaction t=new  Transaction(10,1234561,"savings",100.0,null,LocalDate.now());
		 
		 when(service41.creditUsingSlip(t)).thenReturn("Successful");
		}

	@Test
	public void testcreditCheque() {
		
		 Cheque c = new Cheque(4, 1234561, "SBI", "1001", new Date(), "opened");
		 when(service41.creditUsingCheque(c)).thenReturn("Successful");
		}

	@Test
	public void testdebitSlip() {
		 Transaction t=new  Transaction(10,1,"savings",100.0,null,LocalDate.now());
		 
		 when(service6.debitUsingSlip(t)).thenReturn("transaction succesful");
	}

	@Test
	public void testdebitCheque() {
		
		 Cheque c = new Cheque(4, 1234561, "SBI", "1001", new Date(), "opened");
		 when(service6.debitUsingCheque(c)).thenReturn("transaction succesful");
		}
	@Test
	public void testdebitCheque1() { 
		Cheque c = new Cheque(4, 1234561, "SBI", "1001", new Date(), "opened");

	    assertEquals(1234561,c.getAccount_id()); 
	} 

	@Test
	public void testnegativedebitCheque() { 
		Cheque c = new Cheque(4, 1234561, "SBI", "1001", new Date(), "opened");

	    assertFalse(c.getAccount_id()==1234562); 
	} 
	@Test
	public void testnegativecreditCheque() {
		
		 Cheque c = new Cheque(4, 1234561, "SBI", "1001", new Date(), "opened");
		 assertFalse(c.getAccount_id()==1234562);
	}

	@Test
	public void testnegativecreditSlip() {
		 Transaction t=new  Transaction(10,1234561,"savings",100.0,null,LocalDate.now());
		 assertFalse(t.getAccId()==1234562);
		 
		}

	}





		






			
