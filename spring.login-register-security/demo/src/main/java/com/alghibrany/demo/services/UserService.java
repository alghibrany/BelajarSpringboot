package com.alghibrany.demo.services;

import com.alghibrany.demo.model.*;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}