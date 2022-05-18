package com.example.appshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.example.appshop.entidades.Categoria;
import com.example.appshop.entidades.Producto;

import java.util.ArrayList;

public class DbProducto extends DatabaseHelper {

    Context context;

    public DbProducto(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarProducto(Producto producto) {

        long id = 0;
        try {
            DatabaseHelper dbhelper = new DatabaseHelper(context);
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            //values.put("id", producto.getId()); Es AUTO_INCREMENTAL
            values.put("nombre", producto.getNombre());
            values.put("descripcion", producto.getDescripcion());
            values.put("talla", producto.getTalla());
            values.put("categoria", producto.getCategoria().getId());

            id = db.insert(TABLA_PRODUCTOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

    return id;
    }

    public ArrayList<Producto> mostrarProductos() {

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        DbCategoria dbCategoria = new DbCategoria(context);
        ArrayList<Producto> listaProductos = new ArrayList<>();
        Producto producto;
        Cursor cursorProductos;

        cursorProductos = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTOS + " ORDER BY id ASC", null);

        if (cursorProductos.moveToFirst()) {
            do {
                producto = new Producto();
                producto.setId(cursorProductos.getInt(0));
                producto.setNombre(cursorProductos.getString(1));
                producto.setDescripcion(cursorProductos.getString(2));
                producto.setTalla(cursorProductos.getString(3));
                producto.setCategoria(dbCategoria.verCategoria(new Categoria((cursorProductos.getInt(4)))));
                listaProductos.add(producto);
            } while (cursorProductos.moveToNext());
        }

        cursorProductos.close();

        return listaProductos;
    }

    public Producto verProducto(Producto producto) {

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        DbCategoria dbCategoria = new DbCategoria(context);
        Producto producto_devuelta = null;
        Cursor cursorProductos;

        cursorProductos = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTOS + " WHERE id = '" + producto.getId() + "' LIMIT 1", null);

        if (cursorProductos.moveToFirst()) {
            producto_devuelta = new Producto();
            producto_devuelta.setId(cursorProductos.getInt(0));
            producto_devuelta.setNombre(cursorProductos.getString(1));
            producto_devuelta.setDescripcion(cursorProductos.getString(2));
            producto_devuelta.setTalla(cursorProductos.getString(3));
            producto_devuelta.setCategoria(dbCategoria.verCategoria(new Categoria((cursorProductos.getInt(4)))));
        }

        cursorProductos.close();

        return producto_devuelta;
    }

    public boolean editarProducto(Producto producto) {

        boolean correcto = false;

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLA_PRODUCTOS + " SET nombre = '" + producto.getNombre() + "', descripcion = '" + producto.getDescripcion() + "', talla = '" + producto.getTalla() + "', categoria = '" + producto.getCategoria().getId()+ "' +  WHERE id='" + producto.getId() + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarProducto(Producto producto) {

        boolean correcto = false;

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLA_PRODUCTOS + " WHERE id = '" + producto.getId() + "'");
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