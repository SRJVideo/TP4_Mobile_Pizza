package com.example.utiletp4;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.utiletp4.fragment.PizzaFragment;
import com.example.utiletp4.modele.User;
import com.example.utiletp4.ui.InscriptionFragment;
import com.example.utiletp4.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private User currentUser = null;


    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Main started", "start");
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setLogo(R.drawable.papa_johns_logo); // setting a logo in toolbar
        toolbar.setNavigationIcon(R.drawable.menu_icon);
        toolbar.setNavigationOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
        toolbar.inflateMenu(R.menu.nav_items);
        toolbar.getMenu().getItem(0).setVisible(false); // Hide user icon a the beginning
        toolbar.setOnMenuItemClickListener((e) -> {
            InscriptionFragment fragment = new InscriptionFragment();
            replaceFragmentInFrame(fragment);
            return true;
        });
        setNavigationDrawer();

        // Lance la page d'accueil
        HomeFragment home = new HomeFragment();
        replaceFragmentInFrame(home);
    }

    public void setNavigationDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.getMenu().setGroupVisible(R.id.loggedIn, currentUser != null);

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            Fragment frag = null;
            int itemId = menuItem.getItemId();
            switch (itemId) {
                case R.id.home:
                    frag = new HomeFragment();
                    Toast.makeText(this, "Nom User:"+currentUser.getName()+" Email:"+currentUser.getEmail(), Toast.LENGTH_SHORT).show();
                    break;

                case R.id.nav_profile:
                    frag = new InscriptionFragment();
                    break;
                case R.id.nav_pizzas:
                    frag = new PizzaFragment();
                    break;
            }

            Toast.makeText(getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
            replaceFragmentInFrame(frag);
            return frag != null;
        });
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;

        toolbar.getMenu().getItem(0).setVisible(currentUser != null);
    }



    private void replaceFragmentInFrame(Fragment frag) {
        if (frag != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame, frag);
            transaction.commit();
            drawerLayout.closeDrawers();
        }
    }
}