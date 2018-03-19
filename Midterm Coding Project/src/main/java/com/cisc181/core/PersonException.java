package com.cisc181.core;

public class PersonException extends Exception {
	private Person Person;
	
	public PersonException(Person Person)
	{
		this.Person = Person;
	}
	
}
