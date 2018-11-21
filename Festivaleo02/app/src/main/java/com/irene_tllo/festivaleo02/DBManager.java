package com.irene_tllo.festivaleo02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;



/**
 * Created by irene_ on 14/11/2016.
 */

public class DBManager {

    Context ctx;

    public static final String TABLE_NAME_Usuario="Usuario";

    public static final String AT_ID = "id";
    public static final String AT_Nombre = "nombre";
    public static final String AT_Telefono = "telefono";
    public static final String AT_Ciudad = "ciudad";
    public static final String AT_Anio = "anio";
    public static final String AT_Correo = "correo";
    public static final String AT_Password = "password";

    //sentencia para crear la tabla
    public static final String CREATE_TABLE_USUARIO = "create table " + TABLE_NAME_Usuario + " ("
            + AT_ID + " integer primary key autoincrement, "
            + AT_Nombre + " text is unique, "
            + AT_Telefono + " text not null, "
            + AT_Ciudad + " text not null, "
            + AT_Anio + " text not null, "
            + AT_Correo + " text is unique, "
            + AT_Password + " text not null);";


    public static final String TABLE_NAME_Festivales="Festivales";

    public static final String AT_IDF = "id";
    public static final String AT_NombreF = "nombre";
    public static final String AT_Lugar = "Lugar";
    public static final String AT_Url = "Url";
    public static final String AT_Fecha = "Fecha";
    public static final String AT_Asistentes = "Asistentes";
    public static final String AT_Likes = "Likes";
    public static final String AT_Artistas = "Artistas";


    //sentencia para crear la tabla
    public static final String CREATE_TABLE_FESTIVALES = "create table " + TABLE_NAME_Festivales + " ("
            + AT_IDF + " integer primary key autoincrement, "
            + AT_NombreF + " text not null, "
            + AT_Lugar + " text not null, "
            + AT_Url + " text not null, "
            + AT_Fecha + " text not null, "
            + AT_Asistentes + " integer default 0, "
            + AT_Likes + " integer default 0, "
            + AT_Artistas + " integer default 0);";

    /* TABLA Valoraciones */
    public static final String TABLE_NAME_AsistentesF = "Asistentes";
    public static final String AT_IDA = "_id";
    public static final String AT_ID_FestivalA = "_idFestival";
    public static final String AT_ID_UsuarioA = "_idUsuario";

    public static final String TABLE_NAME_Asistentes = "create table " + TABLE_NAME_AsistentesF + "( "
            + AT_IDA + " integer primary key autoincrement, "
            + AT_ID_FestivalA + " integer, "
            + AT_ID_UsuarioA + " integer, "
            + " FOREIGN KEY " + "(" + AT_ID_UsuarioA + ")" + " REFERENCES " + TABLE_NAME_Usuario + " (" + AT_ID + "),"
            + " FOREIGN KEY " + "(" + AT_ID_FestivalA + ")" + " REFERENCES " + TABLE_NAME_Festivales + " (" + AT_IDF + ")"
            + " );";


    /* TABLA Valoraciones */
    public static final String TABLE_NAME_Valoraciones = "Valoraciones";
    public static final String AT_IDV = "_id";
    public static final String AT_ID_Festival = "_idFestival";
    public static final String AT_ID_Usuario = "_idUsuario";
    public static final String AT_Puntuacion = "puntuacion";
    public static final String AT_Comentario = "comentario";

    public static final String CREATE_TABLE_VALORACIONES = "create table " + TABLE_NAME_Valoraciones + "( "
            + AT_IDV + " integer primary key autoincrement, "
            + AT_ID_Festival + " integer, "
            + AT_ID_Usuario + " integer, "
            + AT_Puntuacion + " integer default 0, "
            + AT_Comentario + " text, "
            + " FOREIGN KEY " + "(" + AT_ID_Usuario + ")" + " REFERENCES " + TABLE_NAME_Usuario + " (" + AT_ID + "),"
            + " FOREIGN KEY " + "(" + AT_ID_Festival + ")" + " REFERENCES " + TABLE_NAME_Festivales + " (" + AT_IDF + ")"
            + " );";


    private DBHelper helper;
    private SQLiteDatabase db;

    //constructor
    public DBManager(Context context) {
        //para crear la base de datos
        helper = new DBHelper(context);
        //devuelve la base de datos en modo escritura
        db = helper.getWritableDatabase();
        ctx=context;
    }

    public void abrir() {
        helper = new DBHelper(ctx);
        db = helper.getWritableDatabase();
    }

    public ContentValues generarValoresUsuario(String nombre, String telefono, String ciudad, String anio, String email, String password) {
        ContentValues valores = new ContentValues();
        valores.put(AT_Nombre, nombre);
        valores.put(AT_Telefono, telefono);
        valores.put(AT_Ciudad, ciudad);
        valores.put(AT_Anio, anio);
        valores.put(AT_Correo, email);
        valores.put(AT_Password, password);
        return valores;
    }

    public long insertarUsuario(String nombre, String telefono, String ciudad, String anio, String email, String password) {
        ContentValues valores = new ContentValues();
        valores.put(AT_Nombre, nombre);
        valores.put(AT_Telefono, telefono);
        valores.put(AT_Ciudad, ciudad);
        valores.put(AT_Anio, anio);
        valores.put(AT_Correo, email);
        valores.put(AT_Password, password);
        return db.insert(TABLE_NAME_Usuario, null, valores);
    }

    //con esto devolvemos 3 campos de todos los registros de la base de datos
    public Cursor cargarCursorUsuarios() {
        String[] columnas = new String[]{AT_ID, AT_Nombre};
        return db.query(TABLE_NAME_Usuario, columnas, null, null, null, null, null);
    }

    public static void insertarUsuariosBase(SQLiteDatabase db) {
        //Usuario 1
        ContentValues valores = new ContentValues();
        valores.put(AT_Nombre, "Judit");
        valores.put(AT_Telefono, "660408435");
        valores.put(AT_Ciudad, "Caceres");
        valores.put(AT_Anio, "22");
        valores.put(AT_Correo, "jujimenez@gmail.com");
        valores.put(AT_Password, "judit93");
        long ident =  db.insert(TABLE_NAME_Usuario, null, valores);
        Log.i("hola","INSERTADOOOOOOOOOOOOOOOOOOOOOOOOOOOO "+ident);
        valores.clear();
    }

    public void dbClose() {
        db.close();
    }


}
