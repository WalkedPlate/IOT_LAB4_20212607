package com.example.iot_lab4_20212607.service;

import com.example.iot_lab4_20212607.dto.LigasAll;
import com.example.iot_lab4_20212607.dto.LigasPorPais;
import com.example.iot_lab4_20212607.dto.PosicionesResponse;
import com.example.iot_lab4_20212607.dto.ResultadosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("all_leagues.php")
    Call<LigasAll> getLigas();  // Llamada a la API para obtener todas las ligas

    @GET("search_all_leagues.php")
    Call<LigasPorPais> getLigasPorPais(@Query("c") String pais); // Obtener las ligas por país

    @GET("lookuptable.php")
    Call<PosicionesResponse> getPosicionesLiga(@Query("l") String idLiga, @Query("s") String temporada); //Obtener posiciones de la liga

    @GET("eventsround.php")
    Call<ResultadosResponse> getResultados(@Query("id") String idLiga, @Query("r") String ronda, @Query("s") String temporada); //Obtener resultados por liga, ronda y temporada


}
