package com.example.testfood;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testfood.SQLite.DatabaseHelper;
import com.example.testfood.model.MonAn;

public class AddMonAnActivity extends AppCompatActivity {

    EditText edtTen, edtGia, edtHinh;
    Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mon);

        edtTen = findViewById(R.id.edtTenMon);
        edtGia = findViewById(R.id.edtGiaMon);
        edtHinh = findViewById(R.id.edtHinhMon);
        btnThem = findViewById(R.id.btnThemMon);

        btnThem.setOnClickListener(v -> {
            String ten = edtTen.getText().toString().trim();
            String hinh = edtHinh.getText().toString().trim().toLowerCase();
            String giaStr = edtGia.getText().toString().trim();

            if (ten.isEmpty() || giaStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ!", Toast.LENGTH_SHORT).show();
                return;
            }

            int gia = Integer.parseInt(giaStr);
            int resId = getResources().getIdentifier(hinh, "drawable", getPackageName());

            if (resId == 0) {
                Toast.makeText(this, "Ảnh không tồn tại trong drawable!", Toast.LENGTH_SHORT).show();
                return;
            }

            MonAn mon = new MonAn(0, ten, gia, hinh);
            DatabaseHelper db = DatabaseHelper.getInstance(this);
            db.themMonAn(mon);

            Toast.makeText(this, "Đã thêm món!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
