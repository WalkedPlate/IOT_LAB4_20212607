package com.example.iot_lab4_20212607.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

//import com.example.iot_lab4_20212607.adapter.PosicionesAdapter;
import com.example.iot_lab4_20212607.databinding.FragmentPosicionesBinding;
//import com.example.iot_lab4_20212607.dto.PosicionesResponse;
//import com.example.iot_lab4_20212607.model.Posicion;
import com.example.iot_lab4_20212607.service.LigasApiService;
import com.example.iot_lab4_20212607.service.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PosicionesFragment extends Fragment {

    private FragmentPosicionesBinding binding;
    //private PosicionesAdapter posicionesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPosicionesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        /*

        // Configurar RecyclerView
        posicionesAdapter = new PosicionesAdapter(view.getContext());
        binding.recyclerViewPosiciones.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.recyclerViewPosiciones.setAdapter(posicionesAdapter);

        // Acción del botón buscar
        binding.searchButton.setOnClickListener(v -> {
            String idLiga = binding.searchEditTextIdLiga.getText().toString().trim();
            String temporada = binding.searchEditTextSeason.getText().toString().trim();
            if (idLiga.isEmpty() || temporada.isEmpty()) {
                Toast.makeText(getContext(), "Por favor ingresa la liga y la temporada", Toast.LENGTH_SHORT).show();
            } else {
                buscarPosiciones(idLiga, temporada);
            }
        });

         */

        return view;
    }

    /*
    // Buscar posiciones por liga y temporada
    private void buscarPosiciones(String idLiga, String temporada) {
        LigasApiService ligasApiService = RetrofitClient.getApiService();
        Call<PosicionesResponse> call = ligasApiService.getPosiciones(idLiga, temporada);

        call.enqueue(new Callback<PosicionesResponse>() {
            @Override
            public void onResponse(Call<PosicionesResponse> call, Response<PosicionesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Posicion> posiciones = response.body().getPosiciones();
                    posicionesAdapter.setPosicionesList(posiciones);
                    posicionesAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "No se encontraron posiciones", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PosicionesResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

     */
}
