/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alghibrany.demo.service;

import com.alghibrany.demo.model.Users;

/**
 *
 * @author ahmad
 */
public interface IUserService {
    Users SSave(Users user);
    boolean cekUsername(String username);
    boolean cekEmail(String email);
    Users getByEmail(String email);
    Users getByToken(String token);
    Users getById(int id_user);
    
}
