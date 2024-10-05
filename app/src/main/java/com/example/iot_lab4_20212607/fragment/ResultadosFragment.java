package com.example.iot_lab4_20212607.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.iot_lab4_20212607.adapter.ResultadosAdapter;
import com.example.iot_lab4_20212607.databinding.FragmentResultadosBinding;
import com.example.iot_lab4_20212607.dto.ResultadosResponse;
import com.example.iot_lab4_20212607.model.ResultadoPartido;
import com.example.iot_lab4_20212607.service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ResultadosFragment extends Fragment {

    private FragmentResultadosBinding binding;
    private ResultadosAdapter resultadosAdapter;
    private List<ResultadoPartido> listaResultados = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentResultadosBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Inicializar el RecyclerView y el adaptador
        resultadosAdapter = new ResultadosAdapter(listaResultados);
        binding.recyclerViewResultados.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewResultados.setAdapter(resultadosAdapter);

        // Configurar el botón de búsqueda
        binding.searchButton.setOnClickListener(v -> buscarResultados());

        return view;
    }

    private void buscarResultados() {
        String idLiga = binding.searchEditTextIdLiga.getText().toString().trim();
        String temporada = binding.searchEditTextSeason.getText().toString().trim();
        String ronda = binding.searchEditTextRonda.getText().toString().trim();

        // Verificar que los campos no estén vacíos
        if (TextUtils.isEmpty(idLiga) || TextUtils.isEmpty(temporada) || TextUtils.isEmpty(ronda)) {
            Toast.makeText(getContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Log para depuración
        Log.d("API_CALL", "idLiga: " + idLiga + ", ronda: " + ronda + ", temporada: " + temporada);


        // Llamar a la API para obtener los resultados
        RetrofitClient.getApiService().getResultados(idLiga, ronda, temporada).enqueue(new Callback<ResultadosResponse>() {
            @Override
            public void onResponse(Call<ResultadosResponse> call, Response<ResultadosResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ResultadoPartido> nuevosResultados = response.body().getResultadosPartidos();
                    if (nuevosResultados != null && !nuevosResultados.isEmpty()) {
                        resultadosAdapter.addResultados(nuevosResultados);
                    } else {
                        Toast.makeText(getContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultadosResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                Log.e("API_ERROR", "Error de conexión", t);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
