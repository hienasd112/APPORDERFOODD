package com.example.testfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfood.R;
import com.example.testfood.model.MonAn;
import com.example.testfood.utils.GioHang;

import java.util.ArrayList;
import java.util.Objects;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {

    private final ArrayList<MonAn> list;
    private final Context context;
    private final TextView txtTongTien;

    public GioHangAdapter(ArrayList<MonAn> list, Context context, TextView txtTongTien) {
        this.list = list;
        this.context = context;
        this.txtTongTien = txtTongTien;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_giohang, parent, false));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GioHangAdapter that = (GioHangAdapter) o;
        return Objects.equals(list, that.list) && Objects.equals(context, that.context) && Objects.equals(txtTongTien, that.txtTongTien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list, context, txtTongTien);
    }

    public ArrayList<MonAn> getList() {
        return list;
    }

    public Context getContext() {
        return context;
    }

    public TextView getTxtTongTien() {
        return txtTongTien;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MonAn mon = list.get(position);
        holder.txtTen.setText(mon.getTen());
        holder.txtGia.setText(mon.getGia() + " VNĐ");
        holder.txtSoLuong.setText(String.valueOf(mon.getSoLuong()));

        holder.btnCong.setOnClickListener(v -> {
            GioHang.tangSoLuong(mon.getId());
            notifyDataSetChanged();
            txtTongTien.setText("Tổng: " + GioHang.tinhTongTien() + " VNĐ");
        });

        holder.btnTru.setOnClickListener(v -> {
            GioHang.giamSoLuong(mon.getId());
            notifyDataSetChanged();
            txtTongTien.setText("Tổng: " + GioHang.tinhTongTien() + " VNĐ");
        });

        holder.btnXoa.setOnClickListener(v -> {
            GioHang.xoaMonAn(mon.getId());
            notifyDataSetChanged();
            txtTongTien.setText("Tổng: " + GioHang.tinhTongTien() + " VNĐ");
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTen, txtGia, txtSoLuong;
        ImageButton btnCong, btnTru, btnXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTenGio);
            txtGia = itemView.findViewById(R.id.txtGiaGio);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            btnCong = itemView.findViewById(R.id.btnCong);
            btnTru = itemView.findViewById(R.id.btnTru);
            btnXoa = itemView.findViewById(R.id.btnXoa);
        }
    }
}
