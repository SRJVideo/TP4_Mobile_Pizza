package com.example.utiletp4;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.utiletp4.database.DatabaseManager;
import com.example.utiletp4.fragment.CommandeFragment;
import com.example.utiletp4.fragment.PizzaFragment;
import com.example.utiletp4.fragment.PointsFragment;
import com.example.utiletp4.modele.Pizza;
import com.example.utiletp4.modele.User;
import com.example.utiletp4.ui.InscriptionFragment;
import com.example.utiletp4.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

    private DrawerLayout drawerLayout;
    private User currentUser = null;
    ScaleGestureDetector scaleGestureDetector;
    private DatabaseManager dbm;
    private Toolbar toolbar;
    Animation animZoomin, animZoomout;
    ImageView imageView;
    ImageView imageViewAcceuil;
    public final static String IMAGE = "Image";
    Animation animFade;
    private float factor;

    String[] sortes = {"Fromage", "Péppéroni", "Bacon", "Garnie", "Tomates", "Végétarienne", "Royale"};
    String[] types = {"Petite", "Moyenne", "Grande"};
    int[] images = {R.drawable.fromage,
            R.drawable.pepperoni,
            R.drawable.bacon,
            R.drawable.garnie,
            R.drawable.tomates,
            R.drawable.vegetarienne,
            R.drawable.royale};

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
        imageView = findViewById(R.id.imageViewPizzas);

        dbm = new DatabaseManager(this);
        if (dbm.readPizza().isEmpty()) AjoutPizzaBD();
        Log.i("Les pizzas", String.valueOf(dbm.readPizza()));


        animZoomin = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        animZoomin.setAnimationListener(this);

        // Pour dézoomer
        animZoomout = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        animZoomout.setAnimationListener(this);


        HomeFragment home = new HomeFragment();
        replaceFragmentInFrame(home);

        //Drawerayout myLayout = findViewById(R.id.drawerLayout);
//        myLayout.setOnTouchListener(
//                new ConstraintLayout.OnTouchListener() {
//                    // La méthode à surcharger
//                    public boolean onTouch(View v,
//                                           MotionEvent m) {
//                        handleTouch(m);
//                        return true;
//                    }
//                }
//        );

    }

    public void auclicImage(View view) {
        //imageView.startAnimation(animZoomin);
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
                    break;
                case R.id.nav_profile:
                    frag = new InscriptionFragment();
                    break;
                case R.id.nav_pizzas:
                    frag = new PizzaFragment();
                    break;
                case R.id.nav_commande:
                    frag = new CommandeFragment();
                    break;
                case R.id.nav_points:
                    frag = new PointsFragment();
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

    /***
     * Ajouter des pizzas dans la BD si celle-ci est vide
     */
    void AjoutPizzaBD() {
        for (int i = 0; i < sortes.length; i++) {
            BigDecimal decimal = BigDecimal.valueOf(Math.random() * 10).setScale(2, RoundingMode.UP);
            double prix = Double.parseDouble(String.valueOf(decimal));
            for (int j = 0; j < types.length; j++) {

                dbm.insertPizza(new Pizza(sortes[i], types[j], prix + j));
            }
        }
    }


    /***
     * Getter setter
     * @return
     */
    public String[] getSortes() {
        return sortes;
    }

    public String[] getTypes() {
        return types;
    }

    public int[] getImages() {
        return images;
    }

    private void animationZoom() {

    }

    void handleTouch(MotionEvent m) {
        // on récupère l’action
        int action = m.getActionMasked();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_POINTER_DOWN:

                break;
            case MotionEvent.ACTION_POINTER_UP:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            default:
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return true;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

//
//    public class MyOnScaleGestureListener extends
//            ScaleGestureDetector.SimpleOnScaleGestureListener {
//
//        @Override
//        public boolean onScale(ScaleGestureDetector detector) {
//
//            float scaleFactor = detector.getScaleFactor();
//            factor *= detector.getScaleFactor();
//            factor = Math.max(0.1f, Math.min(factor, 10.f));
//            imageView.setScaleX(factor);
//            return true;
//        }
//
//    }

}