package com.alghibrany.demo.controller;

import com.alghibrany.demo.model.Users;
import com.alghibrany.demo.service.IEmailSevice;
import com.alghibrany.demo.service.IUserService;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ahmad
 */
@Controller
public class UserController {
    
     @Autowired
    IUserService ur;
    @Autowired
    IEmailSevice ie;

    @GetMapping("/")
    public String home(final Model m) {
                // m.addAttribute("anggota", new Users());
        return "index";
    }


    @GetMapping("/register")
    public String showFormRegister(final Model m){
        m.addAttribute("anggota", new Users());
        return "registerForm";
    }


    @PostMapping("/register_proses")
    public String registerProses(final Model m, final Users user) {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String newPassword = encoder.encode(user.getPassword());
        user.setPassword(newPassword);
        String error = null;
        boolean temps_cek = true;
        if (ur.cekEmail(user.getEmail())) {
            temps_cek = false;
            error = "Email Telah ada";
        }
        if (ur.cekUsername(user.getUsername())) {
            temps_cek = false;
            error = "Username Telah ada";
        }
        if (!temps_cek) {
            //flash error ke html
            m.addAttribute("error", error);
            m.addAttribute("anggota", user);
            return "registerForm";
        }
        m.addAttribute("anggota", user);
        ur.SSave(user);
        return "redirect:/";
    }

    @RequestMapping("/access-denied")
    public String accessDenied() {
        return "accessDenied";
    }

    @GetMapping("/forget")
    public String forget(final Model m, final Users user) {
        m.addAttribute("anggota", user);
        return "forgotPassword";
    }

    @PostMapping(value = "/forgetProses")
    public String forgetProses(final Model m, Users user, final HttpServletRequest request) {
        String error = null;
        String token = null;
        if (!ur.cekEmail(user.getEmail())) {
            error = "Email tidak ditemukan didatabase kami!";
            m.addAttribute("error", error);
            m.addAttribute("anggota", user);
            return "forgotPassword";
        }
        user = ur.getByEmail(user.getEmail());
        token = UUID.randomUUID().toString();
        user.setToken(token);
        ur.SSave(user);
        m.addAttribute("anggota", user);
        m.addAttribute("info", "link reset password telah dikirim ke alamat email = " + user.getEmail());
        
        final String appUrl = request.getScheme() + "://" + request.getServerName();
        final SimpleMailMessage passwordReset = new SimpleMailMessage();
        passwordReset.setFrom("salafudin.batman@gmail.com");
        passwordReset.setTo(user.getEmail());
        passwordReset.setSubject("Reset Password");
        passwordReset.setText("berikut adalah link untuk reset password anda = " +
                appUrl+":8080/reset?token=" +user.getToken());
        ie.sendMail(passwordReset);
         return "forgotPassword";
        
    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public String showResetPage(final Model m, @RequestParam("token") final String token)
    {
        final Users user = ur.getByToken(token);
        if(!user.getToken().equals(token))
        {
            return token;
        }
        m.addAttribute("anggota", user);
        return "resetPassword";
    }

    @PostMapping("/resetProses")
    public String resPassword(final Model m,Users user)
    {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String password_baru = user.getPassword();
        user = ur.getById(user.getId());
        user.setPassword(encoder.encode(password_baru));
        user.setToken(null);
        ur.SSave(user);
        m.addAttribute("anggota", user);
        return "redirect:/";
    }
    
}
