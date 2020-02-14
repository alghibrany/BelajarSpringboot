package com.alghibrany.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author ahmad
 */
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nama;
    private String data_gambar;
    private String link_gambar;
    private String data_cv;
    private String link_cv;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getData_gambar() {
        return data_gambar;
    }

    public void setData_gambar(String data_gambar) {
        this.data_gambar = data_gambar;
    }

    public String getLink_gambar() {
        return link_gambar;
    }

    public void setLink_gambar(String link_gambar) {
        this.link_gambar = link_gambar;
    }

    public String getData_cv() {
        return data_cv;
    }

    public void setData_cv(String data_cv) {
        this.data_cv = data_cv;
    }

    public String getLink_cv() {
        return link_cv;
    }

    public void setLink_cv(String link_cv) {
        this.link_cv = link_cv;
    }

}
