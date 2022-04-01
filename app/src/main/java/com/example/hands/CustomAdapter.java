package com.example.hands;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private final Context context;
    private  ArrayList id,nome,idade,temperatura,tosse,dor,pais,semanas,status;

    public CustomAdapter(Context context,  ArrayList id, ArrayList nome, ArrayList idade, ArrayList temperatura, ArrayList tosse, ArrayList dor, ArrayList pais, ArrayList semana, ArrayList status) {
        this.context = context;
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.temperatura = temperatura;
        this.tosse = tosse;
        this.dor = dor;
        this.pais = pais;
        this.semanas = semana;
        this.status = status;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.id_name.setText(String.valueOf(nome.get(position)));
        holder.id_idade.setText(String.valueOf(idade.get(position)));
        holder.id_temperatura.setText(String.valueOf(temperatura.get(position)));
        holder.id_tosse.setText(String.valueOf(tosse.get(position)));
        holder.id_dor.setText(String.valueOf(dor.get(position)));
        holder.id_pais.setText(String.valueOf(pais.get(position)));
        holder.id_semanas.setText(String.valueOf(semanas.get(position)));
        holder.id_status.setText(String.valueOf(status.get(position)));

        // ==========================Parte que editar a lista ==================//
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity4.class);
                intent.putExtra("id", String.valueOf(id.get(holder.getAdapterPosition())));
                intent.putExtra("nome",String.valueOf(nome.get(holder.getAdapterPosition())));
                intent.putExtra("idade",String.valueOf(idade.get(holder.getAdapterPosition())));
                intent.putExtra("temperatura",String.valueOf(temperatura.get(holder.getAdapterPosition())));
                intent.putExtra("tosse",String.valueOf(tosse.get(holder.getAdapterPosition())));
                intent.putExtra("dor",String.valueOf(dor.get(holder.getAdapterPosition())));
                intent.putExtra("pais",String.valueOf(pais.get(holder.getAdapterPosition())));
                intent.putExtra("semanas",String.valueOf(semanas.get(holder.getAdapterPosition())));
                intent.putExtra("status",String.valueOf(status.get(holder.getAdapterPosition())));
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_name,id_idade,id_temperatura ,id_tosse, id_dor, id_pais, id_semanas, id_status;
        ImageView mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_name = itemView.findViewById(R.id.id_nome);
            id_idade = itemView.findViewById(R.id.id_idade);
            id_temperatura = itemView.findViewById(R.id.id_temperatura);
            id_tosse = itemView.findViewById(R.id.id_tosse);
            id_dor = itemView.findViewById(R.id.id_dor);
            id_pais = itemView.findViewById(R.id.id_pais);
            id_semanas = itemView.findViewById(R.id.id_semanas);
            id_status = itemView.findViewById(R.id.id_status);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
