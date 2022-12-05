package com.example.tp4_pizzaapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp4_pizzaapplication.R;
import com.example.tp4_pizzaapplication.databinding.ActivityConnexionBinding;
import com.example.tp4_pizzaapplication.databinding.NouveauCompteMainBinding;
import com.example.tp4_pizzaapplication.util.service.WebReq;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

public class SignupActivity extends MainActivity {
    NouveauCompteMainBinding nouveauCompteMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        init();
        nouveauCompteMainBinding = NouveauCompteMainBinding.inflate(getLayoutInflater());
        setContentView(nouveauCompteMainBinding.getRoot());
        setContentView(R.layout.nouveau_compte_main);
        initEditField();
        initPasswordField();
    }

    private void initPasswordField() {
        // EMAIL INPUT
        nouveauCompteMainBinding.editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (nouveauCompteMainBinding.editPassword.getText().toString().length() < 6) {
                    nouveauCompteMainBinding.editPassword.setError("Au moins 6 caractères");
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void initEditField() {
        // EMAIL INPUT
        nouveauCompteMainBinding.editCourriel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!isValidate(nouveauCompteMainBinding.editCourriel.getText().toString())) {
                    nouveauCompteMainBinding.editCourriel.setError("Addresse Courriel non valide");
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
    private class ResponseHandler extends JsonHttpResponseHandler {
        @Override
        public void onStart() {
            super.onStart();
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            super.onSuccess(statusCode, headers, response);
            Log.d("response ", response.toString() + " ");
            try {
                if (response.getBoolean("error")) {
                    // failed to login
                    Toast.makeText(context, response.getString("message"), Toast.LENGTH_SHORT).show();
                } else {
                    // successfully logged in
                    JSONObject user = response.getJSONObject("user");
                    //save login values
                    sharedPrefEditor.putBoolean("login", true);
                    sharedPrefEditor.putString("id", user.getString("id"));
                    sharedPrefEditor.putString("name", user.getString("name"));
                    sharedPrefEditor.putString("email", user.getString("email"));
                    sharedPrefEditor.apply();
                    sharedPrefEditor.commit();
                    intent = new Intent(context, AccueilActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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

