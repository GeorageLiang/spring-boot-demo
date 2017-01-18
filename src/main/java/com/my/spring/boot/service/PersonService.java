package com.my.spring.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring.boot.mapper.PersonMapper;
import com.my.spring.boot.model.Person;

@Service
public class PersonService {

	@Autowired
	private PersonMapper personMapper;
	
	public List<Person> getAll() {
		return personMapper.getAllPersons();
	}

	public Person getById(int id) {
		return personMapper.getPersonById(id);
	}
	
	public void insert(int id) {
		personMapper.insert(id);
	}
}
