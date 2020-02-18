/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alghibrany.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author ahmad
 */

 @Service
public class EmailService implements IEmailSevice {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(SimpleMailMessage mail) {
        mailSender.send(mail);
    }
}
