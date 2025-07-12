package com.example.testfood;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testfood.SQLite.DatabaseHelper;
import com.example.testfood.model.DonHang;

import java.util.ArrayList;

public class XemDonHangActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<DonHang> danhSach;
    ArrayAdapter<String> adapter;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemdonhang);

        listView = findViewById(R.id.listViewDonHang);
        db = new DatabaseHelper(this);
        danhSach = db.getDanhSachDonHang();

        ArrayList<String> data = new ArrayList<>();
        for (DonHang dh : danhSach) {
            data.add("Bàn " + dh.getIdBan() + " - " + dh.getNgay() + " - Tổng: " + dh.getTongTien() + " VNĐ");
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }
}
