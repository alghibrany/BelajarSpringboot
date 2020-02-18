/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alghibrany.demo.dao;

import com.alghibrany.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ahmad
 */
public interface UserDao extends JpaRepository<Users, Integer> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Users findByEmail(String email);

    Users findByToken(String token);

    Users findById(int id_user);

}
