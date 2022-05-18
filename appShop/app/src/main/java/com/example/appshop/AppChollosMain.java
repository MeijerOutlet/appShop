package com.example.appshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.appshop.db.DbCategoria;
import com.example.appshop.db.DbComercio;
import com.example.appshop.db.DbProductoComercio;
import com.example.appshop.entidades.Categoria;
import com.example.appshop.entidades.Comercio;
import com.example.appshop.entidades.ProductoComercio;
import com.example.appShop.R;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

import java.util.ArrayList;

public class AppChollosMain extends AppCompatActivity implements SearchView.OnQueryTextListener{
    GridView gridView;
    public static ArrayList<ProductoComercio> productos;
    public static ProductosAdapter adapter;
    Button bOrdenPrecioAB, bOrdenPrecioBA;
    ImageButton filtro, orden;
    TextView precioFiltroTV, distribuidorFiltroTV, categoriaFiltroTV, buttonCloseNavBar;
    ImageView applicarFiltro, borrarFiltro;
    NavigationView navBar;
    RangeSeekBar<Integer> precioSeekbar;
    SearchView buscador;
    LinearLayout containerDistribuidoresCheckBoxes, containerOrdenarProductos;
    FloatingActionButton buttonRegressToFirstProduct, botonCarrito;
    ConstraintLayout backPanelNav;
    ArrayList<Comercio> comercios;
    ArrayList<Categoria> categorias;
    ArrayList<RadioButton> comerciosContainer, categoriasContainer;
    DbComercio dbComercio;
    DbCategoria dbCategoria;
    private DbProductoComercio dbProductoComercio;
    RadioGroup rgDis;
    RadioGroup rgCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_chollos_main);

        inicializaciones();
    }
    void inicializaciones(){
        gridView = findViewById(R.id.GridViewProductos);
        productos = new ArrayList<>();
        dbProductoComercio = new DbProductoComercio(this);
        productos.addAll(dbProductoComercio.mostrarProductoComercio());
        //ESTO TIENE QUE ESTAR DEBAJO DE LOS .ADD() A productos, SI NO FALLA LA BUSQUEDA DEL SEARCHVIEW
        adapter = new ProductosAdapter(productos, this);
        gridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        {
            filtro = findViewById(R.id.botonFiltro);
            orden = findViewById(R.id.botonOrden);
            navBar = findViewById(R.id.nav_bar);
            navBar.setVisibility(View.GONE);
            navBar.setTranslationX(-(navBar.getWidth()));
            buttonCloseNavBar = findViewById(R.id.closeNavBarButton);
            applicarFiltro = findViewById(R.id.navApplyButton);
            borrarFiltro = findViewById(R.id.navDeleteButton);
            precioFiltroTV = findViewById(R.id.navPrecioText);
            distribuidorFiltroTV = findViewById(R.id.navDistribuidorText);
            categoriaFiltroTV = findViewById(R.id.navCategoriaText);
            precioSeekbar = findViewById(R.id.navRangeSeekbar);
            precioSeekbar.setRangeValues(0, 400);
            precioSeekbar.setNotifyWhileDragging(true);
            containerDistribuidoresCheckBoxes = findViewById(R.id.navContainerCheckBoxs);
            dbComercio = new DbComercio(this);
            dbCategoria = new DbCategoria(this);
            comercios = dbComercio.mostrarComercios();
            categorias = dbCategoria.mostrarCategorias();
            comerciosContainer = new ArrayList<>();
            rgDis = findViewById(R.id.radioGroup);
            rgCat = findViewById(R.id.radioGroupCat);
            for (int i = 0; i < comercios.size(); i++) {
                RadioButton cb = new RadioButton(this);
                cb.setText(comercios.get(i).getNombre());
                comerciosContainer.add(cb);
                rgDis.addView(cb);
            }
            categoriasContainer = new ArrayList<>();
            for (int i = 0; i < categorias.size(); i++) {
                RadioButton cbCat = new RadioButton(this);
                cbCat.setText(categorias.get(i).getDescripcion());
                categoriasContainer.add(cbCat);
                rgCat.addView(cbCat);
            }


            containerOrdenarProductos = findViewById(R.id.linearOrdenar);
            bOrdenPrecioAB = findViewById(R.id.ordenarPrecioAltoBajo);
            bOrdenPrecioBA = findViewById(R.id.ordenarPrecioBajoAlto);
            buttonRegressToFirstProduct = findViewById(R.id.floatingButton);
            botonCarrito = findViewById(R.id.carritoButton);
            backPanelNav = findViewById(R.id.backPanelNav);
            backPanelNav.setVisibility(View.GONE);
            buscador = findViewById(R.id.buscadorAppProductos);
        }
        //--------------------LISTENERS------------------------------------
        buscador.setOnQueryTextListener(this);

        backPanelNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeNavBar();
                backPanelNav.setVisibility(View.GONE);
            }
        });
        botonCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AppChollosMain.this, carrito_compra.class);
                startActivity(i);
            }
        });
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem>=5)buttonRegressToFirstProduct.setVisibility(View.VISIBLE);
                else buttonRegressToFirstProduct.setVisibility(View.GONE);
            }
        });
        buttonRegressToFirstProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridView.smoothScrollToPosition(View.SCROLLBAR_POSITION_DEFAULT);
            }
        });
        buttonCloseNavBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeNavBar();
            }
        });
        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ABRIENDO NAVBAR DE LOS FILTROS
                navBar.clearAnimation();
                TranslateAnimation animation = new TranslateAnimation(-(navBar.getWidth()),0,0,0);
                animation.setDuration(250);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        navBar.setVisibility(View.VISIBLE);
                        backPanelNav.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        applicarFiltro.setClickable(true);
                        buttonCloseNavBar.setClickable(true);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                navBar.setAnimation(animation);
            }
        });
        borrarFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.borrarFiltros();
                clearFilters();
            }
        });
        applicarFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtrarComoPutoCrack();
                closeNavBar();
            }
        });
        precioFiltroTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAndHide(precioFiltroTV, precioSeekbar);
            }
        });
        distribuidorFiltroTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAndHide(distribuidorFiltroTV, rgDis);
            }
        });

        categoriaFiltroTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAndHide(categoriaFiltroTV, rgCat);
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(AppChollosMain.this, view_item.class);
                i.putExtra("id_prod",productos.get(position).getId_prod().getId());
                i.putExtra("indexProducto",position);
                //i.putExtra("imgProducto", );
                i.putExtra("nombreProducto", productos.get(position).getId_prod().getNombre());
                i.putExtra("precioProducto", productos.get(position).getPrecio_total());
                i.putExtra("precioProductoDescuento",productos.get(position).getImporte_total_descontado());
                i.putExtra("descuentoProducto", productos.get(position).getDescuento());
                i.putExtra("stockProducto", productos.get(position).getCantidad());
                i.putExtra("comercioProducto", productos.get(position).getId_com().getId());
                i.putExtra("comercioNombre", productos.get(position).getId_com().getNombre());
                i.putExtra("descripcionProducto", productos.get(position).getId_prod().getDescripcion());
                i.putExtra("tallaProducto", productos.get(position).getId_prod().getTalla());
                i.putExtra("categoriaProducto", productos.get(position).getId_prod().getCategoria().getDescripcion());
                startActivity(i);
            }
        });
        orden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(containerOrdenarProductos.getVisibility() == View.GONE){containerOrdenarProductos.setVisibility(View.VISIBLE);}
                else {containerOrdenarProductos.setVisibility(View.GONE);}
            }
        });
    }
    public void displayAndHide(TextView header, View content){
        Drawable downDrawable = getResources().getDrawable(R.drawable.ic_baseline_arrow_drop_down_24);
        Drawable upDrawable = getResources().getDrawable(R.drawable.ic_baseline_arrow_drop_up_24);
        if(content.getVisibility()==View.VISIBLE){
            header.setCompoundDrawablesWithIntrinsicBounds(null,null,upDrawable,null);
            content.setVisibility(View.GONE);
        }else{
            header.setCompoundDrawablesWithIntrinsicBounds(null,null,downDrawable,null);
            content.setVisibility(View.VISIBLE);
        }
    }
    public void onClickOrdenarButtons(View v){
        adapter.ordenar(v);
        containerOrdenarProductos.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
    }
    private void clearFilters(){
        precioSeekbar.resetSelectedValues();
        rgDis.clearCheck();
        rgCat.clearCheck();
        buscador.setQuery("",false);
    }
    private void closeNavBar(){
        //                navBar.setVisibility(View.GONE);
        navBar.clearAnimation();
        TranslateAnimation animation = new TranslateAnimation(navBar.getX(),navBar.getX()-navBar.getWidth(),0,0);
        animation.setDuration(200);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                applicarFiltro.setClickable(false);
                buttonCloseNavBar.setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                navBar.setVisibility(View.GONE);
                backPanelNav.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        navBar.setAnimation(animation);
    }

    public void filtrarComoPutoCrack(){
        double min = precioSeekbar.getAbsoluteMinValue().doubleValue(), minSelected = precioSeekbar.getSelectedMinValue().doubleValue(), maxSelected = precioSeekbar.getSelectedMaxValue().doubleValue(), max = precioSeekbar.getAbsoluteMaxValue().doubleValue();
        boolean checked = false, precio = false, checkedCat = false;
        RadioButton CBaux = null;
        RadioButton CBauxCat = null;
        for (RadioButton cb : comerciosContainer) {
            if(cb.isChecked()){
                CBaux = cb;
                checked = true;
                break;
            }
        }

        for (RadioButton cbCat : categoriasContainer) {
            if(cbCat.isChecked()){
                CBauxCat = cbCat;
                checkedCat = true;
                break;
            }
        }
        if((minSelected!=min || maxSelected!=max)){
            precio= true;
        }
        if(checked && checkedCat){
            adapter.filtrar(precio,checked, checkedCat,minSelected, maxSelected,comercios.get(comerciosContainer.indexOf(CBaux)).getId(),categorias.get(categoriasContainer.indexOf(CBauxCat)).getId(),buscador.getQuery().toString());
        }else if(checked){
            adapter.filtrar(precio,checked, checkedCat,minSelected, maxSelected,comercios.get(comerciosContainer.indexOf(CBaux)).getId(),-1,buscador.getQuery().toString());
        }else if(checkedCat){
            adapter.filtrar(precio,checked,checkedCat, minSelected, maxSelected,-1,categorias.get(categoriasContainer.indexOf(CBauxCat)).getId(),buscador.getQuery().toString());
        }
        else {
            adapter.filtrar(precio,checked,checkedCat, minSelected, maxSelected,-1,-1,buscador.getQuery().toString());
        }

    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        filtrarComoPutoCrack();
        return false;
    }
}