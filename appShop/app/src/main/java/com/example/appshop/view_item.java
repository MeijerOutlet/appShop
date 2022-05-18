package com.example.appshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appshop.db.DbValoraciones;
import com.example.appshop.entidades.ProductoComercio;
import com.example.appshop.entidades.Valoracion;
import com.example.appShop.R;

import java.util.ArrayList;

public class view_item extends AppCompatActivity {
    ImageView imgProducto;
    TextView nombreProducto, categoriaProducto, precioProducto, precioProductoDescuento, stockProducto, descuentoProducto, contadorProductos, comercio, descripcionProducto;
    Spinner spinnerTallas;
    Button buttonAdd, buttonRemove, botonAnhadirCarrito;
    RecyclerView recyclerValoraciones;
    AdapterValoracion adapterValoraciones;
    ArrayList<Valoracion> valoraciones;
    DbValoraciones dbValoraciones;
    int cantidadDisponibleTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        cantidadDisponibleTotal = getIntent().getIntExtra("stockProducto",0);
        inicializaciones();
        mostrarProductoSeleccionado();
    }
    @SuppressLint("NotifyDataSetChanged")
    void inicializaciones(){
        //Comentarios
        spinnerTallas = findViewById(R.id.tallas);
        recyclerValoraciones = findViewById(R.id.recyclerValoraciones);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerValoraciones.setLayoutManager(linearLayoutManager);
        valoraciones = new ArrayList<>();
        dbValoraciones = new DbValoraciones(this);
        int id_prod = getIntent().getIntExtra("id_prod",-1);
        valoraciones = dbValoraciones.mostrarValoracionesProducto(id_prod);
        adapterValoraciones = new AdapterValoracion(valoraciones);
        recyclerValoraciones.setAdapter(adapterValoraciones);
//        for (int i = 0; i < 10; i++) {
//            valoraciones.add(new Valoracion("pepe",1,1,3,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", new Date(22,2,22)));
//        }
        adapterValoraciones.notifyDataSetChanged();
        //Views generales
        imgProducto = findViewById(R.id.imgProductoView);
        nombreProducto = findViewById(R.id.nombreProductoView);
        categoriaProducto = findViewById(R.id.categoriaProductoView);
        precioProducto = findViewById(R.id.precioProductoView);
        precioProductoDescuento = findViewById(R.id.precioProductoDescuentoView);
        stockProducto = findViewById(R.id.stockProductoView);
        descuentoProducto = findViewById(R.id.descuentoProductoView);
        botonAnhadirCarrito = findViewById(R.id.botonAnhadirCarrito);
        buttonAdd = findViewById(R.id.buttonAddView);
        buttonRemove = findViewById(R.id.buttonRemoveView);
        contadorProductos = findViewById(R.id.contadorProductosView);
        contadorProductos.setFocusable(false);
        comercio = findViewById(R.id.comercioView);
        descripcionProducto = findViewById(R.id.descriptionView);
        //LISTENER
        botonAnhadirCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int indexProducto = getIntent().getIntExtra("indexProducto",-1);
                int cantidad = Integer.parseInt(contadorProductos.getText().toString());
                if(cantidad>0) {
                    ProductoComercio p = AppChollosMain.productos.get(indexProducto);
                    p.setCantidad(p.getCantidad()-cantidad);
                    AppChollosMain.productos.set(indexProducto,p);
                    AppChollosMain.adapter.notifyDataSetChanged();

                    ControladorProductosCompra.addProduct(new ProductoCarrito(nombreProducto.getText().toString(), getIntent().getDoubleExtra("precioProducto",-1), getIntent().getDoubleExtra("precioProductoDescuento", -1), Integer.parseInt(contadorProductos.getText().toString()), R.drawable.ic_menu_gallery, getIntent().getIntExtra("stockProducto",-1),getIntent().getIntExtra("comercioProducto",-1),getIntent().getIntExtra("id_prod",-1), getIntent().getStringExtra("comercioNombre")));
                    Toast.makeText(view_item.this, "Se ha añadido a tu carrito " + contadorProductos.getText().toString() + " " + nombreProducto.getText().toString(), Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(view_item.this, "Por favor, añade al menos 1 producto", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    void mostrarProductoSeleccionado(){
        Intent i = getIntent();
        //int drawable = i.getIntExtra("imgProducto",-1);
//        if (drawable!=-1){
//            imgProducto.setImageResource(drawable);
//        }
        String tallas = i.getStringExtra("tallaProducto");
        String[] tallasArray = tallas.split(",");
        spinnerTallas.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,tallasArray));
        //ArrayAdapter<CharSequence>adapter= ArrayAdapter.createFromResource(this, R.array.tallaLista, android.R.layout.simple_spinner_item);
        //adapter.addAll(tallasArray);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        nombreProducto.setText(i.getStringExtra("nombreProducto"));
        categoriaProducto.setText("#"+i.getStringExtra("categoriaProducto"));
        stockProducto.setText("Quedan " + cantidadDisponibleTotal + " disponibles");
        comercio.setText(i.getStringExtra("comercioNombre"));
        descripcionProducto.setText(i.getStringExtra(("descripcionProducto")));
        if(i.getDoubleExtra("descuentoProducto",0f) > 0){
            precioProducto.setPaintFlags(precioProducto.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            precioProductoDescuento.setText(i.getDoubleExtra("precioProductoDescuento",-1)+"$");
            precioProducto.setText(i.getDoubleExtra("precioProducto",-1)+"$");
            descuentoProducto.setText("-"+i.getDoubleExtra("descuentoProducto",0f)+"$");
        }else{
            precioProductoDescuento.setText("");
            precioProducto.setTextSize(25);
            precioProducto.setText(i.getDoubleExtra("precioProducto",-1)+"$");
            descuentoProducto.setText("");
        }





        if (nombreProducto.getText().toString().equals("Camiseta Nike")){
            imgProducto.setImageResource(R.drawable.camiseta1);
        } else if(nombreProducto.getText().toString().equals("Camiseta Adidas")){
            imgProducto.setImageResource(R.drawable.camiseta2);
        }else if(nombreProducto.getText().toString().equals("Camiseta Basica")){
            imgProducto.setImageResource(R.drawable.camiseta3);
        }else if(nombreProducto.getText().toString().equals("Pantalon Vaquero")){
            imgProducto.setImageResource(R.drawable.pantalon1);
        }else if(nombreProducto.getText().toString().equals("Pantalon Vaquero Gris")){
            imgProducto.setImageResource(R.drawable.pantalon2);
        }else if(nombreProducto.getText().toString().equals("Pantalon Vaquero Straigth Figth")){
            imgProducto.setImageResource(R.drawable.pantalon3);
        }else if(nombreProducto.getText().toString().equals("Chaqueta de Cuero")){
            imgProducto.setImageResource(R.drawable.chaqueta2);
        }else if(nombreProducto.getText().toString().equals("Chaqueta de Cuero Negro")){
            imgProducto.setImageResource(R.drawable.chaqueta1);
        }else if(nombreProducto.getText().toString().equals("Chaqueta Biker Efecto Piel")){
            imgProducto.setImageResource(R.drawable.chaqueta3);
        }else if(nombreProducto.getText().toString().equals("Sudadera Basica")){
            imgProducto.setImageResource(R.drawable.sudadera1);
        }else if(nombreProducto.getText().toString().equals("Sudadera Piolin")){
            imgProducto.setImageResource(R.drawable.sudadera2);
        }else if(nombreProducto.getText().toString().equals("Sudadera Universidad")){
            imgProducto.setImageResource(R.drawable.sudadera3);
        }else{
            imgProducto.setImageResource(R.drawable.ic_menu_gallery);
        }

    }
    public void onClickAddRemoveButtons(View v){
        int auxContador;
        if(contadorProductos.getText().toString().isEmpty()){
            auxContador = 0;
        }else{
            auxContador = Integer.parseInt(contadorProductos.getText().toString());
        }

        if (v.getId()==buttonAdd.getId()&&!(contadorProductos.getText().toString().contentEquals(String.valueOf(getIntent().getIntExtra("stockProducto",0))))){contadorProductos.setText(String.valueOf(++auxContador));stockProducto.setText("Quedan " + (--cantidadDisponibleTotal) + " disponibles");}
        else if(v.getId()==buttonRemove.getId()&&auxContador>0){contadorProductos.setText(String.valueOf(--auxContador)); stockProducto.setText("Quedan " + (++cantidadDisponibleTotal) + " disponibles");}
    }
}