package com.alghibrany.spring_xls.dao;

import com.alghibrany.spring_xls.model.Siswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiswaDao extends JpaRepository<Siswa, Integer> {
}
