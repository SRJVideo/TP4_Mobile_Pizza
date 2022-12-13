package com.example.utiletp4.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
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
import android.widget.Toast;

import com.example.utiletp4.R;
import com.example.utiletp4.modele.Pizza;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


public class PizzaListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<Pizza> readPizza;
    String[] sortes;
    String[] types;
    int[] images;



    public PizzaListAdapter(Context context, List<Pizza> readPizza, String[] sortes, String[] types, int[] images) {
        this.context = context;
        this.readPizza = readPizza;
        this.sortes = sortes;
        this.types = types;
        this.images = images;
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

            ImageView imgView = convertView.findViewById(R.id.imageViewPizzas);
            TextView textSorte = convertView.findViewById(R.id.textViewSortePizza);
            TextView textType = convertView.findViewById(R.id.textViewTypePizza);
            TextView textPrix = convertView.findViewById(R.id.textViewPrixPizza);
            Spinner spinner = convertView.findViewById(R.id.spinnerPizza);
            Button bouton = convertView.findViewById(R.id.buttonAjouter);

            //1 - Insertion de sorte par position
            textSorte.setText(sortes[position]);

            //2 - Spinner (c'est presque ça)
             ArrayAdapter ad = new ArrayAdapter(context,android.R.layout.simple_spinner_item,types);
             spinner.setAdapter(ad);

            //3 - Prendre les prix
            List<Double> Assortieprix = new ArrayList<>();
            List<Pizza>assortiePizzas= readPizza.stream().filter(pizza -> Objects.equals(pizza.getSortePizza(), sortes[position])).collect(Collectors.toList());
            assortiePizzas.forEach(pizza -> Assortieprix.add(pizza.getPrix()));

            //4 - Adapter les types et les prix au choix d'element du Spinner
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textType.setText(parent.getSelectedItem().toString());
                Log.i("Test " , Arrays.toString(readPizza.toArray()));
                textPrix.setText(Assortieprix.get(parent.getSelectedItemPosition()) + "$ ");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
             });

            //5 - Images
            imgView.setImageResource(images[position]);
            imgView.setTag("Pizza " + sortes[position]);

            //6 - Bouton programmable pour récupérer les données
            bouton.setOnClickListener(view -> {
                String sorte = (String) textSorte.getText();
                String type = (String) textType.getText();
                Double prix = Double.parseDouble((String) textPrix.getText().subSequence(0,textPrix.length()-2))   ;

               Optional<Pizza> choisie = readPizza.stream().filter(pizza ->
                       pizza.getPrix() == prix &&
                               Objects.equals(pizza.getSortePizza(), sorte) &&
                               Objects.equals(pizza.getType(), type)
               ).findFirst();
                Toast.makeText(context.getApplicationContext(), "J'ai choisi --> "+ choisie+". Miam miam!",Toast.LENGTH_SHORT).show();
            });





        return convertView;
    }


}