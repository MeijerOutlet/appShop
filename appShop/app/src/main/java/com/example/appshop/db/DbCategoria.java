package com.example.appshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.example.appshop.entidades.Categoria;

import java.util.ArrayList;

public class DbCategoria extends DatabaseHelper {

    Context context;

    public DbCategoria(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarCategoria(Categoria categoria) {

        long id = 0;
        try {
            DatabaseHelper dbhelper = new DatabaseHelper(context);
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            //values.put("id", categoria.getId()); Es AUTO_INCREMENTAL
            values.put("descripcion", categoria.getDescripcion());

            id = db.insert(TABLA_CATEGORIAS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Categoria> mostrarCategorias() {

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Categoria> listaCategorias = new ArrayList<>();
        Categoria categoria;
        Cursor cursorCategorias;

        cursorCategorias = db.rawQuery("SELECT * FROM " + TABLA_CATEGORIAS + " ORDER BY id ASC", null);

        if (cursorCategorias.moveToFirst()) {
            do {
                categoria = new Categoria();
                categoria.setId(cursorCategorias.getInt(0));
                categoria.setDescripcion(cursorCategorias.getString(1));
                listaCategorias.add(categoria);
            } while (cursorCategorias.moveToNext());
        }

        cursorCategorias.close();

        return listaCategorias;
    }

    public Categoria verCategoria(Categoria categoria) {

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Categoria categoria_devuelta = null;
        Cursor cursorCategorias;

        cursorCategorias = db.rawQuery("SELECT * FROM " + TABLA_CATEGORIAS + " WHERE id = '" + categoria.getId() + "' LIMIT 1", null);

        if (cursorCategorias.moveToFirst()) {
            categoria_devuelta = new Categoria();

            categoria_devuelta.setId(cursorCategorias.getInt(0));
            categoria_devuelta.setDescripcion((cursorCategorias.getString(1)));
        }

        cursorCategorias.close();

        return categoria_devuelta;
    }

}
