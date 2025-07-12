package com.example.testfood;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfood.Adapter.MonAnAdminAdapter;
import com.example.testfood.SQLite.DatabaseHelper;
import com.example.testfood.model.MonAn;

import java.util.ArrayList;

public class QuanLyMonAnActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<MonAn> list;
    MonAnAdminAdapter adapter;
    Button btnThemMon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlymonan);

        recyclerView = findViewById(R.id.recyclerMonAn);
        btnThemMon = findViewById(R.id.btnThemMon);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        list = dbHelper.getDanhSachMonAn();

        if (list.isEmpty()) {
            Toast.makeText(this, "Chưa có món ăn nào trong hệ thống", Toast.LENGTH_LONG).show();
        }


        adapter = new MonAnAdminAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Xử lý nút thêm món
        btnThemMon.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddMonAnActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Cập nhật lại danh sách món ăn khi quay lại màn hình
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        list.clear();
        list.addAll(dbHelper.getDanhSachMonAn());
        adapter.notifyDataSetChanged();
    }
}
