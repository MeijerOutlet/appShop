package com.example.appshop;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appShop.R;

import java.util.ArrayList;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.HolderProductos> {
    ArrayList<ProductoCarrito> productos;

    public CarritoAdapter(ArrayList<ProductoCarrito> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public HolderProductos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrito,null,false);
        return new HolderProductos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderProductos holderProductos, @SuppressLint("RecyclerView") int position) {
        holderProductos.nombreP.setText(productos.get(position).getNombre());
        holderProductos.precioP.setText(String.valueOf(productos.get(position).getPrecio())+"$");
        holderProductos.precioTotalP.setText(String.valueOf(productos.get(position).getPrecio()*productos.get(position).getCantidad())+"$");
        holderProductos.cantidad.setText(String.valueOf(productos.get(position).getCantidad()));
        holderProductos.nombreComercio.setText(productos.get(position).getComercio_Nombre());
        holderProductos.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cantidad = Integer.parseInt(holderProductos.cantidad.getText().toString());
                if(cantidad<productos.get(position).stock)ControladorProductosCompra.addItem(productos,position);
            }
        });
        holderProductos.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cantidad = Integer.parseInt(holderProductos.cantidad.getText().toString());
                if(cantidad>1)ControladorProductosCompra.removeItem(productos,position);
                else ControladorProductosCompra.removeProduct(productos.get(position));

            }
        });
        holderProductos.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ControladorProductosCompra.removeProduct(productos.get(position));
            }
        });
        if (holderProductos.nombreP.getText().toString().equals("Camiseta Nike")){
            holderProductos.imgProducto.setImageResource(R.drawable.camiseta1);
        } else if(holderProductos.nombreP.getText().toString().equals("Camiseta Adidas")){
            holderProductos.imgProducto.setImageResource(R.drawable.camiseta2);
        }else if(holderProductos.nombreP.getText().toString().equals("Camiseta Basica")){
            holderProductos.imgProducto.setImageResource(R.drawable.camiseta3);
        }else if(holderProductos.nombreP.getText().toString().equals("Pantalon Vaquero")){
            holderProductos.imgProducto.setImageResource(R.drawable.pantalon1);
        }else if(holderProductos.nombreP.getText().toString().equals("Pantalon Vaquero Gris")){
            holderProductos.imgProducto.setImageResource(R.drawable.pantalon2);
        }else if(holderProductos.nombreP.getText().toString().equals("Pantalon Vaquero Straigth Figth")){
            holderProductos.imgProducto.setImageResource(R.drawable.pantalon3);
        }else if(holderProductos.nombreP.getText().toString().equals("Chaqueta de Cuero")){
            holderProductos.imgProducto.setImageResource(R.drawable.chaqueta2);
        }else if(holderProductos.nombreP.getText().toString().equals("Chaqueta de Cuero Negro")){
            holderProductos.imgProducto.setImageResource(R.drawable.chaqueta1);
        }else if(holderProductos.nombreP.getText().toString().equals("Chaqueta Biker Efecto Piel")){
            holderProductos.imgProducto.setImageResource(R.drawable.chaqueta3);
        }else if(holderProductos.nombreP.getText().toString().equals("Sudadera Basica")){
            holderProductos.imgProducto.setImageResource(R.drawable.sudadera1);
        }else if(holderProductos.nombreP.getText().toString().equals("Sudadera Piolin")){
            holderProductos.imgProducto.setImageResource(R.drawable.sudadera2);
        }else if(holderProductos.nombreP.getText().toString().equals("Sudadera Universidad")){
            holderProductos.imgProducto.setImageResource(R.drawable.sudadera3);
        }else{
            holderProductos.imgProducto.setImageResource(R.drawable.ic_menu_gallery);
        }
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class HolderProductos extends RecyclerView.ViewHolder {
        ImageView imgProducto, delete;
        TextView nombreP, precioP, precioTotalP, cantidad, nombreComercio;
        Button add, remove;
        public HolderProductos(@NonNull View itemView) {
            super(itemView);
            imgProducto = itemView.findViewById(R.id.imgRecyclerCart);
            nombreComercio = itemView.findViewById(R.id.pNameComercio);
            nombreP = itemView.findViewById(R.id.pNameRecyclerCart);
            precioP = itemView.findViewById(R.id.precioItemRecyclerCart);
            precioTotalP = itemView.findViewById(R.id.precioItemSrecyclerCart);
            cantidad = itemView.findViewById(R.id.countRecyclerCart);
            add = itemView.findViewById(R.id.bAddRecyclerCart);
            remove = itemView.findViewById(R.id.bRemoveRecyclerCart);
            delete = itemView.findViewById(R.id.bDeleteItemCarrito);
        }
    }
}
