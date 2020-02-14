/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alghibrany.restapi.service;

import com.alghibrany.restapi.model.Person;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

/**
 *
 * @author ahmad
 */
@Service
public class PersonService {

    ArrayList<Person> persons = new ArrayList<>();

    public PersonService() {
        Person p = new Person();
        p.setId("1");
        p.setAge(27);
        p.setFirstName("Salaph");
        p.setLastName("Alghibrany");
        persons.add(p);

        p = new Person();
        p.setId("2");
        p.setAge(25);
        p.setFirstName("Ardi");
        p.setLastName("Anto");
        persons.add(p);
    }

    public Person getPerson(String id) {
        for (Person person : persons) {
            if (person.getId().equalsIgnoreCase(id)) {
                return person;
            }
        }
        return null;
    }

    public ArrayList<Person> getAll() {
        return persons;
    }

    public ArrayList<Person> add() {
        Person p = new Person();
        p.setId("1");
        p.setAge(27);
        p.setFirstName("Salaph");
        p.setLastName("Alghibrany");
        persons.add(p);
        return persons;
    }

}
