package com.example.iot_lab4_20212607.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.iot_lab4_20212607.adapter.LigasAdapter;
import com.example.iot_lab4_20212607.databinding.FragmentLigasBinding;
import com.example.iot_lab4_20212607.dto.LigasAll;
import com.example.iot_lab4_20212607.dto.LigasPorPais;
import com.example.iot_lab4_20212607.model.Liga;
import com.example.iot_lab4_20212607.service.ApiService;
import com.example.iot_lab4_20212607.service.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LigasFragment extends Fragment {

    private FragmentLigasBinding binding;
    private LigasAdapter ligasAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLigasBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Configurar RecyclerView
        ligasAdapter = new LigasAdapter(view.getContext());
        binding.recyclerViewLigas.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.recyclerViewLigas.setAdapter(ligasAdapter);

        // Acción del botón buscar
        binding.searchButton.setOnClickListener(v -> {
            String country = binding.searchEditText.getText().toString().trim();
            if (country.isEmpty()) {
                loadAllLigas();  // Cargar ligas generales si no se introduce país
            } else {
                buscarLigasPorPais(country);  // Cargar ligas por país
            }
        });

        return view;
    }

    // Cargar todaslas ligas
    private void loadAllLigas() {
        binding.progressBar.setVisibility(View.VISIBLE);

        ApiService apiService = RetrofitClient.getApiService();
        apiService.getLigas().enqueue(new Callback<LigasAll>() {
            @Override
            public void onResponse(Call<LigasAll> call, Response<LigasAll> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    List<Liga> ligas = response.body().getLeagues();
                    ligasAdapter.setLigasList(ligas);
                } else {
                    Toast.makeText(getContext(), "No se encontraron ligas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LigasAll> call, Throwable t) {
                Toast.makeText(getContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Cargar ligas por país
    private void buscarLigasPorPais(String pais) {
        ApiService apiService = RetrofitClient.getApiService();
        Call<LigasPorPais> call = apiService.getLigasPorPais(pais);

        call.enqueue(new Callback<LigasPorPais>() {
            @Override
            public void onResponse(Call<LigasPorPais> call, Response<LigasPorPais> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LigasPorPais ligaPorPais = response.body();
                    // Actualizar el RecyclerView con las ligas obtenidas
                    actualizarRecyclerView(ligaPorPais.getLigas());
                } else {
                    Toast.makeText(getContext(), "No se encontraron ligas para este país", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LigasPorPais> call, Throwable t) {
                // Maneja el error (puedes mostrar un Toast, por ejemplo)
                Toast.makeText(getContext(), "Error al buscar ligas: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void actualizarRecyclerView(List<Liga> ligas) {
        if (ligas != null && !ligas.isEmpty()) {
            ligasAdapter.setLigasList(ligas); // Asegúrate de que tu adapter tenga un método setLigas(List<Liga> ligas)
            ligasAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(), "No se encontraron ligas para este país", Toast.LENGTH_SHORT).show();
        }
    }


}
