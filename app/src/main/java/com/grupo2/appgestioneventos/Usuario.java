package com.grupo2.appgestioneventos;

import java.io.Serializable;

public class Usuario implements Serializable {
    //variables del usuario
    int id;
    String email;
    String contrasena;
    String nombre;
    String apellido;

    //constructor
    public Usuario(int paramID, String paramEmail, String paramContrasenia, String paramNombre, String paramApellido){
        id = paramID;
        email = paramEmail;
        contrasena = paramContrasenia;
        nombre = paramNombre;
        apellido = paramApellido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", contrasenia='" + contrasena + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
