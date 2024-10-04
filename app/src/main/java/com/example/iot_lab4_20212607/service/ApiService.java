package com.example.iot_lab4_20212607.service;

import com.example.iot_lab4_20212607.dto.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("all_leagues.php")
    Call<ApiResponse> getLigas();  // Llamada a la API para obtener todas las ligas
}
