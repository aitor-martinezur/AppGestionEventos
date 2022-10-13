package com.grupo2.appgestioneventos;

public class Usuario {
    int id;
    String email;
    String contrasenia;
    String nombre;
    String apellido;

    //constructor
    public Usuario(int paramID, String paramEmail, String paramContrasenia, String paramNombre, String paramApellido){
        id = paramID;
        email = paramEmail;
        contrasenia = paramContrasenia;
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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
                ", contrasenia='" + contrasenia + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
