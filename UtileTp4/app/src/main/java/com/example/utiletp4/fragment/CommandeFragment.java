package com.example.utiletp4.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.utiletp4.MainActivity;
import com.example.utiletp4.R;
import com.example.utiletp4.listCommandeArrayAdapter;

public class CommandeFragment extends Fragment {

    MainActivity mainActivity;
    ListView listView;


    Double totalCommande;
    TextView textTotalCommande;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_commande, container, false);

        System.out.println("taille de liste pizza : "+mainActivity.getPizzaChoisie().size());
        return root;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listpizza);
        textTotalCommande = view.findViewById(R.id.totalCommandeTextView);

        listCommandeArrayAdapter adapter = new listCommandeArrayAdapter(getContext(),R.layout.pizza_list_commande_adapter, mainActivity.getPizzaChoisie(), mainActivity.getDrawPizzaChoisie());
        listView.setAdapter(adapter);
        adapter.changerTextPrixTotal(textTotalCommande);


      //  textTotalCommande.setText(String.valueOf(totalCommande));


    }

    // Called when a fragment is first attached to its context.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }

    private void checkgahngement(String s){
        textTotalCommande.setText(s);
    }


    public Double getTotalCommande() {
        return totalCommande;
    }

    public void setTotalCommande(Double totalCommande) {
        this.totalCommande = totalCommande;
    }
}
