package com.example.iot_lab4_20212607.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iot_lab4_20212607.R;
import com.example.iot_lab4_20212607.model.Liga;

import java.util.ArrayList;
import java.util.List;

public class LigasAdapter extends RecyclerView.Adapter<LigasAdapter.LigasViewHolder>{

    private List<Liga> ligasList = new ArrayList<>();
    private Context context;


    public LigasAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LigasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_liga, parent, false);
        return new LigasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LigasViewHolder holder, int position) {
        Liga liga = ligasList.get(position);
        holder.ligaNameTextView.setText(liga.getNombre());

        // Manejo de clics en cada ítem, mostrando un Toast con el nombre de la liga
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Liga seleccionada: " + liga.getNombre(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return ligasList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setLigasList(List<Liga> ligasList) {
        this.ligasList = ligasList;
        notifyDataSetChanged();
    }

    static class LigasViewHolder extends RecyclerView.ViewHolder {
        TextView ligaNameTextView;

        public LigasViewHolder(@NonNull View itemView) {
            super(itemView);
            ligaNameTextView = itemView.findViewById(R.id.ligaNameTextView);
        }
    }

}
