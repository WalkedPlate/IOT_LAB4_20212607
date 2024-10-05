package com.example.iot_lab4_20212607.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

//import com.example.iot_lab4_20212607.adapter.ResultadosAdapter;
import com.example.iot_lab4_20212607.databinding.FragmentResultadosBinding;
//import com.example.iot_lab4_20212607.dto.ResultadosResponse;
//import com.example.iot_lab4_20212607.model.Resultado;
import com.example.iot_lab4_20212607.service.LigasApiService;
import com.example.iot_lab4_20212607.service.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultadosFragment extends Fragment {

    private FragmentResultadosBinding binding;
    //private ResultadosAdapter resultadosAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentResultadosBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        /*

        // Configurar RecyclerView
        resultadosAdapter = new ResultadosAdapter(view.getContext());
        binding.recyclerViewResultados.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.recyclerViewResultados.setAdapter(resultadosAdapter);

        // Acción del botón buscar
        binding.searchButton.setOnClickListener(v -> {
            String idLiga = binding.searchEditTextIdLiga.getText().toString().trim();
            String temporada = binding.searchEditTextSeason.getText().toString().trim();
            String ronda = binding.searchEditTextRonda.getText().toString().trim();
            if (idLiga.isEmpty() || temporada.isEmpty() || ronda.isEmpty()) {
                Toast.makeText(getContext(), "Por favor ingresa todos los datos", Toast.LENGTH_SHORT).show();
            } else {
                buscarResultados(idLiga, temporada, ronda);
            }
        });

         */

        return view;
    }

    /*



    // Buscar resultados por liga, temporada y ronda
    private void buscarResultados(String idLiga, String temporada, String ronda) {
        LigasApiService ligasApiService = RetrofitClient.getApiService();
        Call<ResultadosResponse> call = ligasApiService.getResultados(idLiga, temporada, ronda);

        call.enqueue(new Callback<ResultadosResponse>() {
            @Override
            public void onResponse(Call<ResultadosResponse> call, Response<ResultadosResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Resultado> resultados = response.body().getResultados();
                    resultadosAdapter.setResultadosList(resultados);
                    resultadosAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultadosResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    */
}
