package com.example.testfood.model;

import java.io.Serializable;

public class BanAn implements Serializable {
    private int id;
    private int soBan;
    private String trangThai;

    public BanAn(int id, int soBan, String trangThai) {
        this.id = id;
        this.soBan = soBan;
        this.trangThai = trangThai;
    }

    // Getter
    public int getId() { return id; }
    public int getSoBan() { return soBan; }
    public String getTrangThai() { return trangThai; }

    // Setter
    public void setId(int id) { this.id = id; }
    public void setSoBan(int soBan) { this.soBan = soBan; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}
