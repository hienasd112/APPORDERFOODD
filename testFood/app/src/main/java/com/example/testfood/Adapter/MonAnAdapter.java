package com.example.testfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfood.R;
import com.example.testfood.model.MonAn;

import java.util.ArrayList;

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.MonAnViewHolder> {

    Context context;
    ArrayList<MonAn> list;
    OnMonClickListener listener;

    public MonAnAdapter(Context context, ArrayList<MonAn> list, OnMonClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MonAnViewHolder(LayoutInflater.from(context).inflate(R.layout.item_monan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnViewHolder holder, int position) {
        MonAn monAn = list.get(position);
        holder.txtTen.setText(monAn.getTen());
        holder.txtGia.setText(monAn.getGia() + " VNĐ");

        // Load ảnh từ drawable
        int resId = context.getResources().getIdentifier(
                monAn.getHinhAnh(), "drawable", context.getPackageName());

        if (resId != 0) {
            holder.imgMonAn.setImageResource(resId);
        } else {
            holder.imgMonAn.setImageResource(R.drawable.default_food); // ảnh mặc định nếu sai tên
        }

        holder.btnThem.setOnClickListener(v -> {
            if (listener != null) {
                listener.onThemMonClick(monAn);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnMonClickListener {
        void onThemMonClick(MonAn monAn);
    }

    public static class MonAnViewHolder extends RecyclerView.ViewHolder {
        TextView txtTen, txtGia;
        ImageView imgMonAn;
        Button btnThem;

        public MonAnViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTen);
            txtGia = itemView.findViewById(R.id.txtGia);
            imgMonAn = itemView.findViewById(R.id.imgMonAn);
            btnThem = itemView.findViewById(R.id.btnThem);
        }
    }
}
