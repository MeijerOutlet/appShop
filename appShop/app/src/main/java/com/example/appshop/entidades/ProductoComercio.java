package com.example.appshop.entidades;

public class ProductoComercio {

    private Producto id_prod;
    private Comercio id_com;
    private Promocion id_prom;
    private double precio_total;
    private double descuento;
    private double importe_total_descontado;
    private int cantidad;

    public ProductoComercio() {
    }

    public ProductoComercio(Producto id_prod, Comercio id_com, Promocion id_prom, double precio_total, double descuento, double importe_total_descontado, int cantidad) {
        this.id_prod = id_prod;
        this.id_com = id_com;
        this.id_prom = id_prom;
        this.precio_total = precio_total;
        this.descuento = descuento;
        this.importe_total_descontado = importe_total_descontado;
        this.cantidad = cantidad;
    }

    public ProductoComercio(Producto id_prod, Comercio id_com) {
        this.id_prod = id_prod;
        this.id_com = id_com;
    }

    public Producto getId_prod() { return id_prod; }

    public Comercio getId_com() { return id_com; }

    public Promocion getId_prom() { return id_prom; }

    public double getPrecio_total() { return precio_total; }

    public double getDescuento() { return descuento; }

    public double getImporte_total_descontado() { return importe_total_descontado; }

    public int getCantidad() { return cantidad; }

    public void setId_prod(Producto id_prod) { this.id_prod = id_prod; }

    public void setId_com(Comercio id_com) { this.id_com = id_com; }

    public void setId_prom(Promocion id_prom) { this.id_prom = id_prom; }

    public void setPrecio_total(double precio_total) { this.precio_total = precio_total; }

    public void setDescuento(double descuento) { this.descuento = descuento; }

    public void setImporte_total_descontado(double importe_total_descontado) { this.importe_total_descontado = importe_total_descontado; }

    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

}

