package com.capgemini.pecunia.exceptions;

@SuppressWarnings("serial")
public class AccountIdNotFound extends Exception{
	public AccountIdNotFound(String errorMsg){
		super(errorMsg);
	}

}
