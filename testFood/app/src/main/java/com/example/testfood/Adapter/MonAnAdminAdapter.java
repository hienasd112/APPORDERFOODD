package com.example.testfood.Adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfood.EditMonAnActivity;
import com.example.testfood.R;
import com.example.testfood.SQLite.DatabaseHelper;
import com.example.testfood.model.MonAn;

import java.util.ArrayList;

public class MonAnAdminAdapter extends RecyclerView.Adapter<MonAnAdminAdapter.ViewHolder> {

    Context context;
    ArrayList<MonAn> list;
    DatabaseHelper db;

    public MonAnAdminAdapter(Context context, ArrayList<MonAn> list) {
        this.context = context;
        this.list = list;
        db = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_monan_admin, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MonAn mon = list.get(position);
        holder.txtTen.setText(mon.getTen());
        holder.txtGia.setText(mon.getGia() + " VNĐ");

        holder.btnSua.setOnClickListener(v -> {
            Intent i = new Intent(context, EditMonAnActivity.class);
            i.putExtra("mon", mon);
            context.startActivity(i);
        });

        holder.btnXoa.setOnClickListener(v -> {
            db.xoaMonAn(mon.getId());
            list.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Đã xoá món!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTen, txtGia;
        Button btnSua, btnXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTen);
            txtGia = itemView.findViewById(R.id.txtGia);
            btnSua = itemView.findViewById(R.id.btnSua);
            btnXoa = itemView.findViewById(R.id.btnXoa);
        }
    }
}
