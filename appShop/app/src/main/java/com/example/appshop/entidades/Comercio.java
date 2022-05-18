package com.example.appshop.entidades;

public class Comercio {

    private int id;
    private String nombre;
    private String cif;
    private String direccion;
    private String region;

    public Comercio(int id, String nombre, String cif, String direccion, String region) {
        this.id = id;
        this.nombre = nombre;
        this.cif = cif;
        this.direccion = direccion;
        this.region = region;
    }

    public Comercio() {
    }

    public Comercio(int id) {
        this.id = id;
    }

    public int getId() { return id; }

    public String getNombre() { return nombre; }

    public String getCif() { return cif; }

    public String getDireccion() { return direccion; }

    public String getRegion() { return region; }

    public void setId(int id) { this.id = id; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public void setCif(String cif) { this.cif = cif; }

    public void setDireccion(String direccion) { this.direccion = direccion; }

    public void setRegion(String region) { this.region = region; }
}
