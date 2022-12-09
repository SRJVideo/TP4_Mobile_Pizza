package com.example.utiletp4.fragment;

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
}
