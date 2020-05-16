package com.capgemini.pecunia.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "transaction")
@XmlRootElement
public class Transaction 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trans_id_seq")
	@SequenceGenerator(sequenceName = "trans_id_seq", allocationSize = 1, name = "trans_id_seq")
	@Column(name="trans_id")
	private int transId;
	@Column(name="account_id")
	private int accId;
	@Column(name="type")
	private String type;
	@Column(name="amount")
	private double amount;
	@OneToOne
	@JoinColumn(name="cheque_id")
	private Cheque cheque;
	@Column(name="date_of_trans")
	private LocalDate dateOfTrans;
	public Transaction() 
	{
	}
	public Transaction(int transId, int accId, String type, double amount, Cheque cheque, LocalDate dateOfTrans) 
	{
		this.transId = transId;
		this.accId = accId;
		this.type = type;
		this.amount = amount;
		this.cheque = cheque;
		this.dateOfTrans = dateOfTrans;
	}
	public int getTransId() {
		return transId;
	}
	public void setTransId(int transId) {
		this.transId = transId;
	}
	public int getAccId() {
		return accId;
	}
	public void setAccId(int accId) {
		this.accId = accId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Cheque getCheque() {
		return cheque;
	}
	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}
	public LocalDate getDateOfTrans() {
		return dateOfTrans;
	}
	public void setDateOfTrans(LocalDate dateOfTrans) {
		this.dateOfTrans = dateOfTrans;
	}
}
