package com.example.appshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.example.appshop.entidades.Promocion;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DbPromocion extends DatabaseHelper {

    Context context;

    public DbPromocion(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarPromocion(Promocion promocion) {

        long id = 0;
        try {
            DatabaseHelper dbhelper = new DatabaseHelper(context);
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            //values.put("id", producto.getId()); Es AUTO_INCREMENTAL
            values.put("nombre", promocion.getNombre());
            values.put("tipo_descuento", promocion.getTipo_descuento());
            values.put("fecha_desde_valida", promocion.getFecha_desde_valida().toString());
            values.put("fecha_hasta_valida", promocion.getFecha_hasta_valida().toString());

            id = db.insert(TABLA_PROMOCIONES, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

    return id;
    }

    public ArrayList<Promocion> mostrarPromociones() {

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Promocion> listaPromociones = new ArrayList<>();
        Promocion promocion;
        Cursor cursorPromociones;

        cursorPromociones = db.rawQuery("SELECT * FROM " + TABLA_PROMOCIONES + " ORDER BY id ASC", null);

        if (cursorPromociones.moveToFirst()) {
            do {
                promocion = new Promocion();
                promocion.setId(cursorPromociones.getInt(0));
                promocion.setNombre(cursorPromociones.getString(1));
                promocion.setTipo_descuento(cursorPromociones.getDouble(2));
                DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    promocion.setFecha_desde_valida((Date)df.parse(cursorPromociones.getString(3)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    promocion.setFecha_hasta_valida((Date)df.parse(cursorPromociones.getString(4)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                listaPromociones.add(promocion);
            } while (cursorPromociones.moveToNext());
        }

        cursorPromociones.close();

        return listaPromociones;
    }

    public Promocion verPromocion(Promocion promocion) {

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Promocion promocion_devuelta = null;
        Cursor cursorPromociones;

        cursorPromociones = db.rawQuery("SELECT * FROM " + TABLA_PROMOCIONES + " WHERE id = '" + promocion.getId() + "' LIMIT 1", null);

        if (cursorPromociones.moveToFirst()) {
            promocion_devuelta = new Promocion();
            promocion.setId(cursorPromociones.getInt(0));
            promocion.setNombre(cursorPromociones.getString(1));
            promocion.setTipo_descuento(cursorPromociones.getDouble(2));
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            try {
                promocion.setFecha_desde_valida((Date)df.parse(cursorPromociones.getString(3)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                promocion.setFecha_hasta_valida((Date)df.parse(cursorPromociones.getString(4)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        cursorPromociones.close();

        return promocion_devuelta;
    }

    public boolean editarPromocion(Promocion promocion) {

        boolean correcto = false;

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLA_PROMOCIONES + " SET nombre = '" + promocion.getNombre() + "', tipo_descuento = '" + promocion.getTipo_descuento() + "', fecha_desde_valida = '" + promocion.getFecha_desde_valida() + "', fecha_hasta_valida = '" + promocion.getFecha_hasta_valida() + "' +  WHERE id='" + promocion.getId() + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarPromocion(Promocion promocion) {

        boolean correcto = false;

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLA_PROMOCIONES + " WHERE id = '" + promocion.getId() + "'");
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