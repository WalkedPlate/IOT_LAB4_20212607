package com.example.iot_lab4_20212607.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.iot_lab4_20212607.adapter.PosicionesAdapter;
import com.example.iot_lab4_20212607.adapter.PosicionesAdapter;
import com.example.iot_lab4_20212607.databinding.FragmentPosicionesBinding;
import com.example.iot_lab4_20212607.dto.PosicionesResponse;
import com.example.iot_lab4_20212607.model.EstadisticasEquipo;
import com.example.iot_lab4_20212607.service.ApiService;
import com.example.iot_lab4_20212607.service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import com.example.iot_lab4_20212607.dto.PosicionesResponse;
//import com.example.iot_lab4_20212607.model.Posicion;


public class PosicionesFragment extends Fragment {

    private FragmentPosicionesBinding binding;
    private PosicionesAdapter adapter;
    private List<EstadisticasEquipo> equipos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar la vista usando View Binding
        binding = FragmentPosicionesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Inicializar RecyclerView y ProgressBar con View Binding
        binding.recyclerViewPosiciones.setLayoutManager(new LinearLayoutManager(getContext()));

        equipos = new ArrayList<>();
        adapter = new PosicionesAdapter(getContext(), equipos);
        binding.recyclerViewPosiciones.setAdapter(adapter);

        // Configurar el botón de búsqueda
        binding.searchButton.setOnClickListener(v -> {
            String idLiga = binding.searchEditTextIdLiga.getText().toString();
            String temporada = binding.searchEditTextTemporada.getText().toString();
            if (!idLiga.isEmpty() && !temporada.isEmpty()) {
                obtenerPosiciones(idLiga, temporada);
            } else {
                Toast.makeText(getContext(), "Por favor, ingrese el ID de la liga y la temporada", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void obtenerPosiciones(String idLiga, String temporada) {
        binding.progressBar.setVisibility(View.VISIBLE);

        ApiService apiService = RetrofitClient.getApiService();
        Call<PosicionesResponse> call = apiService.getPosicionesLiga(idLiga, temporada);

        call.enqueue(new Callback<PosicionesResponse>() {
            @Override
            public void onResponse(Call<PosicionesResponse> call, Response<PosicionesResponse> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    equipos = response.body().getRankingLiga();
                    adapter.updateData(equipos);
                } else {
                    Toast.makeText(getContext(), "Datos no encontrados para la liga o temporada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PosicionesResponse> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error de conexión o datos inválidos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Desvincular la vista para evitar pérdidas de memoria
    }
}