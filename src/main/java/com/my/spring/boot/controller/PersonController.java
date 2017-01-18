package com.my.spring.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.my.spring.boot.model.Person;
import com.my.spring.boot.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "hello world";
	}
	
	@RequestMapping("/getAll")
	@ResponseBody
	public List<Person> getAll() {
		return personService.getAll();
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public Person getById(@RequestParam(value="id") int id) {
		return personService.getById(id);
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(@RequestParam(value="id") int id, @RequestParam(value="count") int count) {
		for (int i = 0; i < count; i++) {
			personService.insert(id);
		}
		return "0";
	}
	
}
