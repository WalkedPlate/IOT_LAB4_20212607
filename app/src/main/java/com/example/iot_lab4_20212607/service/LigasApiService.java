package com.example.iot_lab4_20212607.service;

import com.example.iot_lab4_20212607.dto.LigasAll;
import com.example.iot_lab4_20212607.dto.LigasPorPais;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LigasApiService {
    @GET("all_leagues.php")
    Call<LigasAll> getLigas();  // Llamada a la API para obtener todas las ligas

    @GET("search_all_leagues.php")
    Call<LigasPorPais> getLigasPorPais(@Query("c") String pais);

}
