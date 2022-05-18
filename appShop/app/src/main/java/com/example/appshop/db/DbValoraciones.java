package com.example.appshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.example.appshop.entidades.Producto;
import com.example.appshop.entidades.Comercio;
import com.example.appshop.entidades.Usuario;
import com.example.appshop.entidades.Valoracion;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DbValoraciones extends DatabaseHelper {

    Context context;

    public DbValoraciones(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarValoracion(Valoracion valoracion) {

        long id = 0;
        try {
            DatabaseHelper dbhelper = new DatabaseHelper(context);
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nick", valoracion.getNick().getNick());
            values.put("id_com", valoracion.getId_com().getId());
            values.put("id_prod", valoracion.getId_prod().getId());
            values.put("valoracion", valoracion.getValoracion());
            values.put("comentario", valoracion.getComentario());
            values.put("fecha", valoracion.getFecha().toString());

            id = db.insert(TABLA_VALORACIONES, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Valoracion> mostrarValoraciones() {

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        DbUsuario dbUsuario = new DbUsuario(context);
        DbComercio dbComercio = new DbComercio(context);
        DbProducto dbProducto = new DbProducto(context);

        ArrayList<Valoracion> listaValoraciones = new ArrayList<>();
        Cursor cursorValoraciones;

        cursorValoraciones = db.rawQuery("SELECT * FROM " + TABLA_VALORACIONES + " ORDER BY nick ASC", null);

        if (cursorValoraciones.moveToFirst()) {
            do {
                Usuario usuario = new Usuario();
                Comercio comercio = new Comercio();
                Producto producto = new Producto();
                Valoracion valoracion = new Valoracion();

                valoracion.setNick(dbUsuario.verUsuario(new Usuario(cursorValoraciones.getString(0))));
                valoracion.setId_com(dbComercio.verComercio(new Comercio(cursorValoraciones.getInt(1))));
                valoracion.setId_prod(dbProducto.verProducto(new Producto(cursorValoraciones.getInt(2))));
                valoracion.setValoracion(cursorValoraciones.getInt(3));
                valoracion.setComentario((cursorValoraciones.getString(4)));

                java.sql.Date sqlDate = java.sql.Date.valueOf(cursorValoraciones.getString(5));
                valoracion.setFecha(sqlDate);

                listaValoraciones.add(valoracion);
            } while (cursorValoraciones.moveToNext());
        }

        cursorValoraciones.close();

        return listaValoraciones;
    }

    public ArrayList<Valoracion> mostrarValoracionesProducto(int id_producto) {

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        DbUsuario dbUsuario = new DbUsuario(context);
        DbComercio dbComercio = new DbComercio(context);
        DbProducto dbProducto = new DbProducto(context);

        ArrayList<Valoracion> listaValoraciones = new ArrayList<>();
        Cursor cursorValoraciones;

        cursorValoraciones = db.rawQuery("SELECT * FROM " + TABLA_VALORACIONES + " WHERE id_prod = '" + id_producto + "' ORDER BY nick ASC", null);

        if (cursorValoraciones.moveToFirst()) {
            do {
                Usuario usuario = new Usuario();
                Comercio comercio = new Comercio();
                Producto producto = new Producto();
                Valoracion valoracion = new Valoracion();

                valoracion.setNick(dbUsuario.verUsuario(new Usuario(cursorValoraciones.getString(0))));
                valoracion.setId_com(dbComercio.verComercio(new Comercio(cursorValoraciones.getInt(1))));
                valoracion.setId_prod(dbProducto.verProducto(new Producto(cursorValoraciones.getInt(2))));
                valoracion.setValoracion(cursorValoraciones.getInt(3));
                valoracion.setComentario((cursorValoraciones.getString(4)));

                java.sql.Date sqlDate = java.sql.Date.valueOf(cursorValoraciones.getString(5));
                valoracion.setFecha(sqlDate);

                listaValoraciones.add(valoracion);
            } while (cursorValoraciones.moveToNext());
        }

        cursorValoraciones.close();

        return listaValoraciones;
    }

    public Valoracion verValoracion(Valoracion valoracion) {

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        DbUsuario dbUsuario = new DbUsuario(context);
        DbComercio dbComercio = new DbComercio(context);
        DbProducto dbProducto = new DbProducto(context);

        Valoracion valoracion_devuelta = null;
        Cursor cursorValoraciones;

        cursorValoraciones = db.rawQuery("SELECT * FROM " + TABLA_VALORACIONES + " WHERE nick = '" + valoracion.getNick().getNick() + "'AND id_com = '" + valoracion.getId_com().getId() + "' AND id_prod = '" + valoracion.getId_prod().getId() + "' LIMIT 1", null);

        if (cursorValoraciones.moveToFirst()) {
            Usuario usuario = new Usuario();
            Comercio comercio = new Comercio();
            Producto producto = new Producto();
            valoracion_devuelta = new Valoracion();

            valoracion_devuelta.setNick(dbUsuario.verUsuario(new Usuario(cursorValoraciones.getString(0))));
            valoracion_devuelta.setId_com(dbComercio.verComercio(new Comercio(cursorValoraciones.getInt(1))));
            valoracion_devuelta.setId_prod(dbProducto.verProducto(new Producto(cursorValoraciones.getInt(2))));
            valoracion_devuelta.setValoracion(cursorValoraciones.getInt(3));
            valoracion_devuelta.setComentario((cursorValoraciones.getString(4)));
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            try {
                valoracion.setFecha((Date)df.parse(cursorValoraciones.getString(5)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        cursorValoraciones.close();

        return valoracion_devuelta;
    }

    public boolean editarValoracion(Valoracion valoracion) {

        boolean correcto = false;

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLA_VALORACIONES + " SET valoracion = '" + valoracion.getValoracion() + "', comentario = '" + valoracion.getComentario() + "', fecha = '" + valoracion.getFecha() + "' WHERE nick = '" + valoracion.getNick().getNick() + "'AND id_com = '" + valoracion.getId_com().getId() + "' AND id_prod = '" + valoracion.getId_prod().getId() + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarValoracion(Valoracion valoracion) {

        boolean correcto = false;

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLA_VALORACIONES + " WHERE nick = '" + valoracion.getNick().getNick() + "'AND id_com = '" + valoracion.getId_com().getId() + "' AND id_prod = '" + valoracion.getId_prod().getId() + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}