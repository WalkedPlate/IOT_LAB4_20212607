package com.example.iot_lab4_20212607.dto;

import com.example.iot_lab4_20212607.model.ResultadoPartido;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultadosResponse {

    @SerializedName("events")
    private List<ResultadoPartido> resultadosPartidos;

    public List<ResultadoPartido> getResultadosPartidos() {
        return resultadosPartidos;
    }

    public void setResultadosPartidos(List<ResultadoPartido> resultadosPartidos) {
        this.resultadosPartidos = resultadosPartidos;
    }
}
