package com.cg.bank.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.bank.dao.BankAccDAO;
import com.cg.bank.dao.BankAccDAOImpl;
import com.cg.bank.dto.Customer;
import com.cg.bank.exception.BankAccException;


public class BankAccServiceImpl implements BankAccService{

	BankAccDAO dao  = new BankAccDAOImpl();
	
	
	//------------------------ 1. Wallet Application --------------------------
		/****************************************************************************************************************
		 - Function Name	:	createAccount(Customer customer)
		 - Input Parameters	:	Customer customer
		 - Return Type		:	void
		 - Author			:	CAPGEMINI
		 - Creation Date	:	10/30/2018
		 - Description		:	Calls method createAccount(Customer customer) from DAO class and adds customer to DB
		 ****************************************************************************************************************/
	
	
	@Override
	public void createAccount(Customer customer) {
		// TODO Auto-generated method stub
		dao.createAccount(customer);		
	}

	
	//------------------------ 1. Wallet Application --------------------------
		/*******************************************************************************************************
		 - Function Name	:	deposit(String mobileNo, double amount)
		 - Input Parameters	:	MonileNo and Amount
		 - Return Type		:	void
		 - Author			:	CAPGEMINI
		 - Creation Date	:	10/30/2018
		 - Description		:	calls deposit(String mobileNo, double amount) from the DAO class
		 * @return 
		 ********************************************************************************************************/
	
	@Override
	public void deposit(String mobileNo, double amount) {
		// TODO Auto-generated method stub
		dao.deposit(mobileNo, amount);
		
	}

	
	//------------------------ 1. Wallet Application --------------------------
		/*******************************************************************************************************
		 - Function Name	:	withdraw(String mobileNo, double amount)
		 - Input Parameters	:	MonileNo and Amount
		 - Return Type		:	void
		 - Author			:	CAPGEMINI
		 - Creation Date	:	10/30/2018
		 - Description		:	calls method deposit(String mobileNo, double amount) form DAO class and withdraws amount 
		 ********************************************************************************************************/
	
	
	@Override
	public void withdraw(String mobileNo, double amount) {
		// TODO Auto-generated method stub
		dao.withdraw(mobileNo, amount);
		
	}
	
	
	//------------------------ 1. Wallet Application --------------------------
	/*******************************************************************************************************
	 - Function Name	:	checkBalance(String mobileNo)
	 - Input Parameters	:	MonileNo
	 - Return Type		:	double
	 - Author			:	CAPGEMINI
	 - Creation Date	:	10/30/2018
	 - Description		:	calls method checkBalance(String mobileNo) from DAO class and returns amount
	 ********************************************************************************************************/
	

	@Override
	public double checkBalance(String mobileNo) {
		// TODO Auto-generated method stub
		return dao.checkBalance(mobileNo);
	}

	
	//------------------------ 1. Wallet Application --------------------------
		/*******************************************************************************************************
		 - Function Name	:	fundTransfer(String sender, String reciever, double amount)
		 - Input Parameters	:	Sender's Mobile No.,Receiver's Mobile No. and Amount
		 - Return Type		:	void
		 - Author			:	CAPGEMINI
		 - Creation Date	:	10/30/2018
		 - Description		:	calls method fundTransfer(String sender, String reciever, double amount) from DAO class
		 ********************************************************************************************************/
	
	@Override
	public void fundTransfer(String sender, String reciever, double amount) {
		// TODO Auto-generated method stub
		dao.fundTransfer(sender, reciever, amount);
		
	}

	

