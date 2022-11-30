package com.example.tp4_pizzaapplication.util.service;


import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

// Nous avons implementer et importer loopj pour accéder aux données de l'URL

/**
 * WebReq est utilisée pour envoyer des requêtes sur le serveur.
 * Il a une méthode GET/POST pour renvoyer des données JSON à partir d'une URL donnée.
 */
public class WebReq {
    public static AsyncHttpClient client;

    static {

        client = new AsyncHttpClient(true, 8888, 443);
    }

    public static void get(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        client.get(context, getAbsoluteUrl(url), params, responseHandler);
    }

    //concatenation of base url and file name
    private static String getAbsoluteUrl(String relativeUrl) {
        Log.d("response URL: ", GlobalClass.getInstance().BASE_URL + relativeUrl + " ");
        return GlobalClass.getInstance().BASE_URL + relativeUrl;
    }

    public static void post(Context context, String url, RequestParams params, ResponseHandlerInterface responseHandler) {
        client.post(context, getAbsoluteUrl(url), params, responseHandler);
    }
}