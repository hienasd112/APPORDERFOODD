package com.example.testfood.model;

public class DonHang {
    private int id;
    private int idBan;
    private String ngay;
    private double tongTien;

    public DonHang(int anInt, int anInt1, String string, double aDouble) {

    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdBan() { return idBan; }
    public void setIdBan(int idBan) { this.idBan = idBan; }

    public String getNgay() { return ngay; }
    public void setNgay(String ngay) { this.ngay = ngay; }

    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
}