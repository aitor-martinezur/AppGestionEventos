package com.grupo2.appgestioneventos;

import com.google.firebase.Timestamp;

import java.sql.Time;

public class Evento {
    //variables del evento
    int id;
    String nombre;
    String descripcion;
    String tipo;
    String creador;
    String horaFechaInicio;
    String horaFechaFin;

    //constructor
    public Evento(int paramID, String paramNombre, String paramDescripcion, String paramTipo, String paramCreador, String paramHoraFechaInicio, String paramHoraFechaFin){
        id = paramID;
        nombre = paramNombre;
        descripcion = paramDescripcion;
        tipo = paramTipo;
        creador = paramCreador;
        horaFechaInicio = paramHoraFechaInicio;
        horaFechaFin = paramHoraFechaFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getHoraFechaInicio() {
        return horaFechaInicio;
    }

    public void setHoraFechaInicio(String horaFechaInicio) {
        this.horaFechaInicio = horaFechaInicio;
    }

    public String getHoraFechaFin() {
        return horaFechaFin;
    }

    public void setHoraFechaFin(String horaFechaFin) {
        this.horaFechaFin = horaFechaFin;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", tipo='" + tipo + '\'' +
                ", creador='" + creador + '\'' +
                ", horaFechaInicio=" + horaFechaInicio +
                ", horaFechaFin=" + horaFechaFin +
                '}';
    }
}
