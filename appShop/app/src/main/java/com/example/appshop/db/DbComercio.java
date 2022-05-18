package com.example.appshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.example.appshop.entidades.Comercio;

import java.util.ArrayList;

public class DbComercio extends DatabaseHelper {

    Context context;

    public DbComercio(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarComercio(Comercio comercio) {

        long id = 0;
        try {
            DatabaseHelper dbhelper = new DatabaseHelper(context);
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            //values.put("id", comercio.getId()); Es AUTO_INCREMENTAL
            values.put("nombre", comercio.getNombre());
            values.put("cif", comercio.getCif());
            values.put("direccion", comercio.getDireccion());
            values.put("region", comercio.getRegion());

            id = db.insert(TABLA_COMERCIOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

    return id;
    }

    public ArrayList<Comercio> mostrarComercios() {

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Comercio> listaComercios = new ArrayList<>();
        Comercio comercio;
        Cursor cursorComercios;

        cursorComercios = db.rawQuery("SELECT * FROM " + TABLA_COMERCIOS + " ORDER BY id ASC", null);

        if (cursorComercios.moveToFirst()) {
            do {
                comercio = new Comercio();
                comercio.setId(cursorComercios.getInt(0));
                comercio.setNombre(cursorComercios.getString(1));
                comercio.setCif(cursorComercios.getString(2));
                comercio.setDireccion(cursorComercios.getString(3));
                comercio.setRegion((cursorComercios.getString(4)));
                listaComercios.add(comercio);
            } while (cursorComercios.moveToNext());
        }

        cursorComercios.close();

        return listaComercios;
    }

    public Comercio verComercio(Comercio comercio) {

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Comercio comercio_devuelta = null;
        Cursor cursorComercios;

        cursorComercios = db.rawQuery("SELECT * FROM " + TABLA_COMERCIOS + " WHERE id = '" + comercio.getId() + "' LIMIT 1", null);

        if (cursorComercios.moveToFirst()) {
            comercio_devuelta = new Comercio();
            comercio_devuelta.setId(cursorComercios.getInt(0));
            comercio_devuelta.setNombre(cursorComercios.getString(1));
            comercio_devuelta.setCif(cursorComercios.getString(2));
            comercio_devuelta.setDireccion(cursorComercios.getString(3));
            comercio_devuelta.setRegion((cursorComercios.getString(4)));
        }

        cursorComercios.close();

        return comercio_devuelta;
    }

    public boolean editarComercio(Comercio comercio) {

        boolean correcto = false;

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLA_COMERCIOS + " SET nombre = '" + comercio.getNombre() + "', cif = '" + comercio.getCif() + "', direccion = '" + comercio.getDireccion() + "', region = '" + comercio.getRegion() + "' +  WHERE id='" + comercio.getId() + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarComercio(Comercio comercio) {

        boolean correcto = false;

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLA_COMERCIOS + " WHERE id = '" + comercio.getId() + "'");
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