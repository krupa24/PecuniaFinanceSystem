package com.capgemini.pecunia.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Customerdata")
public class Customerdata {
	@Id
	private int customerId;
	@NotBlank(message="customername cannot be empty")
	private String customerName;
	@NotBlank(message="customertype cannot be empty")
	private String customerType;
	@Size(min=8, max=20, message="password must be minimum 8 characters")
	private String customerPassword;
	@Min(value=10)
	private long customerPhoneno;
	@Email(message="enter proper email id")
	private String customerEmail;
	
	public Customerdata(int customerId, @NotBlank(message = "customername cannot be empty") String customerName,
			@NotBlank(message = "customertype cannot be empty") String customerType,
			@Size(min = 8, max = 20, message = "password must be minimum 8 characters") String customerPassword,
			@Min(10) long customerPhoneno, @Email(message = "enter proper email id") String customerEmail) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerType = customerType;
		this.customerPassword = customerPassword;
		this.customerPhoneno = customerPhoneno;
		this.customerEmail = customerEmail;
	}
	public Customerdata() {
		
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public long getCustomerPhoneno() {
		return customerPhoneno;
	}
	public void setCustomerPhoneno(long customerPhoneno) {
		this.customerPhoneno = customerPhoneno;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
}