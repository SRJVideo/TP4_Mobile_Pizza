package com.example.tp4_pizzaapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.tp4_pizzaapplication.R;
import com.example.tp4_pizzaapplication.databinding.ActivityAccueilBinding;

public class AccueilActivity extends MainActivity {

    public ActivityAccueilBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccueilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

    }
}