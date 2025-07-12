package com.example.testfood;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testfood.SQLite.DatabaseHelper;
import com.example.testfood.model.ChiTietDonHang;
import com.example.testfood.model.MonAn;

import java.util.ArrayList;

public class DonHangDetailActivity extends AppCompatActivity {

    ListView listView;
    TextView txtTitle;
    DatabaseHelper db;
    int idDonHang;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donhang_detail);

        listView = findViewById(R.id.listChiTiet);
        txtTitle = findViewById(R.id.txtDonHangInfo);
        DatabaseHelper db = DatabaseHelper.getInstance(this);


        idDonHang = getIntent().getIntExtra("idDonHang", -1);
        ArrayList<ChiTietDonHang> list = db.getChiTietDonHang(idDonHang);

        ArrayList<String> data = new ArrayList<>();
        for (ChiTietDonHang ct : list) {
            MonAn mon = db.getMonAnById(ct.getIdMonAn());
            data.add(mon.getTen() + " - SL: " + ct.getSoLuong());
        }

        txtTitle.setText("Chi tiết đơn #" + idDonHang);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }
}
