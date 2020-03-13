package com.alghibrany.spring_xls.controller;

import com.alghibrany.spring_xls.dao.SiswaDao;
import com.alghibrany.spring_xls.model.Siswa;
import com.alghibrany.spring_xls.myapachepoi.ExcelGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
public class HttpMappingRestController {

    private SiswaDao siswaDao;
    private ExcelGenerator excel;

    @Autowired
    public HttpMappingRestController(SiswaDao siswaDao, ExcelGenerator excel) {
        this.siswaDao = siswaDao;
        this.excel = excel;
    }

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> excelSiswaReport() throws Exception {
        List<Siswa> siswas = siswaDao.findAll();

        ByteArrayInputStream in = excel.exportExcel(siswas);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=siswa.xlsx");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

    }


}
