package com.irene_tllo.festivaleo;

import java.security.Principal;

/**
 * Created by irene_ on 17/11/2016.
 */

public class Usuario {
    private String id;
    private String nombre;
    private String telefono;
    private String ciudad;
    private String anio;
    private String correo;
    private String password;

    public Usuario() {
        this.id="";
        this.nombre="";
        this.telefono="";
        this.ciudad="";
        this.anio="";
        this.correo="";
        this.password="";
    }

    public Usuario(String nombre, String telefono, String ciudad, String anio, String correo, String password) {

        this.nombre = nombre;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.anio = anio;
        this.correo = correo;
        this.password = password;
    }

    public Usuario(String nombre, String telefono, String ciudad) {

        this.nombre = nombre;
        this.telefono = telefono;
        this.ciudad = ciudad;
    }

    public void setUsuario(String id,String nombre, String telefono, String ciudad, String anio, String correo, String password) {
        this.id=id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.anio = anio;
        this.correo = correo;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {

        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getAnio() {
        return anio;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }
}
