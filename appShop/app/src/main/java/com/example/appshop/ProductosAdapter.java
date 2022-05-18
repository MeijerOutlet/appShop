package com.example.appshop;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.appshop.db.DbProductoComercio;
import com.example.appshop.entidades.ProductoComercio;
import com.example.appShop.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductosAdapter extends BaseAdapter {
    ArrayList<ProductoComercio> listaProductos;
    ArrayList<ProductoComercio> listaOriginal;
    DbProductoComercio dbProductoComercio;
    Context contexto;

    public ProductosAdapter(ArrayList<ProductoComercio> arrayList, Context contexto) {
        this.listaProductos = arrayList;
        this.contexto = contexto;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaProductos);
        dbProductoComercio = new DbProductoComercio(contexto);
    }
    public void buscar(final String txtBuscar){
        int longitud = txtBuscar.length();
        //System.out.println(longitud);
        if (longitud == 0){
            listaProductos.clear();
            listaProductos.addAll(listaOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<ProductoComercio> collection = listaProductos.stream().filter(i -> i.getId_prod().getNombre().toLowerCase().contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collection);
            }
            else{

                for (ProductoComercio p : listaProductos) {
                    if (!(p.getId_prod().getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))){
                        listaProductos.remove(p);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
    //precio, comercio, preciioYcomercio, precioYbuscar, buscarYcomercio buscar_precio_comercio
//    public void filtrar(boolean precio, boolean comercio, boolean precioYcomercio, double min, double max, int id_com, String text){
//        if (precio){
//            //listaProductos.clear();
//            //listaProductos.addAll(listaOriginal);
//
//            for (int i = 0; i < listaOriginal.size(); i++) {
//                ProductoComercio p = listaOriginal.get(i);
//                if (p.getImporte_total_descontado()<min || p.getImporte_total_descontado()>max){
//                    listaProductos.remove(p);
//                }else if(!(listaProductos.contains(p))){listaProductos.add(i,p);}
//            }
//            //listaProductos.addAll(dbProductoComercio.filtrarProductosComerciosPrecio(min,max));
//        }
//        else if (comercio){
//            for (ProductoComercio p : listaOriginal) {
//                if (p.getId_com().getId()==id_com){
//                    listaProductos.add(p);
//                }
//            }
//
//            Collections.sort(listaProductos, new Comparator<ProductoComercio>() {
//                @Override
//                public int compare(ProductoComercio o1, ProductoComercio o2) {
//                    if(o1.getId_com().getId()==o2.getId_com().getId()){
//                        return 0;
//                    }else{
//                        return 1;
//                    }
//                }
//            });
////            listaProductos.addAll(dbProductoComercio.filtrarProductosComerciosComercio(id_com));
//        }
//        else if(precioYcomercio){
//            for (ProductoComercio p : listaOriginal) {
//                if (p.getImporte_total_descontado()>=min && p.getImporte_total_descontado()<=max && p.getId_com().getId()==id_com){
//                    listaProductos.add(p);
//                }
//            }
//            Collections.sort(listaProductos, new Comparator<ProductoComercio>() {
//                @Override
//                public int compare(ProductoComercio o1, ProductoComercio o2) {
//                    if(o1.getId_com().getId()==o2.getId_com().getId()){
//                        return 0;
//                    }else{
//                        return 1;
//                    }
//                }
//            });
//        }
//        else{
//            listaProductos.clear();
//            listaProductos.addAll(listaOriginal);
//        }
//        notifyDataSetChanged();
//    }
    public void filtrar(boolean precio, boolean comercio, boolean categoria,double min, double max, int id_com, int id_cat,String text)
    {

        boolean texto = !text.isEmpty();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            if(texto && precio && comercio && categoria){
                List<ProductoComercio> collection = listaOriginal.stream()
                        .filter(j -> j.getId_prod().getNombre().toLowerCase().contains(text.toLowerCase()))
                        .filter(i -> i.getImporte_total_descontado()>=min&&i.getImporte_total_descontado()<=max)
                        .filter(x -> x.getId_com().getId()==id_com)
                        .filter(y -> y.getId_prod().getCategoria().getId()==id_cat)
                        .collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collection);
            }else if(texto && precio && comercio){
                List<ProductoComercio> collection = listaOriginal.stream()
                        .filter(j -> j.getId_prod().getNombre().toLowerCase().contains(text.toLowerCase()))
                        .filter(i -> i.getImporte_total_descontado()>=min&&i.getImporte_total_descontado()<=max)
                        .filter(x -> x.getId_com().getId()==id_com)
                        .collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collection);
            }else if(texto && precio && categoria){
                List<ProductoComercio> collection = listaOriginal.stream()
                        .filter(j -> j.getId_prod().getNombre().toLowerCase().contains(text.toLowerCase()))
                        .filter(i -> i.getImporte_total_descontado()>=min&&i.getImporte_total_descontado()<=max)
                        .filter(y -> y.getId_prod().getCategoria().getId()==id_cat)
                        .collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collection);
            }else if(!texto && precio && categoria && comercio){
                List<ProductoComercio> collection = listaOriginal.stream()
                        .filter(i -> i.getImporte_total_descontado()>=min&&i.getImporte_total_descontado()<=max)
                        .filter(x -> x.getId_com().getId()==id_com)
                        .filter(y -> y.getId_prod().getCategoria().getId()==id_cat)
                        .collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collection);
            }else if(texto && precio){
                List<ProductoComercio> collection = listaOriginal.stream()
                        .filter(i -> i.getImporte_total_descontado()>=min&&i.getImporte_total_descontado()<=max)
                        .filter(j -> j.getId_prod().getNombre().toLowerCase().contains(text.toLowerCase()))
                        .collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collection);
            }else if(texto && comercio){
                List<ProductoComercio> collection = listaOriginal.stream()
                        .filter(x -> x.getId_com().getId()==id_com)
                        .filter(j -> j.getId_prod().getNombre().toLowerCase().contains(text.toLowerCase()))
                        .collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collection);
            }else if(texto && categoria){
                List<ProductoComercio> collection = listaOriginal.stream()
                        .filter(y -> y.getId_prod().getCategoria().getId()==id_cat)
                        .filter(j -> j.getId_prod().getNombre().toLowerCase().contains(text.toLowerCase()))
                        .collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collection);
            }else if(!texto && precio && comercio){
                List<ProductoComercio> collection = listaOriginal.stream()
                        .filter(x -> x.getId_com().getId()==id_com)
                        .filter(i -> i.getImporte_total_descontado()>=min&&i.getImporte_total_descontado()<=max)
                        .collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collection);
            }else if(!texto && precio && categoria){
                List<ProductoComercio> collection = listaOriginal.stream()
                        .filter(y -> y.getId_prod().getCategoria().getId()==id_cat)
                        .filter(i -> i.getImporte_total_descontado()>=min&&i.getImporte_total_descontado()<=max)
                        .collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collection);
            }else if(!texto && comercio && categoria){
                List<ProductoComercio> collection = listaOriginal.stream()
                        .filter(y -> y.getId_prod().getCategoria().getId()==id_cat)
                        .filter(x -> x.getId_com().getId()==id_com)
                        .collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collection);
            }else if(!texto && precio){
                List<ProductoComercio> collection = listaOriginal.stream()
                        .filter(i -> i.getImporte_total_descontado()>=min&&i.getImporte_total_descontado()<=max)
                        .collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collection);
            }else if (!texto && comercio) {
                List<ProductoComercio> collection = listaOriginal.stream()
                        .filter(x -> x.getId_com().getId()==id_com)
                        .collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collection);
            }else if (!texto && categoria) {
                List<ProductoComercio> collection = listaOriginal.stream()
                        .filter(y -> y.getId_prod().getCategoria().getId()==id_cat)
                        .collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collection);
            }else if(text.length()>0){
                List<ProductoComercio> collection = listaOriginal.stream()
                        .filter(j -> j.getId_prod().getNombre().toLowerCase().contains(text.toLowerCase()))
                        .collect(Collectors.toList());
                listaProductos.clear();
                listaProductos.addAll(collection);
            }else{
                listaProductos.clear();
                listaProductos.addAll(listaOriginal);
            }
        }
        notifyDataSetChanged();
    }
    public void ordenar(View v){
        if (v.getId()== R.id.ordenarPrecioBajoAlto){
            Collections.sort(listaProductos, new Comparator<ProductoComercio>() {
                @Override
                public int compare(ProductoComercio p1, ProductoComercio p2) {
                    if(p1.getImporte_total_descontado()>p2.getImporte_total_descontado()){
                        return 1;
                    }else if(p1.getImporte_total_descontado()==p2.getImporte_total_descontado()){
                        return 0;
                    }else{
                        return -1;
                    }
                }
            });
        }else if(v.getId()==R.id.ordenarPrecioAltoBajo) {
            Collections.sort(listaProductos, new Comparator<ProductoComercio>() {
                @Override
                public int compare(ProductoComercio p1, ProductoComercio p2) {
                    if (p1.getImporte_total_descontado() > p2.getImporte_total_descontado()) {
                        return -1;
                    } else if (p1.getImporte_total_descontado() == p2.getImporte_total_descontado()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
        }
    }
    public void borrarFiltros(){
        listaProductos.clear();
        listaProductos.addAll(listaOriginal);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listaProductos.size();
    }

    @Override
    public ProductoComercio getItem(int position) {
        return listaProductos.get(position);
    }
    //NO LO USAMOS
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewInflado = LayoutInflater.from(contexto).inflate(R.layout.layout_item_list_productos, null);
        //ENLAZAMOS LOS VIEWS PARA CAMBIAR SUS ATRIBUTOS
        ImageView imgP = viewInflado.findViewById(R.id.imgItem);
        TextView nombreP = viewInflado.findViewById(R.id.nombreProducto);
        TextView precioPDescunto = viewInflado.findViewById(R.id.precioProductoDescuento);
        TextView precioP = viewInflado.findViewById(R.id.precioProducto);
        TextView stockP = viewInflado.findViewById(R.id.stockProducto);
        TextView descuento = viewInflado.findViewById(R.id.descuentoProducto);
        TextView distribuidorP = viewInflado.findViewById(R.id.distribuidorProducto);
        //Creamos objeto CON ATRIBUTOS PERSONALIZADOS
        ProductoComercio producto = getItem(position);
        //LLenamos los views de la plantilla CON EL OBJETO CREADO QUE NOS DEVUELVE EL ARRAYLIST DE PRODUCTOS
        if (producto.getId_prod().getNombre().equals("Camiseta Nike")){
            imgP.setImageResource(R.drawable.camiseta1);
        } else if(producto.getId_prod().getNombre().equals("Camiseta Adidas")){
            imgP.setImageResource(R.drawable.camiseta2);
        }else if(producto.getId_prod().getNombre().equals("Camiseta Basica")){
            imgP.setImageResource(R.drawable.camiseta3);
        }else if(producto.getId_prod().getNombre().equals("Pantalon Vaquero")){
            imgP.setImageResource(R.drawable.pantalon1);
        }else if(producto.getId_prod().getNombre().equals("Pantalon Vaquero Gris")){
            imgP.setImageResource(R.drawable.pantalon2);
        }else if(producto.getId_prod().getNombre().equals("Pantalon Vaquero Straigth Figth")){
            imgP.setImageResource(R.drawable.pantalon3);
        }else if(producto.getId_prod().getNombre().equals("Chaqueta de Cuero")){
            imgP.setImageResource(R.drawable.chaqueta2);
        }else if(producto.getId_prod().getNombre().equals("Chaqueta de Cuero Negro")){
            imgP.setImageResource(R.drawable.chaqueta1);
        }else if(producto.getId_prod().getNombre().equals("Chaqueta Biker Efecto Piel")){
            imgP.setImageResource(R.drawable.chaqueta3);
        }else if(producto.getId_prod().getNombre().equals("Sudadera Basica")){
            imgP.setImageResource(R.drawable.sudadera1);
        }else if(producto.getId_prod().getNombre().equals("Sudadera Piolin")){
            imgP.setImageResource(R.drawable.sudadera2);
        }else if(producto.getId_prod().getNombre().equals("Sudadera Universidad")){
            imgP.setImageResource(R.drawable.sudadera3);
        }else{
            imgP.setImageResource(R.drawable.ic_menu_gallery);
        }
        //imgP.setImageResource();
        nombreP.setText(producto.getId_prod().getNombre());

        stockP.setText("Quedan "+producto.getCantidad()+" disponibles");
        if ((producto.getDescuento() > 0)) {
            descuento.setText("-" + producto.getDescuento() + "$");
            precioP.setPaintFlags(precioP.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            precioPDescunto.setText(producto.getImporte_total_descontado()+"$");
            precioP.setText(producto.getPrecio_total()+"$");
        } else {
            descuento.setText("");
            precioPDescunto.setText("");
            precioP.setTextSize(15);
            precioP.setText(producto.getPrecio_total()+"$");
        }
        distribuidorP.setText("Tienda: "+producto.getId_com().getNombre());
        return viewInflado;
    }
}
