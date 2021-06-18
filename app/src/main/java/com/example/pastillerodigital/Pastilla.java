package com.example.pastillerodigital;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pastilla {

    @PrimaryKey(autoGenerate = true)
    int idPastilla;

    @ColumnInfo(name = "nombrePastilla")
    String pastilla;

    @ColumnInfo(name = "unidad")
    String unidad;

    @ColumnInfo(name = "Duracion")
    String duracion;

    @ColumnInfo(name = "Frecuencia")
    String frecuencia;

    @ColumnInfo(name="Hora")
    String hora;
    public Pastilla() {
    }

    public Pastilla(String pastilla, String unidad, String duracion, String frecuencia, String hora) {
        this.pastilla = pastilla;
        this.unidad = unidad;
        this.duracion = duracion;
        this.frecuencia = frecuencia;
        this.hora = hora;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdPastilla() {
        return idPastilla;
    }

    public void setIdPastilla(int idPastilla) {
        this.idPastilla = idPastilla;
    }

    public String getPastilla() {
        return pastilla;
    }

    public void setPastilla(String pastilla) {
        this.pastilla = pastilla;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }
}
