package com.alghibrany.demo.dao;

import com.alghibrany.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}