	/*******************************************************************************************************
	 - Function Name	: validateName(String name)
	 - Input Parameters	: Name
	 - Return Type		: Boolean
	 - Throws		    : BAnkException
	 - Author	      	: CAPGEMINI
	 - Creation Date	: 10/30/2018
	 - Description		: validates the Name input by user
	 ********************************************************************************************************/
	
	
	@Override
	public boolean validateName(String name) throws BankAccException {
		try{
			if(name == null)
				throw new BankAccException("Null value found");
			Pattern p = Pattern.compile("[A-Z]{1}[a-z]{3,10}");
			Matcher m = p.matcher(name); 
			if(!m.matches())
				System.err.println("Error");
			return m.matches();
		}catch(BankAccException e){
			System.out.println(e);
		}
		return false;
		
	}

	
	/*******************************************************************************************************
	 - Function Name	: validateAge(float age)
	 - Input Parameters	: Age
	 - Return Type		: Boolean
	 - Throws		    : BankException
	 - Author	      	: CAPGEMINI
	 - Creation Date	: 10/30/2018
	 - Description		: validates the Customer Age
	 ********************************************************************************************************/
	
	
	@Override
	public boolean validateAge(float age)  throws BankAccException {
		try{
			if(age == 0)
			{
				System.err.println("Age Cannot be  null");
				throw new BankAccException("Age cannot be  null");
			}
			else if(age >100)
			{
				System.err.println("Age cannot be  greater than 100");
			
				throw new BankAccException("Age cannot be  greater than 100");
			}
			else if(age < 0)
			{
				System.err.println("Age cannot be a negative number");
				throw new BankAccException("Age cannot be a negative number");
			}
			else if(0<age && age<18)
			{
				System.err.println("Age Should be 18 above");
				throw new BankAccException("Age Should be 18 above");
			}
			else if(age>17)
				return true;
			
		
	} catch (Exception e) {
		System.out.println(e);
	}
		return false;
	}

	
	/*******************************************************************************************************
	 - Function Name	: validateMoileNo(String mobileNo)
	 - Input Parameters	: MobileNo
	 - Return Type		: Boolean
	 - Throws		    : BAnkException
	 - Author	      	: CAPGEMINI
	 - Creation Date	: 10/30/2018
	 - Description		: validates the Customer's Mobile No.
	 ********************************************************************************************************/
	
	
	@Override
	public boolean validateMoileNo(String mobileNo) throws BankAccException{
		try{
			// TODO Auto-generated method stub
			if(mobileNo == null)
				throw new BankAccException("Null value found");
			Pattern p = Pattern.compile("[6789][0-9]{9}");
			Matcher m = p.matcher(mobileNo);
			if(!m.matches())
				System.err.println("Mobile Number Invalid!");
			return m.matches();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return false;
	}

	
	/*******************************************************************************************************
	 - Function Name	: validateAmount(double amount)
	 - Input Parameters	: amount
	 - Return Type		: Boolean
	 - Throws		    : BAnkException
	 - Author	      	: CAPGEMINI
	 - Creation Date	: 10/30/2018
	 - Description		: validates the Amount.
	 ********************************************************************************************************/
	
	
	
	@Override
	public boolean validateAmount(double amount) throws BankAccException {
		// TODO Auto-generated method stub
		try{
			if(amount == 0)
				throw new BankAccException("Null value found, please enter monbile no again");
			String am = String.valueOf(amount);
			if(!am.matches("\\d{3,9}\\.\\d{0,4}"))
				System.err.println("Error Invalid Amount!");
			return (am.matches("\\d{3,9}\\.\\d{0,4}"));
		}catch(BankAccException e){
			System.out.println(e);
		}
		return false;
	}

	
	/*******************************************************************************************************
	 - Function Name	: validateAccount(String mobileNo)
	 - Input Parameters	: String mobileNo
	 - Return Type		: Boolean
	 - Throws		    : BankAccountException
	 - Author	      	: CAPGEMINI
	 - Creation Date	: 10/30/2018
	 - Description		: validates the existence of account.
	 ********************************************************************************************************/
	
	
	@Override
	public boolean validateAccount(String mobileNo) throws BankAccException {
		// TODO Auto-generated method stub
		
		return dao.validateAccount(mobileNo);
	}

	
	/*******************************************************************************************************
	 - Function Name	: gettransactionList(String mobileNo)
	 - Input Parameters	: String mobileNo
	 - Return Type		: Void
	 - Throws		    : BankAccountException
	 - Author	      	: CAPGEMINI
	 - Creation Date	: 10/30/2018
	 - Description		: Retrieves the list of the transaction done by the input mobile number
	 ********************************************************************************************************/
	
	
	@Override
	public void getTransactionList(String mobileNo) {
		// TODO Auto-generated method stub
		dao.getTransactionList(mobileNo);
	}

}
