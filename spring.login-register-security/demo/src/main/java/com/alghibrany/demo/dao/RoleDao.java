package com.alghibrany.demo.dao;

import com.alghibrany.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long>{
}