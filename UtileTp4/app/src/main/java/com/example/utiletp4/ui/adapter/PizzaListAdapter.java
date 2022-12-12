package com.example.utiletp4.ui.adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.utiletp4.R;
import com.example.utiletp4.modele.Pizza;

import java.util.List;

public class PizzaListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<Pizza> readPizza;

    public PizzaListAdapter(Context context, List<Pizza> readPizza) {
        this.context = context;
        this.readPizza = readPizza;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return readPizza.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Convertir la vue de l'adapter
        if(convertView == null)
        convertView = inflater.inflate(R.layout.pizza_list_adapter, parent,false);


        ImageView imgView = convertView.findViewById(R.id.imageViewPizzas);

        TextView textSorte = convertView.findViewById(R.id.textViewSortePizza);
        textSorte.setText(readPizza.get(position).getSortePizza());
        TextView textType = convertView.findViewById(R.id.textViewTypePizza);
        textType.setText( readPizza.get(position).getType());
        TextView textPrix = convertView.findViewById(R.id.textViewPrixPizza);
        textPrix.setText( String.valueOf(readPizza.get(position).getPrix()));
        Spinner spinner = convertView.findViewById(R.id.spinnerPizza);
        Button bouton = convertView.findViewById(R.id.buttonAjouter);
        bouton.setOnClickListener(v -> {

        });

        return convertView;
    }
}