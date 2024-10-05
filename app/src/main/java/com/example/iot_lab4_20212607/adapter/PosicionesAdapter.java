package com.example.iot_lab4_20212607.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.iot_lab4_20212607.R;
import com.example.iot_lab4_20212607.model.EstadisticasEquipo;

import java.util.List;

public class PosicionesAdapter extends RecyclerView.Adapter<PosicionesAdapter.PosicionesViewHolder> {

    private List<EstadisticasEquipo> equipos;
    private Context context;

    public PosicionesAdapter(Context context, List<EstadisticasEquipo> equipos) {
        this.context = context;
        this.equipos = equipos;
    }

    @NonNull
    @Override
    public PosicionesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_posicion, parent, false);
        return new PosicionesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosicionesViewHolder holder, int position) {
        EstadisticasEquipo equipo = equipos.get(position);

        holder.nombreEquipo.setText(equipo.getNombre());
        holder.rankingEquipo.setText(equipo.getRanking());
        holder.victoriasEmpatesDerrotas.setText("V: " + equipo.getVictorias() + " E: " + equipo.getEmpates() + " D: " + equipo.getDerrotas());
        holder.golesInfo.setText("GA: " + equipo.getGolesAnotados() + " GC: " + equipo.getGolesConcedidos() + " DG: " + equipo.getGolesDiferencia());

        // Cargar la imagen del badge usando una librería como Glide o Picasso
        Glide.with(context)
                .load(equipo.getLinkBadge())
                .into(holder.badgeEquipo);
    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }

    public void updateData(List<EstadisticasEquipo> newEquipos) {
        this.equipos = newEquipos;
        notifyDataSetChanged();
    }

    static class PosicionesViewHolder extends RecyclerView.ViewHolder {

        ImageView badgeEquipo;
        TextView nombreEquipo;
        TextView rankingEquipo;
        TextView victoriasEmpatesDerrotas;
        TextView golesInfo;

        public PosicionesViewHolder(@NonNull View itemView) {
            super(itemView);
            badgeEquipo = itemView.findViewById(R.id.badgeEquipo);
            nombreEquipo = itemView.findViewById(R.id.nombreEquipo);
            rankingEquipo = itemView.findViewById(R.id.rankingEquipo);
            victoriasEmpatesDerrotas = itemView.findViewById(R.id.victoriasEmpatesDerrotas);
            golesInfo = itemView.findViewById(R.id.golesInfo);
        }
    }
}
