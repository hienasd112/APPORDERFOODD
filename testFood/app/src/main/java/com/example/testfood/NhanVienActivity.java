package com.example.testfood;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfood.Adapter.BanAdapter;
import com.example.testfood.SQLite.DatabaseHelper;
import com.example.testfood.model.BanAn;

import java.util.ArrayList;

public class NhanVienActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BanAdapter banAdapter;
    ArrayList<BanAn> listBan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien);

        recyclerView = findViewById(R.id.recyclerNhanVien);

        // Lấy danh sách bàn ăn từ database
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(this);
        listBan = dbHelper.getDanhSachBan();

        // Tạo adapter, truyền vào context, danh sách bàn và listener xử lý khi click
        banAdapter = new BanAdapter(this, listBan, new BanAdapter.OnBanClickListener() {
            @Override
            public void onBanClick(BanAn ban) {
                int idBan = ban.getId();
                if (idBan <= 0) {
                    android.util.Log.e("NhanVienActivity", "ID bàn không hợp lệ: " + idBan);
                    return;
                }

                Intent intent = new Intent(NhanVienActivity.this, MenuMonAnActivity.class);
                intent.putExtra("idBan", idBan);
                startActivity(intent);
            }
        });


        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 cột dạng lưới
        recyclerView.setAdapter(banAdapter);
    }
}
