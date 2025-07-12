package com.example.testfood.utils;

import com.example.testfood.model.MonAn;
import java.util.ArrayList;

public class GioHang {
    public static int idBan = -1;
    public static ArrayList<MonAn> danhSachGioHang = new ArrayList<>();

    public static void clear() {
        danhSachGioHang.clear();
    }

    public static ArrayList<MonAn> getDanhSachGioHang() {
        return danhSachGioHang;
    }

    public static int tinhTongTien() {
        int tong = 0;
        for (MonAn mon : danhSachGioHang) {
            tong += mon.getGia() * mon.getSoLuong();
        }
        return tong;
    }

    public static void themMonAn(MonAn monAn) {
        for (MonAn m : danhSachGioHang) {
            if (m.getId() == monAn.getId()) {
                m.setSoLuong(m.getSoLuong() + 1);
                return;
            }
        }
        MonAn monMoi = new MonAn(monAn.getId(), monAn.getTen(), monAn.getGia(), monAn.getHinhAnh());
        monMoi.setSoLuong(1);
        danhSachGioHang.add(monMoi);
    }

    public static void tangSoLuong(int idMonAn) {
        for (MonAn mon : danhSachGioHang) {
            if (mon.getId() == idMonAn) {
                mon.setSoLuong(mon.getSoLuong() + 1);
                return;
            }
        }
    }

    public static void giamSoLuong(int idMonAn) {
        for (int i = 0; i < danhSachGioHang.size(); i++) {
            MonAn mon = danhSachGioHang.get(i);
            if (mon.getId() == idMonAn) {
                if (mon.getSoLuong() > 1) {
                    mon.setSoLuong(mon.getSoLuong() - 1);
                } else {
                    danhSachGioHang.remove(i);
                }
                return;
            }
        }
    }

    public static void xoaMonAn(int idMonAn) {
        danhSachGioHang.removeIf(mon -> mon.getId() == idMonAn);
    }
}
