package com.irene_tllo.festivaleo;

import android.content.Context;

import java.util.Date;

/**
 * Created by irene_ on 20/11/2016.
 */

public class ComentarioFestival {
    private String id;
    private String idUser;
    private String idFestival;
    private String coment;
    private Date fecha;
    private String nombU;

    public ComentarioFestival(String id, String idUser, String idFestival, String coment, Date fecha) {
        this.id = id;
        this.idUser = idUser;
        this.idFestival = idFestival;
        this.coment = coment;
        this.fecha = fecha;
    }

    public ComentarioFestival(String idUser, String idFestival, String coment, Date fecha) {
        this.idUser = idUser;
        this.idFestival = idFestival;
        this.coment = coment;
        this.fecha = fecha;
    }

    public String getNombU() {
        return nombU;
    }

    public void setNombU(String nombU) {
        this.nombU = nombU;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setDatosComent(String idC, String idU, String idF, String coment, Date d, String nombreU ){
        this.id=idC;
        this.idUser=idU;
        this.idFestival=idF;
        this.coment=coment;
        this.fecha=d;
        this.nombU=nombreU;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setComentarioFestival(String id, String idUser, String idFestival, String coment, Date fecha) {
        this.id=id;

        this.idUser = idUser;
        this.idFestival = idFestival;
        this.coment = coment;
        this.fecha = fecha;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdFestival() {
        return idFestival;
    }

    public void setIdFestival(String idFestival) {
        this.idFestival = idFestival;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public Date getFechar() {
        return fecha;
    }

    public void setFechar(Date fechar) {
        this.fecha = fechar;
    }

    public ComentarioFestival() {
        this.idUser = "";
        this.idFestival = "";
        this.coment = "";
        this.fecha = new Date();
    }
}
