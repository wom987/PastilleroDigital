package com.example.pastillerodigital;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Time;

@Entity
public class Cita {

    @PrimaryKey(autoGenerate = true)
    int idCita;

    @ColumnInfo(name = "horaCita")
    String hora;

    @ColumnInfo(name="fechaCita")
    String fecha;

    @ColumnInfo(name = "Lugar")
    String lugar;

    public Cita() {

    }

    public Cita( String hora, String fecha, String lugar) {
        this.hora = hora;
        this.fecha = fecha;
        this.lugar = lugar;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
