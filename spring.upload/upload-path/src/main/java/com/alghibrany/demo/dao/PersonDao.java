package com.alghibrany.demo.dao;

import com.alghibrany.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author ahmad
 */
public interface PersonDao extends JpaRepository<Person, Integer>{
    @Query("select p from Person p where id=6")
    Object findImg(int id);
    
}
