package com.example.appshop.entidades;

import java.sql.Date;

public class Valoracion {

    private Usuario nick;
    private Comercio id_com;
    private Producto id_prod;
    private int valoracion;
    private String comentario;
    private Date fecha;

    public Valoracion(Usuario nick, Comercio id_com, Producto id_prod, int valoracion, String comentario, Date fecha) {
        this.nick = nick;
        this.id_com = id_com;
        this.id_prod = id_prod;
        this.valoracion = valoracion;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public Valoracion() {
    }

    public Valoracion(Usuario nick, Comercio id_com, Producto id_prod) {
        this.nick = nick;
        this.id_com = id_com;
        this.id_prod = id_prod;
    }

    public Usuario getNick() { return nick; }

    public Comercio getId_com() { return id_com; }

    public Producto getId_prod() { return id_prod; }

    public int getValoracion() { return valoracion; }

    public String getComentario() { return comentario; }

    public Date getFecha() { return fecha; }

    public void setNick(Usuario nick) { this.nick = nick; }

    public void setId_com(Comercio id_com) { this.id_com = id_com; }

    public void setId_prod(Producto id_prod) { this.id_prod = id_prod; }

    public void setValoracion(int valoracion) { this.valoracion = valoracion; }

    public void setComentario(String comentario) { this.comentario = comentario; }

    public void setFecha(Date fecha) { this.fecha = fecha; }
}

