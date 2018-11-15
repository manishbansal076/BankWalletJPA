package com.cg.bank.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "txn_details")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(length=2)
	int id;
	
	@Column(name="mobile_no", length=10)
	String mobile_no;
	
	@Column(name="credit",length=10)
	String credit;
	
	@Column(name="debit",length=10)
	String debit;
	
	@Column (name="trans",length=20)
	String trans;
	
	@Column(name="Total_Balance",length=10)
	Double balance;
	
	@Column(name="Customer_Name",length=10)
	String name;

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobie_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getDebit() {
		return debit;
	}

	public void setDebit(String debit) {
		this.debit = debit;
	}

	public String getTrans() {
		return trans;
	}

	public void setTrans(String trans) {
		this.trans = trans;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}


	@Override
	public String toString() {
		return "Transaction [id=" + id + ", mobile_no=" + mobile_no
				+ ", credit=" + credit + ", debit=" + debit + ", trans="
				+ trans + ", balance=" + balance + ", name=" + name + "]";
	}
	
	
	
}
