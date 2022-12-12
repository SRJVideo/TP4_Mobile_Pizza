package com.example.utiletp4.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.utiletp4.R;
import com.example.utiletp4.database.DatabaseManager;
import com.example.utiletp4.modele.Pizza;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.utiletp4.MainActivity;
import com.example.utiletp4.R;
import com.example.utiletp4.database.DatabaseManager;
import com.example.utiletp4.modele.Pizza;
import com.example.utiletp4.ui.adapter.PizzaListAdapter;

import java.util.List;

public class PizzaFragment extends Fragment {
    private MainActivity mainActivity;
    DatabaseManager dbm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pizza, container, false);
        // Instantiate object DB and View
        dbm = new DatabaseManager(getActivity());
        if (dbm.readPizza() != null) AjoutPizzaBD();
        afficherlesPizzas(root);
        return root;
    }

    // Called when a fragment is first attached to its context.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }

    void AjoutPizzaBD(){
        String[] sortes = {"Fromage","Péppéroni","Bacon","Garnie","Tomates","Végétarienne","Royale"};
        String[] types = {"Petite","Moyenne","Grande"};

        for (int i=0 ; i < sortes.length ; i++) {
            double prix = (Math.random()*10);

            for (int j=0 ; i <types.length; i++ ) {
                Pizza pizza = new Pizza(sortes[i], types[j], prix+j);

                dbm.insertPizza(pizza);
            }
        }
    }



    /**
     * afficher les pizzas listPizzas
     */
    private void afficherlesPizzas(View v){
        ListView listPizzas = v.findViewById(R.id.listViewPizzas);

        PizzaListAdapter adapter = new PizzaListAdapter(v.getContext(), dbm.readPizza());
        listPizzas.setAdapter(adapter);
    }
    //Mes liens d'aides (Sam)
    //https://www.youtube.com/watch?v=PoPJ-OoBONM
    //https://www.youtube.com/watch?v=_ad6HI94dFQ
}
