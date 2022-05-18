package com.example.appshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.appShop.R;

import java.util.ArrayList;

public class carrito_compra extends AppCompatActivity {
    private static RecyclerView recyclerViewCarrito;
    public static ArrayList<ProductoCarrito> productosCarrito = new ArrayList<>();
    public static CarritoAdapter adapter = new CarritoAdapter(productosCarrito);
    public static TextView totalPrecioProductos, costesEnvio, iva, total;
    private static LinearLayout carritoVacio;
    private static ScrollView carritoConItems;
    private Button bVolverMainApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_compra);
        initComponentes();
        refresh();

    }
    void initComponentes(){
        recyclerViewCarrito = findViewById(R.id.recyclerViewCarrito);
        recyclerViewCarrito.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCarrito.setAdapter(adapter);
        carritoVacio = findViewById(R.id.carritoVacio);
        carritoConItems = findViewById(R.id.scrollView);
        bVolverMainApp = findViewById(R.id.bVolverMain);
        bVolverMainApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        totalPrecioProductos = findViewById(R.id.totalPIntCarrito);
        costesEnvio = findViewById(R.id.costesEnvioIntCarrito);
        iva = findViewById(R.id.ivaIntCarrito);
        total = findViewById(R.id.totalIntCarrito);
    }
    static void refresh(){
        double totalProductos = 0, totalA = 0, costes = 3, ivaA = 21;
        if(productosCarrito.size()>0){
            carritoConItems.setVisibility(View.VISIBLE);
            carritoVacio.setVisibility(View.GONE);
            for (ProductoCarrito p: productosCarrito){
                totalProductos+=p.getPrecio()*p.cantidad;
            }
            ivaA = totalProductos*(ivaA / 100);
            totalA = totalProductos + costes + ivaA;
        }else{
            carritoConItems.setVisibility(View.GONE);
            carritoVacio.setVisibility(View.VISIBLE);
        }

        totalA = totalProductos + costes + ivaA;
        totalPrecioProductos.setText(String.format("%.2f", totalProductos)+"$");
        costesEnvio.setText(String.format("%.2f", costes)+"$");
        iva.setText(String.format("%.2f", ivaA)+"$");
        total.setText(String.format("%.2f", totalA)+"$");
    }
}