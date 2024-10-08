package com.example.iot_lab4_20212607.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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


public class ResultadosFragment extends Fragment implements SensorEventListener {

    private FragmentResultadosBinding binding;
    private ResultadosAdapter resultadosAdapter;
    private List<ResultadoPartido> listaResultados = new ArrayList<>();
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private boolean isShakeDetected = false;
    private static final float SHAKE_THRESHOLD = 20.0f; // Umbral de aceleración
    private List<Integer> historialResultadosAñadidos = new ArrayList<>();

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

        // Inicializar el SensorManager
        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

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
                        historialResultadosAñadidos.add(nuevosResultados.size()); // Añadir la cantidad al historial
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
    public void onResume() {
        super.onResume();
        // Registrar el listener del acelerómetro
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // Desregistrar el listener del acelerómetro
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // Calcular la aceleración total
            float acceleration = (float) Math.sqrt(x * x + y * y + z * z);

            // Verificar si la aceleración supera el umbral
            if (acceleration > SHAKE_THRESHOLD && !isShakeDetected) {
                isShakeDetected = true; // Evitar múltiples detecciones seguidas
                mostrarDialogoConfirmacion();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private void mostrarDialogoConfirmacion() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Confirmar acción")
                .setMessage("¿Deseas deshacer la última acción y eliminar los últimos resultados?")
                .setPositiveButton("Sí", (dialog, which) -> deshacerUltimaAccion())
                .setNegativeButton("No", (dialog, which) -> {
                    // Restablecer la detección de agitación
                    isShakeDetected = false;
                    dialog.dismiss();
                })
                .show();
    }

    private void deshacerUltimaAccion() {
        if (!historialResultadosAñadidos.isEmpty()) {
            // Obtener la cantidad de resultados que se añadió en la última búsqueda
            int cantidadAEliminar = historialResultadosAñadidos.remove(historialResultadosAñadidos.size() - 1);

            // Eliminar la cantidad de resultados obtenida
            resultadosAdapter.removeLastResults(cantidadAEliminar);

            Toast.makeText(getContext(), "Últimos resultados eliminados", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "No hay más resultados para eliminar", Toast.LENGTH_SHORT).show();
        }

        isShakeDetected = false; // Permitir nuevas detecciones
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}