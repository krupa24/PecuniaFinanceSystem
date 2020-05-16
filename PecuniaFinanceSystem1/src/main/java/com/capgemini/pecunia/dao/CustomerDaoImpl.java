package com.capgemini.pecunia.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.pecunia.bean.Customerdata;
@Repository
public class CustomerDaoImpl implements CustomerDao {

	@PersistenceContext	
	 EntityManager em;
	
	@Override
	public Customerdata addCustomer(Customerdata c) {
		Customerdata e=em.merge(c);
		return e;
	}
	
	
	@Override
	public List<Customerdata> getAllCustomers() {
		Query q=em.createQuery("select m from Customerdata m");
		List<Customerdata> customerlist=q.getResultList();
		return customerlist;
	}
	
	@Override
	public Customerdata updateCustomer(Customerdata c) {
		Customerdata ud=em.find(Customerdata.class,c.getCustomerId());
		if(ud!=null)
		{

			ud.setCustomerName(c.getCustomerName());
			ud.setCustomerType(c.getCustomerType());
			ud.setCustomerPassword(c.getCustomerPassword());
			ud.setCustomerPhoneno(c.getCustomerPhoneno());
			ud.setCustomerEmail(c.getCustomerEmail());
		}
		return ud;
			
	}
	@Override	
	public Customerdata deleteCustomer(int customerId) {
		Customerdata ud=em.find(Customerdata.class,customerId);
		if(ud!=null)
			{em.remove(ud);
			}
        return ud;
	}
	
	@Override
	public Boolean adminLoginCustomer(String customerName,String customerPassword) {
		if(customerName.contentEquals("admin66")&&customerPassword.contentEquals("admin66"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	@Override
	public String login(Customerdata c) {
			String type = null;
			String flag = null;
		Query q=em.createQuery("select m.customerType from Customerdata m where m.customerId=?1 and m.customerPassword=?2");
		int a=c.getCustomerId();
		String b=c.getCustomerPassword();
		q.setParameter(1,a);
		q.setParameter(2,b);
		try
		{
			type=(String) q.getSingleResult();
			if(type.equalsIgnoreCase("admin") && type!=null) {
				 flag="admin";
			 }
			 else if(!type.equalsIgnoreCase("admin") && type!=null)
				 flag="customer";
			return flag;
		}catch(javax.persistence.NoResultException e)
	    {
	        e.printStackTrace();
	    }
		return "no";
		}


}
	