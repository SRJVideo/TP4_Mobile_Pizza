package com.example.tp4_pizzaapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp4_pizzaapplication.R;
import com.example.tp4_pizzaapplication.databinding.ActivityConnexionBinding;
import com.example.tp4_pizzaapplication.util.service.WebReq;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends MainActivity {
    ActivityConnexionBinding activityConnexionBinding;
    //List<Client> listeClientsBD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        activityConnexionBinding = ActivityConnexionBinding.inflate(getLayoutInflater());
        setContentView(activityConnexionBinding.getRoot());
        init();
        initEditField();
        initPasswordField();
    }

    void lanceActivityLogin(){
        Intent in = new Intent(this, LoginActivity.class);
        startActivity(in);
    }

    private boolean VerifierSiLeUserExiste() {
        //listeUserBD = databaseManager.readUsers();
        String courriel = activityConnexionBinding.EditCourriel.getText().toString();
        String motDePasse = activityConnexionBinding.editPassword.getText().toString();
        boolean existe = false;
      //  System.out.println(listeUserBD.size());
       /* for (User user : listeUserBD) {
            System.out.println(user);
            if (user.getEmail().equals(courriel) && user.getPassword().equals(password)) {
                currentFirstName = user.getFirstName();
                currentLastName = user.getLastName();
                existe = true;
                break;
            }
        }*/
        return existe;
    }

    /**
     * permet la validation de l'email et mot de passe de l'utilisateur
     * Si tout est correcte, il envoie les données au serveur.
     */
    private void LanceActiviteSiUserExiste() {
        String courriel = activityConnexionBinding.EditCourriel.getText().toString();
        String motDePasse = activityConnexionBinding.editPassword.getText().toString();
        if (VerifierSiLeUserExiste()) {
            Intent intent = new Intent(this, AccueilActivity.class);
            intent.putExtra("CourrielCourant", courriel);
            intent.putExtra("PasswordCourant", motDePasse);
            startActivity(intent);
        }

    }
    public void init() {
        context =this;
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        sharedPrefEditor = sharedPreferences.edit();
    }

    private void initPasswordField() {
        // EMAIL INPUT
        activityConnexionBinding.editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (activityConnexionBinding.editPassword.getText().toString().length() < 6) {
                    activityConnexionBinding.editPassword.setError("Au moins 6 caractères");
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void initEditField() {
        // EMAIL INPUT
        activityConnexionBinding.EditCourriel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!isValidate(activityConnexionBinding.EditCourriel.getText().toString())) {
                    activityConnexionBinding.EditCourriel.setError("Addresse Courriel non valide");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    private boolean isValidate(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    /**
     * essaie de de connexion à travers le serveur
     */
    private class ResponseHandler extends JsonHttpResponseHandler {
        @Override
        public void onStart() {
            super.onStart();
        }
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            super.onSuccess(statusCode, headers, response);
            Log.d("response ",response.toString()+" ");
            try {
                if (response.getBoolean("error")){
                    // failed to login
                    Toast.makeText(context,response.getString("message"),Toast.LENGTH_SHORT).show();
                }else{
                    // successfully logged in
                    JSONObject user = response.getJSONObject("user");
                    //save login values
                    sharedPrefEditor.putBoolean("login",true);
                    sharedPrefEditor.putString("id",user.getString("id"));
                    sharedPrefEditor.putString("name",user.getString("name"));
                    sharedPrefEditor.putString("email",user.getString("email"));
                    sharedPrefEditor.apply();
                    sharedPrefEditor.commit();

                    //TODO Redirect to "Une activité pour commander une pizza"     ----- A modifier
                    intent = new Intent(context,null);
                    startActivity(intent);
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            super.onFailure(statusCode, headers, responseString, throwable);
        }
        @Override
        public void onFinish() {
            super.onFinish();
        }
    }
}
