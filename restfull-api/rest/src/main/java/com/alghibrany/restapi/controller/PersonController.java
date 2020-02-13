package com.alghibrany.restapi.controller;

import com.alghibrany.restapi.model.Person;
import com.alghibrany.restapi.service.PersonService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ahmad
 */

@CrossOrigin
@RestController
@RequestMapping("/persons")
public class PersonController {
    
	@Autowired
	PersonService ps;

	@RequestMapping("/all")
	public ArrayList<Person> getAll() {
		return ps.getAll();
	}

	@RequestMapping("{id}")
	public Person getPerson(@PathVariable("id") String id) {
		return ps.getPerson(id);
	}
        
         @PostMapping("/save")
	public ArrayList<Person> add() {
		return ps.getAll();
	}
}
