package com.example.appshop.entidades;

public class Producto {

    private int id;
    private String nombre;
    private String descripcion;
    private String talla;
    private Categoria categoria;

    public Producto(int id, String nombre, String descripcion, String talla, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.talla = talla;
        this.categoria = categoria;
    }

    public Producto() {
    }

    public Producto(int id) {
        this.id = id;
    }

    public int getId() { return id; }

    public String getNombre() { return nombre; }

    public String getDescripcion() { return descripcion; }

    public String getTalla() { return talla; }


    public void setId(int id) { this.id = id; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public void setTalla(String talla) { this.talla = talla; }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
