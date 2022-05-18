package com.example.appshop;

import android.content.Context;

import com.example.appshop.entidades.ProductoComercio;

import java.util.ArrayList;

public class ControladorProductosCompra {
    Context context;

    public ControladorProductosCompra(Context context) {
        this.context = context;
    }
    static void addProduct(ProductoCarrito p){
        boolean exists = false;
        int pos = 0;
        for(ProductoCarrito pCarrito: carrito_compra.productosCarrito){
            if((pCarrito.getId_prod()==p.getId_prod() && pCarrito.getId_comercio()==p.getId_comercio())){
                exists = true;
                pos = carrito_compra.productosCarrito.indexOf(pCarrito);
                break;
            }
        }
        if(!exists){
            carrito_compra.productosCarrito.add(p);
        }else{
            carrito_compra.productosCarrito.get(pos).setCantidad(p.getCantidad()+carrito_compra.productosCarrito.get(pos).getCantidad());
        }
        carrito_compra.adapter.notifyDataSetChanged();
        //carrito_compra.refresh();
    }
    static void removeProduct(ProductoCarrito p){
        ProductoComercio productoEncontrado = findItem(p);
        if (productoEncontrado!=null){
            productoEncontrado.setCantidad(productoEncontrado.getCantidad()+p.getCantidad());
            AppChollosMain.productos.set(AppChollosMain.productos.indexOf(productoEncontrado),productoEncontrado);
            AppChollosMain.adapter.notifyDataSetChanged();
        }
        carrito_compra.productosCarrito.remove(p);
        carrito_compra.adapter.notifyDataSetChanged();
        carrito_compra.refresh();
    }
    static void addItem(ArrayList<ProductoCarrito> productoCarritos, int position){
        ProductoComercio productoEncontrado = findItem(productoCarritos.get(position));
        if (productoEncontrado!=null){
            productoEncontrado.setCantidad(productoEncontrado.getCantidad()-1);
            AppChollosMain.productos.set(AppChollosMain.productos.indexOf(productoEncontrado),productoEncontrado);
            AppChollosMain.adapter.notifyDataSetChanged();
        }
        productoCarritos.get(position).setCantidad(productoCarritos.get(position).getCantidad()+1);
        carrito_compra.adapter.notifyDataSetChanged();
        carrito_compra.refresh();
    }
    static void removeItem(ArrayList<ProductoCarrito> productoCarritos, int position){
        ProductoComercio productoEncontrado = findItem(productoCarritos.get(position));
        if (productoEncontrado!=null){
            productoEncontrado.setCantidad(productoEncontrado.getCantidad()+1);
            AppChollosMain.productos.set(AppChollosMain.productos.indexOf(productoEncontrado),productoEncontrado);
            AppChollosMain.adapter.notifyDataSetChanged();
        }
        productoCarritos.get(position).setCantidad(productoCarritos.get(position).getCantidad()-1);
        carrito_compra.adapter.notifyDataSetChanged();
        carrito_compra.refresh();
    }
    private static ProductoComercio findItem(ProductoCarrito productoCarrito) {
        for (ProductoComercio p : AppChollosMain.productos) {
            if (productoCarrito.getId_comercio()==p.getId_com().getId() && productoCarrito.getId_prod()==p.getId_prod().getId()) {
                return p;
            }
        }
        return null;
    }
}
