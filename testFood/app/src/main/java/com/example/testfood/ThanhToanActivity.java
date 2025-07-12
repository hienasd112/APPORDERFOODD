package com.example.testfood;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testfood.SQLite.DatabaseHelper;
import com.example.testfood.utils.GioHang;

public class ThanhToanActivity extends AppCompatActivity {

    TextView txtTongTien;
    RadioGroup radioGroupPTTT;
    Button btnXacNhan;
    int idBan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);

        txtTongTien = findViewById(R.id.txtTongTien);
        radioGroupPTTT = findViewById(R.id.radioGroupPTTT);
        btnXacNhan = findViewById(R.id.btnXacNhanThanhToan);

        idBan = getIntent().getIntExtra("idBan", -1);
        if (idBan == -1) {
            Toast.makeText(this, "Không xác định bàn", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        double tong = GioHang.tinhTongTien();
        txtTongTien.setText("Tổng tiền: " + tong + " VNĐ");

        btnXacNhan.setOnClickListener(v -> {
            int selectedId = radioGroupPTTT.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Vui lòng chọn phương thức thanh toán", Toast.LENGTH_SHORT).show();
                return;
            }

            String pttt = ((RadioButton) findViewById(selectedId)).getText().toString();

            // Lưu đơn hàng (nếu chưa có thì có thể bỏ qua nếu đã lưu trước đó)
            DatabaseHelper db = new DatabaseHelper(this);
            int idDonHang = db.themDonHang(idBan, tong);
            for (int i = 0; i < GioHang.danhSachGioHang.size(); i++) {
                db.themChiTietDonHang(idDonHang,
                        GioHang.danhSachGioHang.get(i).getId(),
                        GioHang.danhSachGioHang.get(i).getSoLuong());
            }

            // Cập nhật bàn thành TRỐNG
            db.capNhatTrangThaiBan(idBan, "Trống");

            // Xoá giỏ hàng
            GioHang.clear();

            Toast.makeText(this, "Thanh toán thành công (" + pttt + ")", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}