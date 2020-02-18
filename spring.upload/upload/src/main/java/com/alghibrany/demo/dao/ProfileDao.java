package com.alghibrany.demo.dao;

import com.alghibrany.demo.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ahmad
 */
public interface ProfileDao extends JpaRepository<Profile, Integer>{
        
}
