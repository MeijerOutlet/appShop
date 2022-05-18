package com.example.appshop;

public class ProductoCarrito {
    String nombre, comercio_Nombre;
    int img, cantidad, stock, id_comercio, id_prod;
    double precio, precioDescuento;

    public ProductoCarrito(String nombre, double precioDescuento, double precio, int cantidad, int img, int stock, int id_comercio, int id_prod, String comercio_Nombre) {
        this.nombre = nombre;
        this.precio = precio;
        this.precioDescuento = precioDescuento;
        this.cantidad = cantidad;
        this.img = img;
        this.stock = stock;
        this.id_comercio = id_comercio;
        this.id_prod = id_prod;
        this.comercio_Nombre = comercio_Nombre;
    }

    public String getComercio_Nombre() {
        return comercio_Nombre;
    }

    public void setComercio_Nombre(String comercio_Nombre) {
        this.comercio_Nombre = comercio_Nombre;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public int getId_comercio() {
        return id_comercio;
    }

    public void setId_comercio(int id_comercio) {
        this.id_comercio = id_comercio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecioDescuento() {
        return precioDescuento;
    }

    public void setPrecioDescuento(double precioDescuento) {
        this.precioDescuento = precioDescuento;
    }
}
