package com.example.utiletp4.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.utiletp4.MainActivity;
import com.example.utiletp4.R;
import com.example.utiletp4.listCommandeArrayAdapter;

public class CommandeFragment extends Fragment {

    MainActivity mainActivity;
    ListView listView;

    listCommandeArrayAdapter adapter;
    TextView textTotalCommande;
    TextView textEconomie;
    TextView textAvecPoints;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_commande, container, false);
        adapter = new listCommandeArrayAdapter(getContext(),R.layout.pizza_list_commande_adapter, mainActivity.getPizzaChoisie(), mainActivity.getDrawPizzaChoisie(),this);

        System.out.println("taille de liste pizza : "+mainActivity.getPizzaChoisie().size());
        return root;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listpizza);
        textTotalCommande = view.findViewById(R.id.totalCommandeTextView);
        textEconomie = view.findViewById(R.id.textViewEconomie);
        textAvecPoints = view.findViewById(R.id.textViewTotalAvecPoints);
        listView.setAdapter(adapter);


        Button btAddToCart = view.findViewById(R.id.button);
        btAddToCart.setOnClickListener( new AfficherDialogue());
    }

    // Called when a fragment is first attached to its context.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }

    public TextView getTextTotalCommande() {
        return textTotalCommande;
    }


    private class AfficherDialogue implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            AlertDialog dialog = new AlertDialog.Builder(getContext()).create();

            dialog.setTitle("ADD TO CART");
            dialog.setMessage("Passez à la caisse pour paiement par Carte ?");
            dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OUI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    PointsFragment points = new PointsFragment();
                }
            });
            dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "NON", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            dialog.show();
        }
    }
}
