package com.example.testfood;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testfood.SQLite.DatabaseHelper;

public class AdminActivity extends AppCompatActivity {

    EditText edtNgayThongKe, edtThangThongKe;
    TextView txtKetQuaThongKe;
    Button btnThongKeNgay, btnThongKeThang;
    Button btnMonAn, btnNhanVien, btnDonHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        initView();

        DatabaseHelper db = DatabaseHelper.getInstance(this);

        btnThongKeNgay.setOnClickListener(v -> {
            String ngay = edtNgayThongKe.getText().toString().trim();
            if (ngay.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập ngày", Toast.LENGTH_SHORT).show();
                return;
            }
            double doanhThu = db.getDoanhThuTheoNgay(ngay);
            txtKetQuaThongKe.setText("📅 Doanh thu ngày " + ngay + ": " + doanhThu + " VNĐ");
        });

        btnThongKeThang.setOnClickListener(v -> {
            String thang = edtThangThongKe.getText().toString().trim();
            if (thang.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tháng", Toast.LENGTH_SHORT).show();
                return;
            }
            double doanhThu = db.getDoanhThuTheoThang(thang);
            txtKetQuaThongKe.setText("📆 Doanh thu tháng " + thang + ": " + doanhThu + " VNĐ");
        });

        btnMonAn.setOnClickListener(v -> startActivity(new Intent(this, QuanLyMonAnActivity.class)));
        btnNhanVien.setOnClickListener(v -> startActivity(new Intent(this, QuanLyNhanVienActivity.class)));
        btnDonHang.setOnClickListener(v -> startActivity(new Intent(this, XemDonHangActivity.class)));
    }

    private void initView() {
        edtNgayThongKe = findViewById(R.id.edtNgayThongKe);
        edtThangThongKe = findViewById(R.id.edtThangThongKe);
        btnThongKeNgay = findViewById(R.id.btnThongKeNgay);
        btnThongKeThang = findViewById(R.id.btnThongKeThang);
        txtKetQuaThongKe = findViewById(R.id.txtKetQuaThongKe);
        btnMonAn = findViewById(R.id.btnMonAn);
        btnNhanVien = findViewById(R.id.btnNhanVien);
        btnDonHang = findViewById(R.id.btnDonHang);
    }
}
