package com.example.pastillerodigital;

public class Modelo {
    private int portada;
    private String titulo;
    private String historia;

    public Modelo(int portada, String titulo, String historia) {
        this.portada = portada;
        this.titulo = titulo;
        this.historia = historia;
    }

    public int getPortada() {
        return portada;
    }

    public void setPortada(int portada) {
        this.portada = portada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

}