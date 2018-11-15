package com.cg.bank.service;

import com.cg.bank.dto.Customer;
import com.cg.bank.exception.BankAccException;

public interface BankAccService {

	public void createAccount(Customer customer);
	
	public void deposit(String mobileNo, double amount);
	
	public void withdraw(String mobileNo, double amount);
	
	public double checkBalance(String mobileNo);
	
	public void fundTransfer(String sender, String reciever, double amount);
	
	
	public boolean validateAccount(String mobileNo) throws BankAccException;
	
	public boolean validateName(String name) throws BankAccException;
	
	public boolean validateAge(float age) throws BankAccException;
	
	public boolean validateMoileNo(String mobileNo) throws BankAccException;
	
	public boolean validateAmount(double amount) throws BankAccException;
			
	public void getTransactionList(String mobileNo);
}
