package com.example.utiletp4.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    String[] sortes;
    String[] types;
    ImageView imgView;
    private TextView textSorte;
    int pizzaImg[] = {R.drawable.fromage, R.drawable.pepperoni, R.drawable.bacon, R.drawable.garnie, R.drawable.tomates, R.drawable.vegetarienne,R.drawable.royale};

    public PizzaListAdapter(Context context, List<Pizza> readPizza, String[] sortes, String[] types) {
        this.context = context;
        this.readPizza = readPizza;
        this.sortes = sortes;
        this.types = types;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return sortes.length;
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

            imgView = convertView.findViewById(R.id.imageViewPizzas);
            textSorte = convertView.findViewById(R.id.textViewSortePizza);
            TextView textType = convertView.findViewById(R.id.textViewTypePizza);
            TextView textPrix = convertView.findViewById(R.id.textViewPrixPizza);
            Spinner spinner = convertView.findViewById(R.id.spinnerPizza);
            Button bouton = convertView.findViewById(R.id.buttonAjouter);

            //1 - Insertion de sorte par position
            textSorte.setText(sortes[position]);
            //2 - Spinner (c'est presque ça)
             ArrayAdapter ad = new ArrayAdapter(context,android.R.layout.simple_spinner_item,types);
             spinner.setAdapter(ad);
            // 3 - Adapter les types et les prix au choix d'element du Spinner
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                textType.setText(parent.getSelectedItem().toString());
                textPrix.setText(parent.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mettreImageViewPizza();

//        bouton.setOnClickListener(v -> {
//
//        });

        return convertView;
    }

    void mettreImageViewPizza(){
        for (int i=0; i<sortes.length; i++){
            if (textSorte.getText().equals(sortes[i])){
                imgView.setImageResource(pizzaImg[i]);
            }
        }
    }
}