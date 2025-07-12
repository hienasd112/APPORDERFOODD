package com.example.testfood.model;


public class NhanVien {
    private int id;
    private String tenNV;
    private String sdt;

    public NhanVien(int id, String tenNV, String sdt) {
        this.id = id;
        this.tenNV = tenNV;
        this.sdt = sdt;
    }

    public int getId() { return id; }
    public String getTenNV() { return tenNV; }
    public String getSdt() { return sdt; }

    public void setTenNV(String tenNV) { this.tenNV = tenNV; }
    public void setSdt(String sdt) { this.sdt = sdt; }
}
