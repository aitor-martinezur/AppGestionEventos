package com.grupo2.appgestioneventos;

public class Contacto {
    //variables del contacto
    int id;
    String nombre;
    String apellido;
    String telefono;
    String email;

    //constructor
    public Contacto(int paramID, String paramNombre, String paramApellido, String paramTelefono, String paramEmail){
        id = paramID;
        nombre = paramNombre;
        apellido = paramApellido;
        telefono = paramTelefono;
        email = paramEmail;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
