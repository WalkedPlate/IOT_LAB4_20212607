package com.example.iot_lab4_20212607.dto;

import com.example.iot_lab4_20212607.model.Liga;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LigasAll {
    @SerializedName("leagues")
    private List<Liga> leagues;

    public List<Liga> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<Liga> leagues) {
        this.leagues = leagues;
    }

}
