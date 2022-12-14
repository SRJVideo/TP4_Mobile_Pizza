package com.example.utiletp4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.utiletp4.modele.Commande;
import com.example.utiletp4.modele.Pizza;

import java.util.List;
import java.util.zip.Inflater;

public class listCommandeArrayAdapter extends ArrayAdapter<Pizza> {
    public static final String TAG = "listCommandeArrayAdapter";
    private Context mContext;
    int mResource;
    LayoutInflater inflater;
    List<Pizza> pizzaChoisie;
    List<Drawable> images;

    public listCommandeArrayAdapter(@NonNull Context context, int resource, @NonNull List<Pizza> pizzaChoisie, List<Drawable> images) {
        super(context, resource, pizzaChoisie);
        mContext = context;
        mResource = resource;
        this.pizzaChoisie = pizzaChoisie;
        this.images = images;
        this.inflater = LayoutInflater.from(context);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Convertir la vue de l'adapter
        if(convertView == null)
            convertView = inflater.inflate(mResource, parent,false);


        ImageView imgView = convertView.findViewById(R.id.imageViewPizzas);
        Button btIncremente = convertView.findViewById(R.id.incrementerNombrePizza);
        Button btDecrement = convertView.findViewById(R.id.decrementerNombrePizza);
        TextView textNombre = convertView.findViewById(R.id.nombrePizza);
        TextView textSorte = convertView.findViewById(R.id.sortePizza);
        TextView textPrix = convertView.findViewById(R.id.price);
        TextView textTotal = convertView.findViewById(R.id.total);

//pizzaChoisie.get(position)


        textSorte.setText(pizzaChoisie.get(position).getSortePizza().toUpperCase() +" - "+pizzaChoisie.get(position).getType().toUpperCase());
        textPrix.setText(String.valueOf( pizzaChoisie.get(position).getPrix()));
        textTotal.setText(textTotal.getText());
        imgView.setImageDrawable(images.get(position));
        btIncremente.setOnClickListener(v -> {
            int newvalue = Integer.parseInt(textNombre.getText().toString());
            textNombre.setText(""+(++newvalue)+"");
            double newtotal = Double.parseDouble(textPrix.getText().toString())*newvalue;
            textTotal.setText(""+String.format("%.2f",newtotal)+"");
        });
        btDecrement.setOnClickListener(v -> {
            int newvalue = Integer.parseInt(textNombre.getText().toString());

            if (newvalue > 1){
                double newtotal = (Double.parseDouble(textTotal.getText().toString())/newvalue);
                textNombre.setText(""+(--newvalue)+"");
                newtotal *= newvalue;
                textTotal.setText(""+String.format("%.2f",newtotal)+"");
            }



        });




        return convertView;
    }
}
