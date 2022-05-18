package com.example.appshop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.example.appshop.entidades.Usuario;

import java.util.ArrayList;

public class DbUsuario extends DatabaseHelper {

    Context context;

    public DbUsuario(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarUsuario(Usuario usuario) {

        long id = 0;
        try {
            DatabaseHelper dbhelper = new DatabaseHelper(context);
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nick", usuario.getNick());
            values.put("nombre", usuario.getNombre());
            values.put("apellidos", usuario.getApellidos());
            values.put("email", usuario.getEmail());
            values.put("password", usuario.getPassword());

            id = db.insert(TABLA_USUARIOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

    return id;
    }

    public ArrayList<Usuario> mostrarUsuarios() {

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        Usuario usuario;
        Cursor cursorUsuarios;

        cursorUsuarios = db.rawQuery("SELECT * FROM " + TABLA_USUARIOS + " ORDER BY nick ASC", null);

        if (cursorUsuarios.moveToFirst()) {
            do {
                usuario = new Usuario();
                usuario.setNick(cursorUsuarios.getString(0));
                usuario.setNombre(cursorUsuarios.getString(1));
                usuario.setApellidos(cursorUsuarios.getString(2));
                usuario.setEmail(cursorUsuarios.getString(3));
                usuario.setPassword((cursorUsuarios.getString(4)));
                listaUsuarios.add(usuario);
            } while (cursorUsuarios.moveToNext());
        }

        cursorUsuarios.close();

        return listaUsuarios;
    }

    public Usuario verUsuario(Usuario usuario) {

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Usuario usuario_devuelta = null;
        Cursor cursorUsuarios;

        cursorUsuarios = db.rawQuery("SELECT * FROM " + TABLA_USUARIOS + " WHERE nick = '" + usuario.getNick() + "' LIMIT 1", null);

        if (cursorUsuarios.moveToFirst()) {
            usuario_devuelta = new Usuario();
            usuario_devuelta.setNick(cursorUsuarios.getString(0));
            usuario_devuelta.setNombre(cursorUsuarios.getString(1));
            usuario_devuelta.setApellidos(cursorUsuarios.getString(2));
            usuario_devuelta.setEmail(cursorUsuarios.getString(3));
            usuario_devuelta.setPassword((cursorUsuarios.getString(4)));
        }

        cursorUsuarios.close();

        return usuario_devuelta;
    }

    public boolean editarUsuario(Usuario usuario) {

        boolean correcto = false;

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLA_USUARIOS + " SET nombre = '" + usuario.getNombre() + "', apellidos = '" + usuario.getApellidos() + "', email = '" + usuario.getEmail() + "', password = '" + usuario.getPassword() + "' +  WHERE nick='" + usuario.getNick() + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarUsuario(Usuario usuario) {

        boolean correcto = false;

        DatabaseHelper dbHelper = new DatabaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLA_USUARIOS + " WHERE nick = '" + usuario.getNick() + "'");
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