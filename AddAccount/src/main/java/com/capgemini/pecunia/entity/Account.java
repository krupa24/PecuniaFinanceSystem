package com.capgemini.pecunia.entity;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accId")
	@SequenceGenerator(name = "accId",sequenceName = "accId", allocationSize=50)
	private int accountNumber;
	private String cust_name;
	private double account_balance;
	private String addressLine1;
	private String addressLine2;
	private String state;
	private String city;
	private String country;
	private int zipCode;
	private long aadhar;
	private long contact;
	private Date lastUpdated;
	
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int accountNumber, String cust_name, double account_balance, String addressLine1,
			String addressLine2, String state, String city, String country, int zipCode, long aadhar, long contact,
			Date lastUpdated) {
		super();
		this.accountNumber = accountNumber;
		this.cust_name = cust_name;
		this.account_balance = account_balance;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.state = state;
		this.city = city;
		this.country = country;
		this.zipCode = zipCode;
		this.aadhar = aadhar;
		this.contact = contact;
		this.lastUpdated = lastUpdated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

		public double getAccount_balance() {
		return account_balance;
	}

	public void setAccount_balance(double d) {
		this.account_balance = d;
	}

		public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public long getAadhar() {
		return aadhar;
	}

	public void setAadhar(long aadhar) {
		this.aadhar = aadhar;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}
	
}