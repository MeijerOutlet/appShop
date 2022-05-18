package com.example.appshop.entidades;

import java.sql.Date;

public class Promocion {

    private int id;
    private String nombre;
    private double tipo_descuento;
    private Date fecha_desde_valida;
    private Date fecha_hasta_valida;

    public Promocion(int id, String nombre, double tipo_descuento, Date fecha_desde_valida, Date fecha_hasta_valida) {
        this.id = id;
        this.nombre = nombre;
        this.tipo_descuento = tipo_descuento;
        this.fecha_desde_valida = fecha_desde_valida;
        this.fecha_hasta_valida = fecha_hasta_valida;
    }

    public Promocion() {
    }

    public Promocion(int id) {
        this.id = id;
    }

    public int getId() { return id; }

    public String getNombre() { return nombre; }

    public double getTipo_descuento() { return tipo_descuento; }

    public Date getFecha_desde_valida() { return fecha_desde_valida; }

    public Date getFecha_hasta_valida() { return fecha_hasta_valida; }

    public void setId(int id) { this.id = id; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public void setTipo_descuento(double tipo_descuento) { this.tipo_descuento = tipo_descuento; }

    public void setFecha_desde_valida(Date fecha_desde_valida) { this.fecha_desde_valida = fecha_desde_valida; }

    public void setFecha_hasta_valida(Date fecha_hasta_valida) { this.fecha_hasta_valida = fecha_hasta_valida; }
}
