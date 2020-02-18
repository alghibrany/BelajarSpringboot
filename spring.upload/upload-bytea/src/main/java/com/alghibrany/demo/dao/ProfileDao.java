package com.alghibrany.demo.dao;

import com.alghibrany.demo.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author ahmad
 */
public interface ProfileDao extends JpaRepository<Profile, Integer>{

    @Query("select p from Profile p where id = ?1")
    Object findO(int id);
        
}
