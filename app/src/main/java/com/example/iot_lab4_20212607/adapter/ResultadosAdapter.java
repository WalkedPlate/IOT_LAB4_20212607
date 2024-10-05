package com.example.iot_lab4_20212607.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.iot_lab4_20212607.R;
import com.example.iot_lab4_20212607.model.ResultadoPartido;

import java.util.ArrayList;
import java.util.List;

public class ResultadosAdapter extends RecyclerView.Adapter<ResultadosAdapter.ResultadoViewHolder> {

    private List<ResultadoPartido> resultados;

    public ResultadosAdapter(List<ResultadoPartido> resultados) {
        this.resultados = new ArrayList<>(resultados); // Crear una nueva lista
    }

    @NonNull
    @Override
    public ResultadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_resultado, parent, false);
        return new ResultadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultadoViewHolder holder, int position) {
        ResultadoPartido partido = resultados.get(position);

        holder.nombreLiga.setText(partido.getNombreLiga());
        holder.ronda.setText(partido.getRonda());
        holder.equipoLocal.setText(partido.getEquipoLocal());
        holder.equipoVisitante.setText(partido.getEquipoVisitante());
        holder.resultado.setText(partido.getMarcadorLocal() + " - " + partido.getMarcadorVisitante());
        holder.fecha.setText(partido.getFecha());
        holder.espectadores.setText(partido.getEspectadores() != null ? partido.getEspectadores() : "N/A");

        // Cargar el logo de la liga usando Glide
        Glide.with(holder.itemView.getContext())
                .load(partido.getLogoLiga())
                .into(holder.logoLiga);
    }

    @Override
    public int getItemCount() {
        return resultados.size();
    }

    // Método para agregar nuevos resultados
    public void addResultados(List<ResultadoPartido> nuevosResultados) {
        int startIndex = resultados.size();
        resultados.addAll(nuevosResultados);
        notifyItemRangeInserted(startIndex, nuevosResultados.size()); // Notificar cambios
    }

    // Método para eliminar un conjunto de últimos resultados
    public void removeLastResults(int cantidad) {
        if (!resultados.isEmpty()) {
            int numElementsToRemove = Math.min(cantidad, resultados.size()); // No eliminar más de los que hay
            int startIndex = resultados.size() - numElementsToRemove;

            for (int i = 0; i < numElementsToRemove; i++) {
                resultados.remove(resultados.size() - 1); // Eliminar el último elemento
            }

            notifyItemRangeRemoved(startIndex, numElementsToRemove); // Notificar al adaptador que un rango fue eliminado
        }
    }

    public static class ResultadoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreLiga, ronda, equipoLocal, equipoVisitante, resultado, fecha, espectadores;
        ImageView logoLiga;

        public ResultadoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreLiga = itemView.findViewById(R.id.nombreLiga);
            ronda = itemView.findViewById(R.id.ronda);
            equipoLocal = itemView.findViewById(R.id.equipoLocal);
            equipoVisitante = itemView.findViewById(R.id.equipoVisitante);
            resultado = itemView.findViewById(R.id.resultado);
            fecha = itemView.findViewById(R.id.fechaPartido);
            espectadores = itemView.findViewById(R.id.espectadores);
            logoLiga = itemView.findViewById(R.id.logoLiga);
        }
    }
}
