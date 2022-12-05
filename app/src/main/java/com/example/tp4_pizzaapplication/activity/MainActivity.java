package com.example.tp4_pizzaapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tp4_pizzaapplication.R;
import com.example.tp4_pizzaapplication.databinding.ActivityAccueilBinding;
import com.example.tp4_pizzaapplication.databinding.ActivityMainBinding;
import com.example.tp4_pizzaapplication.databinding.ToolbarBinding;
import com.example.tp4_pizzaapplication.fragment.AccueilFragment;
import com.example.tp4_pizzaapplication.fragment.CommandeFragment;
import com.example.tp4_pizzaapplication.fragment.PizzaFragment;
import com.example.tp4_pizzaapplication.fragment.PointsFragment;
import com.example.tp4_pizzaapplication.fragment.ProfilFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

;

/**
 * cette classe sera utilisée comme activité parente pour toutes les autres activités de l'application
 */
public class MainActivity extends AppCompatActivity {
    DrawerLayout dLayout;
    Toolbar toolbarApp;
    Context context;
    Intent intent;
    SharedPreferences sharedPreferences;
    String SHARED_PREF_NAME ="user_pref";
    SharedPreferences.Editor sharedPrefEditor;
    protected String name, prenom, email,password,pays;
    Button connexion, inscription;

    protected boolean isLoggedIn(){
        return sharedPreferences.getBoolean("login",false);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        connexion = findViewById(R.id.btnConnexion);
        inscription = findViewById(R.id.btnInscription);
        connexion.setOnClickListener(e->{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        inscription.setOnClickListener(i->{
            Intent intent1 = new Intent(this, SignupActivity.class);
            startActivity(intent1);
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dLayout.openDrawer(Gravity.LEFT);
            }
        });

        setNavigationDrawer();

    }



    /***
     * Afficher les elements du volet de navigation
     */

    private void setNavigationDrawer() {
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Fragment frag = null;
                int itemId = menuItem.getItemId();
                if (itemId == R.id.accueil) {
                    frag = new AccueilFragment();
                } else if (itemId == R.id.pizza) {
                    frag = new PizzaFragment();
                }else if (itemId == R.id.commande) {
                    frag = new CommandeFragment();
                }else if (itemId == R.id.profil) {
                    frag = new ProfilFragment();
                }else if (itemId == R.id.points) {
                    frag = new PointsFragment();
                }
                Toast.makeText(getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                if (frag != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame, frag);
                    transaction.commit();
                    dLayout.closeDrawers();
                    return true;
                }
                return false;
            }
        });
    }





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
        toolbarApp = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarApp);
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