package com.example.testfood;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfood.Adapter.BanAdapter;
import com.example.testfood.SQLite.DatabaseHelper;
import com.example.testfood.model.BanAn;

import java.util.ArrayList;

public class ChonBanActivity extends AppCompatActivity implements BanAdapter.OnBanClickListener {

    RecyclerView recyclerViewBan;
    ArrayList<BanAn> listBan;
    BanAdapter adapter;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chonban);

        recyclerViewBan = findViewById(R.id.recyclerBan);
        db = new DatabaseHelper(this);
        listBan = db.getDanhSachBan();

        adapter = new BanAdapter(this, listBan, this);
        recyclerViewBan.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewBan.setAdapter(adapter);
    }

    @Override
    public void onBanClick(BanAn ban) {
        if (ban.getTrangThai().equalsIgnoreCase("Trống")) {
            Toast.makeText(this, "Bắt đầu đặt món cho bàn số " + ban.getSoBan(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MenuMonAnActivity.class);
            intent.putExtra("idBan", ban.getId());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Bàn này đang phục vụ!", Toast.LENGTH_SHORT).show();
        }
    }
}
