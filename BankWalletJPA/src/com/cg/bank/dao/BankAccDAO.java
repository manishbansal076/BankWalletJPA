package com.cg.bank.dao;

import java.util.ArrayList;

import com.cg.bank.dto.Customer;
import com.cg.bank.dto.Transaction;
import com.cg.bank.exception.BankAccException;

public interface BankAccDAO {

	public void createAccount(Customer customer);
	
	public void deposit(String mobileNo, double amount);
	
	public void withdraw(String mobileNo, double amount);
	
	public double checkBalance(String mobileNo);
	
	public void fundTransfer(String sender, String reciever, double amount);
	
	public boolean validateAccount(String mobileNo) throws BankAccException;
	
	public void getTransactionList(String mobileNo);
	
}
