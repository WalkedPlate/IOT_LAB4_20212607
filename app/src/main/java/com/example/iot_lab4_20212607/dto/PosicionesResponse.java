package com.example.iot_lab4_20212607.dto;

import com.example.iot_lab4_20212607.model.EstadisticasEquipo;
import com.example.iot_lab4_20212607.model.Liga;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PosicionesResponse {

    @SerializedName("table")
    private List<EstadisticasEquipo> rankingLiga;


    public List<EstadisticasEquipo> getRankingLiga() {
        return rankingLiga;
    }

    public void setRankingLiga(List<EstadisticasEquipo> rankingLiga) {
        this.rankingLiga = rankingLiga;
    }
}
