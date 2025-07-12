package com.example.testfood.model;

import java.io.Serializable;

public class MonAn implements Serializable {
    private int id;
    private String ten;
    private int gia;
    private String hinhAnh;
    private String moTa;
    private int soLuong;

    public MonAn(int id, String ten, int gia, String hinhAnh) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.hinhAnh = hinhAnh;
        this.moTa = "";       // gán mặc định
        this.soLuong = 1;     // gán mặc định
    }

    public MonAn(int id, String ten, int gia, int soLuong) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.soLuong = soLuong;
        this.moTa = "";
        this.hinhAnh = "";
    }

    // Getters và Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTen() { return ten; }
    public void setTen(String ten) { this.ten = ten; }

    public int getGia() { return gia; }
    public void setGia(int gia) { this.gia = gia; }

    public String getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(String hinhAnh) { this.hinhAnh = hinhAnh; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
}
