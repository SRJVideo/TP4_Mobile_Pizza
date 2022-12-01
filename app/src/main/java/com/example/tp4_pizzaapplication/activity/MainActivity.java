package com.example.tp4_pizzaapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.tp4_pizzaapplication.R;
import com.example.tp4_pizzaapplication.databinding.ActivityMainBinding;
import com.example.tp4_pizzaapplication.databinding.ToolbarBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

;

/**
 * cette classe sera utilisée comme activité parente pour toutes les autres activités de l'application
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    DrawerLayout dLayout;
    Toolbar toolbarApp;

    Context context;
    Intent intent;
    SharedPreferences sharedPreferences;
    String SHARED_PREF_NAME ="user_pref";
    SharedPreferences.Editor sharedPrefEditor;
    protected String name, prenom, email,password,pays;


    protected boolean isLoggedIn(){
        return sharedPreferences.getBoolean("login",false);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }

    /***
     * Afficher les elements du volet de navigation
     */





    /**
     * Redirige vers login page
     */
    protected void logout(){
        sharedPrefEditor.putBoolean("login",false);
        sharedPrefEditor.apply();
        sharedPrefEditor.commit();
    }

    /**
     * permet la validation de l'email
     * @param email
     * @return true ou false si le courriel est valide
     */
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * permet d'initialisé les préférences
     */
    public void init() {
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        sharedPrefEditor = sharedPreferences.edit();
    }

    /**
     * Fait une action particulière si on clique si le menu
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

  
}