/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alghibrany.restapi;

import java.util.ArrayList;

/**
 *
 * @author ahmad
 */
public class PersonService {
    
        ArrayList<Person> persons = new ArrayList<>();
	public PersonService() {
		Person p = new Person();
		p.setId("3");
		p.setAge(25);
		p.setFirstName("Hanna");
		p.setLastName("Anindya");
		persons.add(p);

	}
    
}
