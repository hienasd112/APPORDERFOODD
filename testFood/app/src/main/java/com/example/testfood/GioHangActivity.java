package com.example.testfood;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfood.Adapter.GioHangAdapter;
import com.example.testfood.SQLite.DatabaseHelper;
import com.example.testfood.model.MonAn;
import com.example.testfood.utils.GioHang;

public class GioHangActivity extends AppCompatActivity {

    RecyclerView recyclerGioHang;
    GioHangAdapter adapter;
    TextView txtTongTien;
    Button btnThanhToan, btnQuayLai, btnGoiMon;
    int idBan;

    boolean daGoiMon = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);

        recyclerGioHang = findViewById(R.id.recyclerGioHang);
        txtTongTien = findViewById(R.id.txtTongTien);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        btnQuayLai = findViewById(R.id.btnQuayLai);
        btnGoiMon = findViewById(R.id.btnGoiMon);

        idBan = getIntent().getIntExtra("idBan", -1);
        if (idBan == -1) {
            Toast.makeText(this, "Không xác định được bàn!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        if (GioHang.idBan != idBan) {
            GioHang.idBan = idBan;
        }

        adapter = new GioHangAdapter(GioHang.getDanhSachGioHang(), this, txtTongTien);
        recyclerGioHang.setLayoutManager(new LinearLayoutManager(this));
        recyclerGioHang.setAdapter(adapter);

        txtTongTien.setText("Tổng: " + GioHang.tinhTongTien() + " VNĐ");

        btnGoiMon.setOnClickListener(v -> {
            if (GioHang.getDanhSachGioHang().isEmpty()) {
                Toast.makeText(this, "Giỏ hàng trống", Toast.LENGTH_SHORT).show();
                return;
            }

            if (daGoiMon) {
                Toast.makeText(this, "Đã gọi món trước đó!", Toast.LENGTH_SHORT).show();
                return;
            }

            DatabaseHelper db = DatabaseHelper.getInstance(this);
            int idDonHang = db.themDonHang(idBan, GioHang.tinhTongTien());
            for (MonAn m : GioHang.getDanhSachGioHang()) {
                db.themChiTietDonHang(idDonHang, m.getId(), m.getSoLuong());
            }

            db.capNhatTrangThaiBan(idBan, "Đang phục vụ");
            Toast.makeText(this, "Gọi món thành công. Bạn có thể thanh toán khi cần.", Toast.LENGTH_SHORT).show();

            daGoiMon = true;
        });

        btnThanhToan.setOnClickListener(v -> {
            if (GioHang.getDanhSachGioHang().isEmpty()) {
                Toast.makeText(this, "Không có món nào để thanh toán", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!daGoiMon) {
                Toast.makeText(this, "Vui lòng gọi món trước khi thanh toán!", Toast.LENGTH_SHORT).show();
                return;
            }

            // ➤ Chuyển sang màn hình chọn phương thức thanh toán
            Intent intent = new Intent(this, ThanhToanActivity.class);
            intent.putExtra("idBan", idBan);
            intent.putExtra("tongTien", GioHang.tinhTongTien());
            startActivity(intent);
        });

        btnQuayLai.setOnClickListener(v -> finish());
    }
}
