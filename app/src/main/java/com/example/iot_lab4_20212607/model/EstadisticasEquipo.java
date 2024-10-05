package com.example.iot_lab4_20212607.model;

import com.google.gson.annotations.SerializedName;

public class EstadisticasEquipo {

    @SerializedName("strTeam")
    private String nombre;

    @SerializedName("intRank")
    private String ranking;

    @SerializedName("strBadge")
    private String linkBadge;

    @SerializedName("intWin")
    private String victorias;

    @SerializedName("intLoss")
    private String derrotas;

    @SerializedName("intDraw")
    private String empates;

    @SerializedName("intGoalsFor")
    private String golesAnotados;

    @SerializedName("intGoalsAgainst")
    private String golesConcedidos;

    @SerializedName("intGoalDifference")
    private String golesDiferencia;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getLinkBadge() {
        return linkBadge;
    }

    public void setLinkBadge(String linkBadge) {
        this.linkBadge = linkBadge;
    }

    public String getVictorias() {
        return victorias;
    }

    public void setVictorias(String victorias) {
        this.victorias = victorias;
    }

    public String getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(String derrotas) {
        this.derrotas = derrotas;
    }

    public String getEmpates() {
        return empates;
    }

    public void setEmpates(String empates) {
        this.empates = empates;
    }

    public String getGolesAnotados() {
        return golesAnotados;
    }

    public void setGolesAnotados(String golesAnotados) {
        this.golesAnotados = golesAnotados;
    }

    public String getGolesConcedidos() {
        return golesConcedidos;
    }

    public void setGolesConcedidos(String golesConcedidos) {
        this.golesConcedidos = golesConcedidos;
    }

    public String getGolesDiferencia() {
        return golesDiferencia;
    }

    public void setGolesDiferencia(String golesDiferencia) {
        this.golesDiferencia = golesDiferencia;
    }
}
