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
import com.example.iot_lab4_20212607.dto.ApiResponse;
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

        // Simulación de carga de datos desde la API (más adelante se cambia por la llamada real)
        loadLigas();

        return view;
    }

    // Cargar las ligas
    private void loadLigas() {
        ApiService apiService = RetrofitClient.getApiService();
        apiService.getLigas().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Liga> ligas = response.body().getLeagues();  // Obtenemos las ligas de la respuesta
                    ligasAdapter.setLigasList(ligas);  // Actualizamos el RecyclerView con las ligas
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error al cargar las ligas", Toast.LENGTH_SHORT).show();
            }
        });
    }



}
