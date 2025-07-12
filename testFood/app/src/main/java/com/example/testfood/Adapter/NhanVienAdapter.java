package com.example.testfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfood.R;
import com.example.testfood.model.NhanVien;

import java.util.ArrayList;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<NhanVien> list;
    private final OnNhanVienClickListener listener;

    public interface OnNhanVienClickListener {
        void onClick(NhanVien nhanVien);
    }

    public NhanVienAdapter(Context context, ArrayList<NhanVien> list, OnNhanVienClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup  parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nhanvien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhanVienAdapter.ViewHolder holder, int position) {
        NhanVien nv = list.get(position);
        holder.txtTen.setText(nv.getTenNV());
        holder.txtSdt.setText("SĐT: " + nv.getSdt());
        holder.itemView.setOnClickListener(v -> listener.onClick(nv));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTen, txtSdt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTenNV);
            txtSdt = itemView.findViewById(R.id.txtSdtNV);
        }
    }
}
