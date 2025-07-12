package com.example.testfood;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfood.Adapter.NhanVienAdapter;
import com.example.testfood.SQLite.DatabaseHelper;
import com.example.testfood.model.NhanVien;

import java.util.ArrayList;

public class QuanLyNhanVienActivity extends AppCompatActivity {

    RecyclerView recyclerNhanVien;
    Button btnThemNV;
    ArrayList<NhanVien> list;
    NhanVienAdapter adapter;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanly_nhanvien);

        recyclerNhanVien = findViewById(R.id.recyclerNhanVien);
        btnThemNV = findViewById(R.id.btnThemNV);
        dbHelper = new DatabaseHelper(this);

        list = dbHelper.getDanhSachNhanVien();
        adapter = new NhanVienAdapter(this, list, this::showDialogSuaXoaNhanVien);

        recyclerNhanVien.setLayoutManager(new LinearLayoutManager(this));
        recyclerNhanVien.setAdapter(adapter);

        btnThemNV.setOnClickListener(v -> showDialogThemNhanVien());
    }

    private void showDialogThemNhanVien() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thêm Nhân Viên");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        EditText edtTen = new EditText(this);
        edtTen.setHint("Tên nhân viên");
        EditText edtSdt = new EditText(this);
        edtSdt.setHint("Số điện thoại");
        edtSdt.setInputType(InputType.TYPE_CLASS_PHONE);
        layout.addView(edtTen);
        layout.addView(edtSdt);

        builder.setView(layout);
        builder.setPositiveButton("Thêm", (dialog, which) -> {
            String ten = edtTen.getText().toString();
            String sdt = edtSdt.getText().toString();
            dbHelper.themNhanVien(ten, sdt);
            recreate();
        });
        builder.setNegativeButton("Hủy", null);
        builder.show();
    }

    private void showDialogSuaXoaNhanVien(NhanVien nv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sửa / Xóa Nhân Viên");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        EditText edtTen = new EditText(this);
        edtTen.setText(nv.getTenNV());
        EditText edtSdt = new EditText(this);
        edtSdt.setText(nv.getSdt());
        layout.addView(edtTen);
        layout.addView(edtSdt);

        builder.setView(layout);
        builder.setPositiveButton("Sửa", (dialog, which) -> {
            dbHelper.suaNhanVien(nv.getId(), edtTen.getText().toString(), edtSdt.getText().toString());
            recreate();
        });
        builder.setNegativeButton("Xóa", (dialog, which) -> {
            dbHelper.xoaNhanVien(nv.getId());
            recreate();
        });
        builder.setNeutralButton("Hủy", null);
        builder.show();
    }
}
