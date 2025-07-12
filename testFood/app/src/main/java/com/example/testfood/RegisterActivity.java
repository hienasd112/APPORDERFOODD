package com.example.testfood;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testfood.SQLite.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    RadioGroup radioGroupRole;
    Button btnDangKy, btnQuayLai;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        radioGroupRole = findViewById(R.id.radioGroupRole);
        btnDangKy = findViewById(R.id.btnRegister);
        btnQuayLai = findViewById(R.id.btnBack);

        db = DatabaseHelper.getInstance(this);

        btnDangKy.setOnClickListener(v -> dangKyTaiKhoan());
        btnQuayLai.setOnClickListener(v -> finish());
    }

    private void dangKyTaiKhoan() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        int selectedRoleId = radioGroupRole.getCheckedRadioButtonId();
        if (username.isEmpty() || password.isEmpty() || selectedRoleId == -1) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        // ✅ Sử dụng hằng số thay vì chuỗi cứng
        String role = (selectedRoleId == R.id.radioAdmin) ? Constants.ROLE_ADMIN : Constants.ROLE_NHANVIEN;

        if (db.kiemTraTaiKhoanTonTai(username)) {
            Toast.makeText(this, "Tài khoản đã tồn tại. Vui lòng đăng nhập.", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase database = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        values.put("role", role);

        long result = database.insert("User", null, values);
        if (result != -1) {
            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
            Log.d("REGISTER", "Inserted user: " + username + ", role: " + role);

            Intent intent = new Intent(this,
                    role.equals(Constants.ROLE_ADMIN) ? AdminActivity.class : NhanVienActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Đăng ký thất bại. Thử lại sau.", Toast.LENGTH_SHORT).show();
        }
    }
}
