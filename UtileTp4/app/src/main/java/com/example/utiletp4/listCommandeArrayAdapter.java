package com.example.utiletp4;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.utiletp4.modele.Commande;

import java.util.List;

public class listCommandeArrayAdapter extends ArrayAdapter<Commande> {
    public static final String TAG = "listCommandeArrayAdapter";
    private Context mContext;
    int mResource;
    public listCommandeArrayAdapter(@NonNull Context context, int resource, @NonNull List<Commande> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
