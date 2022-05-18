package com.example.appshop;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appshop.entidades.Valoracion;
import com.example.appShop.R;

import java.util.ArrayList;

public class AdapterValoracion extends RecyclerView.Adapter<AdapterValoracion.HolderValoracion>{
    ArrayList<Valoracion> valoraciones;

    public AdapterValoracion(ArrayList<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    @NonNull
    @Override
    public HolderValoracion onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.valoracion_producto_layout,null,false);
        return new HolderValoracion(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderValoracion holderValoraciones, int pos) {
        holderValoraciones.nickname.setText(valoraciones.get(pos).getNick().getNick());
        //holderValoraciones.asunto.setText(valoraciones.get(pos).);
        holderValoraciones.descripcion.setText(valoraciones.get(pos).getComentario());
        holderValoraciones.valoracionInt.setText(String.valueOf(valoraciones.get(pos).getValoracion()));
        holderValoraciones.date.setText(valoraciones.get(pos).getFecha().toString());
    }

    @Override
    public int getItemCount() {
        return valoraciones.size();
    }

    public class HolderValoracion extends RecyclerView.ViewHolder {
        TextView nickname, asunto, descripcion, valoracionInt, date;
        public HolderValoracion(@NonNull View itemView) {
            super(itemView);
            nickname = itemView.findViewById(R.id.nickNameValoracionLayout);
            //asunto = itemView.findViewById(R.id.asuntoValoracionLayout);
            descripcion = itemView.findViewById(R.id.descripcionValoracionLayout);
            valoracionInt = itemView.findViewById(R.id.valoracionIntLayout);
            date = itemView.findViewById(R.id.dateValoracionLayout);

        }
    }
}
