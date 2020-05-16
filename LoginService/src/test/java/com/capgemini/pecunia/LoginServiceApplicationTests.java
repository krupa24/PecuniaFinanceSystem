package com.capgemini.pecunia;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.capgemini.pecunia.dao.CustomerDaoImpl;
import com.capgemini.pecunia.entity.Customerdata;
import com.capgemini.pecunia.service.CustomerService;

@SpringBootTest
class LoginServiceApplicationTests {
	@Autowired
	private CustomerService service3;
	@MockBean
	private CustomerDaoImpl dao3;
	@Test
	public void  testaddCustomer() {
	Customerdata c=new Customerdata();
	c.setCustomerId(10);
	c.setCustomerName("abc");
	c.setCustomerPassword("abcd");
	c.setCustomerPhoneno(9898l);
	c.setCustomerEmail("abc@gmail.com");
	 dao3.addCustomer(c);
	 Mockito.verify(dao3, Mockito.times(1)).addCustomer(c); 
	}
	@Test
	public void testdeleteCustomer() {
		Customerdata c=new Customerdata(10,"abcde","abcdefd","abcdhjk",989789056,"abcdef@gmail.com");
		dao3.deleteCustomer(10);
	    verify(dao3,times(1)).deleteCustomer(10);
		
	}
	@Test
	public void testUpdateCustomer() {
		Customerdata c=new Customerdata(15,"abcde","abcdefd","abcdhjk",989789056,"abcdef@gmail.com");
		dao3.updateCustomer(c);
	    verify(dao3,times(1)).updateCustomer(c);
		
	}
}
	