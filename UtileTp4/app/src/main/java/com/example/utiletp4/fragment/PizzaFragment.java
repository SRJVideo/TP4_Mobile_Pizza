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
    DatabaseManager databaseManager;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pizza, container, false);
        databaseManager = new DatabaseManager(this.getContext());
        AjoutPizzaBD();

        return root;
    }

    void AjoutPizzaBD(){
        //Sorte, bacon, cheese, pepperoni, garnie
        Pizza pizza1 = new Pizza("Bacon", "petite", 6d);
        Pizza pizza2 = new Pizza("Bacon", "moyenne", 10d);
        Pizza pizza3 = new Pizza("Bacon", "grande", 12d);
        Pizza pizza4 = new Pizza("Cheese", "petite", 8d);
        Pizza pizza5 = new Pizza("Cheese", "moyenne", 11.5d);
        Pizza pizza6 = new Pizza("Cheese", "grande", 14d);
        Pizza pizza7 = new Pizza("Pepperoni", "petite", 10d);
        Pizza pizza8 = new Pizza("Pepperoni", "moyenne", 13d);
        Pizza pizza9 = new Pizza("Pepperoni", "grande", 16.5d);
        Pizza pizza10 = new Pizza("Garnie", "petite", 14d);
        Pizza pizza11 = new Pizza("Garnie", "moyenne", 18d);
        Pizza pizza12 = new Pizza("Garnie", "grande", 22d);

        if (databaseManager.readPizza().isEmpty()){
            databaseManager.insertPizza(pizza1);
            databaseManager.insertPizza(pizza2);
            databaseManager.insertPizza(pizza3);
            databaseManager.insertPizza(pizza4);
            databaseManager.insertPizza(pizza5);
            databaseManager.insertPizza(pizza6);
            databaseManager.insertPizza(pizza7);
            databaseManager.insertPizza(pizza8);
            databaseManager.insertPizza(pizza9);
            databaseManager.insertPizza(pizza10);
            databaseManager.insertPizza(pizza11);
            databaseManager.insertPizza(pizza12);
        }

        System.out.println(databaseManager.readPizza());


    }

    private MainActivity mainActivity;
    DatabaseManager dbm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pizza, container, false);
        // Instantiate object DB and View
        dbm = new DatabaseManager(getActivity());
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
