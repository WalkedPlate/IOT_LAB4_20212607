package com.example.iot_lab4_20212607.dto;

import com.example.iot_lab4_20212607.model.Liga;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LigasPorPais {

    @SerializedName("countries")
    private List<Liga> ligas;

    public List<Liga> getLigas() {
        return ligas;
    }

    public void setLigas(List<Liga> ligas) {
        this.ligas = ligas;
    }
}
