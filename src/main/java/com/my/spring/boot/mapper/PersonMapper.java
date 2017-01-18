package com.my.spring.boot.mapper;

import java.util.List;

import com.my.spring.boot.model.Person;

public interface PersonMapper {
	
	public List<Person> getAllPersons();
	
	public Person getPersonById(int id);

	public void insert(int id);
}
