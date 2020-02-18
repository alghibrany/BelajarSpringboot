/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alghibrany.demo.service;

import com.alghibrany.demo.dao.UserDao;
import com.alghibrany.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ahmad
 */
@Service
public class UserService implements IUserService {

    @Autowired
    UserDao ur;

    @Override
    public Users SSave(Users user) {
        ur.save(user);
        return user;
    }

    @Override
    public boolean cekUsername(String username) {
        
        return ur.existsByUsername(username);
    }

    @Override
    public boolean cekEmail(String email) {
    
        return ur.existsByEmail(email);
    }

    @Override
    public Users getByEmail(String email) {
        return ur.findByEmail(email);
    }

    @Override
    public Users getByToken(String token) {
        return ur.findByToken(token);
    }

    @Override
    public Users getById(int id_user) {
        return ur.findById(id_user);
    }

    @Override
    public String getSession() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        return loggedInUser.getName();
    }
    
}
