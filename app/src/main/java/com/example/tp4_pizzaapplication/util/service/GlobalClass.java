package com.example.tp4_pizzaapplication.util.service;

import android.app.Application;


/**
 * Cette  classe nous aidera à accéder à l'URL BASE et nous donnera également une instance d'application.
 * cette classe sera lancé au démarrage de l'application
 */

public class GlobalClass extends Application {
    public static final String BASE_URL = "http://10.0.2.2:8888/";
    //    public static final String BASE_URL = "http:/localhost:8888/androidApp/api.php";
    private static GlobalClass singleton;
    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }
    public static GlobalClass getInstance() {
        return singleton;
    }
}

/***
 * Android créée une base de donnée
 *creer requête
 *creer bd rafraichir
 *creer table rafraichier
 *changer url du localhost dans webreq et fichier api
 *
 */