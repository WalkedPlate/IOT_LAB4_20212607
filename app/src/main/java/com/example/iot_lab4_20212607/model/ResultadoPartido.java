package com.example.iot_lab4_20212607.model;

import com.google.gson.annotations.SerializedName;

public class ResultadoPartido {
    @SerializedName("strLeague")
    private String nombreLiga;

    @SerializedName("intRound")
    private String ronda;

    @SerializedName("strHomeTeam")
    private String equipoLocal;

    @SerializedName("strAwayTeam")
    private String equipoVisitante;

    @SerializedName("intHomeScore")
    private String marcadorLocal;

    @SerializedName("intAwayScore")
    private String marcadorVisitante;

    @SerializedName("dateEvent")
    private String fecha;

    @SerializedName("strLeagueBadge")
    private String logoLiga;

    @SerializedName("intSpectators")
    private String espectadores;

    public String getNombreLiga() {
        return nombreLiga;
    }

    public void setNombreLiga(String nombreLiga) {
        this.nombreLiga = nombreLiga;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    public String getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(String equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(String equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public String getMarcadorLocal() {
        return marcadorLocal;
    }

    public void setMarcadorLocal(String marcadorLocal) {
        this.marcadorLocal = marcadorLocal;
    }

    public String getMarcadorVisitante() {
        return marcadorVisitante;
    }

    public void setMarcadorVisitante(String marcadorVisitante) {
        this.marcadorVisitante = marcadorVisitante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLogoLiga() {
        return logoLiga;
    }

    public void setLogoLiga(String logoLiga) {
        this.logoLiga = logoLiga;
    }

    public String getEspectadores() {
        return espectadores;
    }

    public void setEspectadores(String espectadores) {
        this.espectadores = espectadores;
    }
}

