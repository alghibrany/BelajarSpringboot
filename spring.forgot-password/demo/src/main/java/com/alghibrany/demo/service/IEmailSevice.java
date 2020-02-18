/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alghibrany.demo.service;
import org.springframework.mail.SimpleMailMessage;

public interface IEmailSevice {
    public void sendMail(SimpleMailMessage mail);
}
