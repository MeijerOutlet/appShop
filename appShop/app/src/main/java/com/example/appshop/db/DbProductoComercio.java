package com.example.appshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.example.appshop.entidades.Comercio;
import com.example.appshop.entidades.Producto;
import com.example.appshop.entidades.ProductoComercio;
import com.example.appshop.entidades.Promocion;

import java.util.ArrayList;

public class DbProductoComercio extends DatabaseHelper {

    Context context;

    public DbProductoComercio(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarProductoComercio(ProductoComercio productoComercio) {

        long id = 0;
        try {
            DatabaseHelper dbhelper = new DatabaseHelper(context);
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id_prod", productoComercio.getId_prod().getId());
            values.put("id_com", productoComercio.getId_com().getId());
            values.put("id_prom", productoComercio.getId_prom().getId());
            values.put("precio_total", productoComercio.getPrecio_total());
            values.put("descuento", productoComercio.getDescuento());
            values.put("importe_total_descontado", productoComercio.getImporte_total_descontado());
            values.put("cantidad", productoComercio.getCantidad());

            id = db.insert(TABLA_PRODUCTOS_COMERCIOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<ProductoComercio> mostrarProductoComercio() {

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        DbProducto dbProducto = new DbProducto(context);
        DbComercio dbComercio = new DbComercio(context);
        DbPromocion dbPromocion = new DbPromocion(context);

        ArrayList<ProductoComercio> listaProductosComercios = new ArrayList<>();
        Cursor cursorProductosComercios;

        cursorProductosComercios = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTOS_COMERCIOS + " ORDER BY id_prod ASC", null);

        if (cursorProductosComercios.moveToFirst()) {
            do {
                Producto producto = new Producto();
                Comercio comercio = new Comercio();
                Promocion promocion = new Promocion();
                ProductoComercio productoComercio = new ProductoComercio();

                productoComercio = new ProductoComercio();
                productoComercio.setId_prod(dbProducto.verProducto(new Producto(cursorProductosComercios.getInt(0))));
                productoComercio.setId_com(dbComercio.verComercio(new Comercio(cursorProductosComercios.getInt(1))));
                productoComercio.setId_prom(dbPromocion.verPromocion(new Promocion(cursorProductosComercios.getInt(2))));
                productoComercio.setPrecio_total(cursorProductosComercios.getDouble(3));
                productoComercio.setDescuento(cursorProductosComercios.getDouble(4));
                productoComercio.setImporte_total_descontado(cursorProductosComercios.getDouble(5));
                productoComercio.setCantidad(cursorProductosComercios.getInt(6));
                listaProductosComercios.add(productoComercio);
            } while (cursorProductosComercios.moveToNext());
        }

        cursorProductosComercios.close();

        return listaProductosComercios;
    }

    public ArrayList<ProductoComercio> filtrarProductosComerciosPrecio(double min, double max){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        DbProducto dbProducto = new DbProducto(context);
        DbComercio dbComercio = new DbComercio(context);
        DbPromocion dbPromocion = new DbPromocion(context);

        ArrayList<ProductoComercio> listaProductosComercios = new ArrayList<>();
        Cursor cursorProductosComercios;

        cursorProductosComercios = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTOS_COMERCIOS + " WHERE importe_total_descontado BETWEEN "  + min +  " AND " + max + " ORDER BY id_prod ASC", null);
        if (cursorProductosComercios.moveToFirst()) {
            do {
                Producto producto = new Producto();
                Comercio comercio = new Comercio();
                Promocion promocion = new Promocion();
                ProductoComercio productoComercio = new ProductoComercio();

                productoComercio = new ProductoComercio();
                productoComercio.setId_prod(dbProducto.verProducto(new Producto(cursorProductosComercios.getInt(0))));
                productoComercio.setId_com(dbComercio.verComercio(new Comercio(cursorProductosComercios.getInt(1))));
                productoComercio.setId_prom(dbPromocion.verPromocion(new Promocion(cursorProductosComercios.getInt(2))));
                productoComercio.setPrecio_total(cursorProductosComercios.getDouble(3));
                productoComercio.setDescuento(cursorProductosComercios.getDouble(4));
                productoComercio.setImporte_total_descontado(cursorProductosComercios.getDouble(5));
                productoComercio.setCantidad(cursorProductosComercios.getInt(6));
                listaProductosComercios.add(productoComercio);
            } while (cursorProductosComercios.moveToNext());
        }

        cursorProductosComercios.close();

        return listaProductosComercios;
    }

    public ArrayList<ProductoComercio> filtrarProductosComerciosComercio(int id_comercio){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        DbProducto dbProducto = new DbProducto(context);
        DbComercio dbComercio = new DbComercio(context);
        DbPromocion dbPromocion = new DbPromocion(context);

        ArrayList<ProductoComercio> listaProductosComercios = new ArrayList<>();
        Cursor cursorProductosComercios;

        cursorProductosComercios = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTOS_COMERCIOS + " WHERE id_com = '" + id_comercio +  "' ORDER BY id_prod ASC", null);
        if (cursorProductosComercios.moveToFirst()) {
            do {
                Producto producto = new Producto();
                Comercio comercio = new Comercio();
                Promocion promocion = new Promocion();
                ProductoComercio productoComercio = new ProductoComercio();

                productoComercio = new ProductoComercio();
                productoComercio.setId_prod(dbProducto.verProducto(new Producto(cursorProductosComercios.getInt(0))));
                productoComercio.setId_com(dbComercio.verComercio(new Comercio(cursorProductosComercios.getInt(1))));
                productoComercio.setId_prom(dbPromocion.verPromocion(new Promocion(cursorProductosComercios.getInt(2))));
                productoComercio.setPrecio_total(cursorProductosComercios.getDouble(3));
                productoComercio.setDescuento(cursorProductosComercios.getDouble(4));
                productoComercio.setImporte_total_descontado(cursorProductosComercios.getDouble(5));
                productoComercio.setCantidad(cursorProductosComercios.getInt(6));
                listaProductosComercios.add(productoComercio);
            } while (cursorProductosComercios.moveToNext());
        }

        cursorProductosComercios.close();

        return listaProductosComercios;
    }

    public ArrayList<ProductoComercio> filtrarProductosComerciosPrecioComercioOrdenado(double min, double max, int id_comercio, int orden){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        DbProducto dbProducto = new DbProducto(context);
        DbComercio dbComercio = new DbComercio(context);
        DbPromocion dbPromocion = new DbPromocion(context);

        ArrayList<ProductoComercio> listaProductosComercios = new ArrayList<>();

        Cursor cursorProductosComercios = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTOS_COMERCIOS + " WHERE importe_total_descontado BETWEEN "  + min +  " AND " + max + " AND id_com = '" + id_comercio + "' ORDER BY ir_prod DESC", null);
        if(orden==1){
            cursorProductosComercios = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTOS_COMERCIOS + " WHERE importe_total_descontado BETWEEN "  + min +  " AND " + max + " AND id_com = '" + id_comercio + "' ORDER BY importe_total_descontado ASC", null);
        } else if(orden==2) {
            cursorProductosComercios = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTOS_COMERCIOS + " WHERE importe_total_descontado BETWEEN "  + min +  " AND " + max + " AND id_com = '" + id_comercio + "' ORDER BY importe_total_descontado DESC", null);
        }

        if (cursorProductosComercios.moveToFirst()) {
            do {
                Producto producto = new Producto();
                Comercio comercio = new Comercio();
                Promocion promocion = new Promocion();
                ProductoComercio productoComercio = new ProductoComercio();

                productoComercio = new ProductoComercio();
                productoComercio.setId_prod(dbProducto.verProducto(new Producto(cursorProductosComercios.getInt(0))));
                productoComercio.setId_com(dbComercio.verComercio(new Comercio(cursorProductosComercios.getInt(1))));
                productoComercio.setId_prom(dbPromocion.verPromocion(new Promocion(cursorProductosComercios.getInt(2))));
                productoComercio.setPrecio_total(cursorProductosComercios.getDouble(3));
                productoComercio.setDescuento(cursorProductosComercios.getDouble(4));
                productoComercio.setImporte_total_descontado(cursorProductosComercios.getDouble(5));
                productoComercio.setCantidad(cursorProductosComercios.getInt(6));
                listaProductosComercios.add(productoComercio);
            } while (cursorProductosComercios.moveToNext());
        }

        cursorProductosComercios.close();

        return listaProductosComercios;
    }

    public ArrayList<ProductoComercio> filtrarProductosComerciosPrecioOrdenado(double min, double max, int orden){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        DbProducto dbProducto = new DbProducto(context);
        DbComercio dbComercio = new DbComercio(context);
        DbPromocion dbPromocion = new DbPromocion(context);

        ArrayList<ProductoComercio> listaProductosComercios = new ArrayList<>();

        Cursor cursorProductosComercios = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTOS_COMERCIOS + " WHERE importe_total_descontado BETWEEN "  + min +  " AND " + max + " ORDER BY ir_prod DESC", null);
        if(orden==1){
            cursorProductosComercios = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTOS_COMERCIOS + " WHERE importe_total_descontado BETWEEN "  + min +  " AND " + max + " ORDER BY importe_total_descontado ASC", null);
        } else if(orden==2) {
            cursorProductosComercios = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTOS_COMERCIOS + " WHERE importe_total_descontado BETWEEN "  + min +  " AND " + max + " ORDER BY importe_total_descontado DESC", null);
        }

        if (cursorProductosComercios.moveToFirst()) {
            do {
                Producto producto = new Producto();
                Comercio comercio = new Comercio();
                Promocion promocion = new Promocion();
                ProductoComercio productoComercio = new ProductoComercio();

                productoComercio = new ProductoComercio();
                productoComercio.setId_prod(dbProducto.verProducto(new Producto(cursorProductosComercios.getInt(0))));
                productoComercio.setId_com(dbComercio.verComercio(new Comercio(cursorProductosComercios.getInt(1))));
                productoComercio.setId_prom(dbPromocion.verPromocion(new Promocion(cursorProductosComercios.getInt(2))));
                productoComercio.setPrecio_total(cursorProductosComercios.getDouble(3));
                productoComercio.setDescuento(cursorProductosComercios.getDouble(4));
                productoComercio.setImporte_total_descontado(cursorProductosComercios.getDouble(5));
                productoComercio.setCantidad(cursorProductosComercios.getInt(6));
                listaProductosComercios.add(productoComercio);
            } while (cursorProductosComercios.moveToNext());
        }

        cursorProductosComercios.close();

        return listaProductosComercios;
    }

    public ArrayList<ProductoComercio> filtrarProductosComerciosComercioOrdenado(int id_comercio, int orden){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        DbProducto dbProducto = new DbProducto(context);
        DbComercio dbComercio = new DbComercio(context);
        DbPromocion dbPromocion = new DbPromocion(context);

        ArrayList<ProductoComercio> listaProductosComercios = new ArrayList<>();

        Cursor cursorProductosComercios = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTOS_COMERCIOS + " WHERE id_com = '" + id_comercio + "' ORDER BY ir_prod DESC", null);
        if(orden==1){
            cursorProductosComercios = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTOS_COMERCIOS + " WHERE  id_com = '" + id_comercio + "' ORDER BY importe_total_descontado ASC", null);
        } else if(orden==2) {
            cursorProductosComercios = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTOS_COMERCIOS + " WHERE  id_com = '" + id_comercio + "' ORDER BY importe_total_descontado DESC", null);
        }

        if (cursorProductosComercios.moveToFirst()) {
            do {
                Producto producto = new Producto();
                Comercio comercio = new Comercio();
                Promocion promocion = new Promocion();
                ProductoComercio productoComercio = new ProductoComercio();

                productoComercio = new ProductoComercio();
                productoComercio.setId_prod(dbProducto.verProducto(new Producto(cursorProductosComercios.getInt(0))));
                productoComercio.setId_com(dbComercio.verComercio(new Comercio(cursorProductosComercios.getInt(1))));
                productoComercio.setId_prom(dbPromocion.verPromocion(new Promocion(cursorProductosComercios.getInt(2))));
                productoComercio.setPrecio_total(cursorProductosComercios.getDouble(3));
                productoComercio.setDescuento(cursorProductosComercios.getDouble(4));
                productoComercio.setImporte_total_descontado(cursorProductosComercios.getDouble(5));
                productoComercio.setCantidad(cursorProductosComercios.getInt(6));
                listaProductosComercios.add(productoComercio);
            } while (cursorProductosComercios.moveToNext());
        }

        cursorProductosComercios.close();

        return listaProductosComercios;
    }

    public ArrayList<ProductoComercio> filtrarProductosComerciosPrecioComercio(double min, double max, int id_comercio){
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        DbProducto dbProducto = new DbProducto(context);
        DbComercio dbComercio = new DbComercio(context);
        DbPromocion dbPromocion = new DbPromocion(context);

        ArrayList<ProductoComercio> listaProductosComercios = new ArrayList<>();
        Cursor cursorProductosComercios;

        cursorProductosComercios = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTOS_COMERCIOS + " WHERE importe_total_descontado BETWEEN "  + min +  " AND " + max + " AND id_com = '" + id_comercio + "' ORDER BY id_prod ASC", null);
        if (cursorProductosComercios.moveToFirst()) {
            do {
                Producto producto = new Producto();
                Comercio comercio = new Comercio();
                Promocion promocion = new Promocion();
                ProductoComercio productoComercio = new ProductoComercio();

                productoComercio = new ProductoComercio();
                productoComercio.setId_prod(dbProducto.verProducto(new Producto(cursorProductosComercios.getInt(0))));
                productoComercio.setId_com(dbComercio.verComercio(new Comercio(cursorProductosComercios.getInt(1))));
                productoComercio.setId_prom(dbPromocion.verPromocion(new Promocion(cursorProductosComercios.getInt(2))));
                productoComercio.setPrecio_total(cursorProductosComercios.getDouble(3));
                productoComercio.setDescuento(cursorProductosComercios.getDouble(4));
                productoComercio.setImporte_total_descontado(cursorProductosComercios.getDouble(5));
                productoComercio.setCantidad(cursorProductosComercios.getInt(6));
                listaProductosComercios.add(productoComercio);
            } while (cursorProductosComercios.moveToNext());
        }

        cursorProductosComercios.close();

        return listaProductosComercios;
    }

    public ProductoComercio verProductoComercio(ProductoComercio productoComercio) {

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        DbProducto dbProducto = new DbProducto(context);
        DbComercio dbComercio = new DbComercio(context);
        DbPromocion dbPromocion = new DbPromocion(context);

        ProductoComercio productoComercio_devuelta = null;
        Cursor cursorProductoComercio;

        cursorProductoComercio = db.rawQuery("SELECT * FROM " + TABLA_PRODUCTOS_COMERCIOS + " WHERE id_prod = '" + productoComercio.getId_prod() + "' AND id_com = '" + productoComercio.getId_com() + "' LIMIT 1", null);

        if (cursorProductoComercio.moveToFirst()) {
            Producto producto = new Producto();
            Comercio comercio = new Comercio();
            Promocion promocion = new Promocion();
            productoComercio_devuelta = new ProductoComercio();

            productoComercio_devuelta = new ProductoComercio();
            productoComercio_devuelta.setId_prod(dbProducto.verProducto(new Producto(cursorProductoComercio.getInt(0))));
            productoComercio_devuelta.setId_com(dbComercio.verComercio(new Comercio(cursorProductoComercio.getInt(1))));
            productoComercio_devuelta.setId_prom(dbPromocion.verPromocion(new Promocion(cursorProductoComercio.getInt(2))));
            productoComercio_devuelta.setPrecio_total(cursorProductoComercio.getDouble(3));
            productoComercio_devuelta.setDescuento(cursorProductoComercio.getDouble(4));
            productoComercio_devuelta.setImporte_total_descontado(cursorProductoComercio.getDouble(5));
            productoComercio_devuelta.setCantidad(cursorProductoComercio.getInt(6));

        }

        cursorProductoComercio.close();

        return productoComercio_devuelta;
    }

    public boolean editarProductoComercio(ProductoComercio productoComercio) {

        boolean correcto = false;

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLA_PRODUCTOS_COMERCIOS + " SET id_prom = '" + productoComercio.getId_prom().getId() + "', precio_total = '" + productoComercio.getPrecio_total() + "', descuento = '" + productoComercio.getDescuento() + "', importe_total_descontado = '" + productoComercio.getImporte_total_descontado() + "' , cantidad = '" + productoComercio.getCantidad() + "' + WHERE id_prod = '" + productoComercio.getId_prod().getId() + "' AND id_com = '" + productoComercio.getId_com().getId() + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarProductoComercio(ProductoComercio productoComercio) {

        boolean correcto = false;

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLA_PRODUCTOS_COMERCIOS + " WHERE id_prod = '" + productoComercio.getId_prod().getId() + "' AND id_com = '" + productoComercio.getId_com().getId() + "'");
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