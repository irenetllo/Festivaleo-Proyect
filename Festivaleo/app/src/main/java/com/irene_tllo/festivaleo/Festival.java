package com.irene_tllo.festivaleo;

import android.graphics.Bitmap;

/**
 * Created by irene_ on 16/11/2016.
 */

public class Festival {
    //private PictureDrawable fotoF;
    private String id;
    private String nombre;
    private String lugar;
    private String url;
    private String fecha;
    private String asistentes;
    private String likes;
    private String artistas;
    private String coordenadasLat;
    private String coordenadasLng;
    private String fotof;
    private String descripcion;
    private Bitmap foto;

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public Festival() {
        this.nombre = "";
        this.lugar = "";
        this.url = "";
        this.fecha = "";
        this.asistentes = "";
        this.likes = "";
        this.artistas = "";
        this.coordenadasLat = "";
        this.coordenadasLng = "";
        this.fotof = "";
        this.descripcion = "";
    }

    public Festival(String nombre, String lugar/*, Bitmap foto*/) {
        // this.fotoF=fotof;
        this.nombre = nombre;
        this.lugar = lugar;
        this.foto=foto;
    }

    public Festival(String nombre, String lugar, String url) {

        this.nombre = nombre;
        this.lugar = lugar;
        this.fotof=url;
    }

    public Festival(String nombre, String lugar, String url, String fecha, String asistentes, String likes, String artistas, String coordenadasLat, String coordenadasLng, String fotof, String descripcion) {
        this.nombre = nombre;
        this.lugar = lugar;
        this.url = url;
        this.fecha = fecha;
        this.asistentes = asistentes;
        this.likes = likes;
        this.artistas = artistas;
        this.coordenadasLat = coordenadasLat;
        this.coordenadasLng = coordenadasLng;
        this.fotof = fotof;
        this.descripcion = descripcion;
    }

    public void setFestival(String id, String nombre, String lugar, String url, String fecha, String asistentes, String likes, String artistas, String coordenadasLat,String coordenadasLng, String fotof, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.lugar = lugar;
        this.url = url;
        this.fecha = fecha;
        this.asistentes = asistentes;
        this.likes = likes;
        this.artistas = artistas;
        this.coordenadasLat = coordenadasLat;
        this.coordenadasLng = coordenadasLng;
        this.fotof = fotof;
        this.descripcion = descripcion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(String asistentes) {
        this.asistentes = asistentes;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getArtistas() {
        return artistas;
    }

    public void setArtistas(String artistas) {
        this.artistas = artistas;
    }

    public String getCoordenadasLat() {
        return coordenadasLat;
    }

    public void setCoordenadasLat(String coordenadasLat) {
        this.coordenadasLat = coordenadasLat;
    }

    public String getCoordenadasLng() {
        return coordenadasLng;
    }

    public void setCoordenadasLng(String coordenadasLng) {
        this.coordenadasLng = coordenadasLng;
    }

    public String getFotof() {
        return fotof;
    }

    public void setFotof(String fotof) {
        this.fotof = fotof;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}

