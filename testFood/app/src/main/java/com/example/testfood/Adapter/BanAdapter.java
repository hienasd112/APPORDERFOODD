package com.example.testfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfood.R;
import com.example.testfood.model.BanAn;

import java.util.ArrayList;

public class BanAdapter extends RecyclerView.Adapter<BanAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<BanAn> list;
    private final OnBanClickListener listener;

    public interface OnBanClickListener {
        void onBanClick(BanAn ban);
    }

    public BanAdapter(Context context, ArrayList<BanAn> list, OnBanClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ban, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BanAn ban = list.get(position);
        holder.txtSoBan.setText("Bàn " + ban.getSoBan());
        holder.txtTrangThai.setText(ban.getTrangThai());

        holder.itemView.setOnClickListener(v -> listener.onBanClick(ban));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtSoBan, txtTrangThai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSoBan = itemView.findViewById(R.id.txtSoBan);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
        }
    }
}