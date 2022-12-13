package com.example.utiletp4.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.utiletp4.R;
import com.example.utiletp4.database.DatabaseManager;
import com.example.utiletp4.modele.Pizza;

import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.Nullable;
import com.example.utiletp4.MainActivity;
import com.example.utiletp4.ui.adapter.PizzaListAdapter;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class PizzaFragment extends Fragment  {
    private MainActivity mainActivity;
    DatabaseManager dbm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pizza, container, false);
        // Instantiate object DB and View
        dbm = new DatabaseManager(getActivity());
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        afficherlesPizzas(view);
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
        ListView listPizzas = (ListView) v.findViewById(R.id.listViewPizzas);

        PizzaListAdapter adapter = new PizzaListAdapter(getActivity(), dbm.readPizza(),mainActivity.getSortes(),mainActivity.getTypes(), mainActivity.getImages());
        listPizzas.setAdapter(adapter);
    }


    //Mes liens d'aides (Sam)
    //https://www.youtube.com/watch?v=PoPJ-OoBONM
    //https://www.youtube.com/watch?v=_ad6HI94dFQ
}
