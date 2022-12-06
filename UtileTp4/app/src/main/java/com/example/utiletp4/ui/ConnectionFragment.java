package com.example.utiletp4.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.utiletp4.MainActivity;
import com.example.utiletp4.R;
import com.example.utiletp4.database.DatabaseManager;
import com.example.utiletp4.modele.Field;
import com.example.utiletp4.modele.User;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnectionFragment extends Fragment {

    private DatabaseManager databaseManager;

    //View
    private EditText emailInput;
    private EditText passwordInput;
    private Button buttonConnection;
    private Button buttonCreerCompte;
    private TextView title;

    private MainActivity mainActivity;

    /**
     * Execute a la creation de l'app
     *
     * @param savedInstanceState savedInstanceState
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_connection, container, false);

        // Instantiate object DB and View
        databaseManager = new DatabaseManager(getActivity());
        initEditField(root);
        buttonConnection = (Button) root.findViewById(R.id.buttonConnection);
        buttonConnection.setOnClickListener(v -> {
            // Chercher dans la BD le User
            User u = databaseManager.findUserByEmail(emailInput.getText().toString().toLowerCase());
            // Si le password est OK
            if (passwordVerification(u)) {
                //Envoie le current user a la mainActivity
                mainActivity.setCurrentUser(u);

                mainActivity.setNavigationDrawer();

            }
        });
        buttonCreerCompte = (Button) root.findViewById(R.id.buttonCreerCompte);
        buttonCreerCompte.setOnClickListener(v -> {
            InscriptionFragment inscriptionFragment = new InscriptionFragment();
            replaceFragmentInFrame(inscriptionFragment);
        });
        title = (TextView) root.findViewById(R.id.title);
        String titleString = title.getText().toString();

        buttonConnection.setEnabled(false);

        //Affiche les users dans la console
        List<User> list = databaseManager.readUsers();
        for (User u : list) {
            Log.d("all users in BD", u.toString());
        }
        return root;
    }

    // Called when a fragment is first attached to its context.
    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
    }

    /**
     * Configure les EditField et les errors
     */
    private void initEditField(View v) {

        // EMAIL INPUT
        emailInput = (EditText) v.findViewById(R.id.emailEdit);
        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!isValidate(emailInput.getText().toString())) {
                    emailInput.setError(Field.EMAIL.validationMessage);
                    buttonConnection.setEnabled(false);
                } else {
                    if (passwordInput.getError() == null && !passwordInput.getText().toString().isEmpty()) {
                        buttonConnection.setEnabled(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // PASSWORD INPUT
        passwordInput = (EditText) v.findViewById(R.id.passwordEdit);
        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (passwordInput.getText().toString().length() < 6) {
                    passwordInput.setError(Field.PASSWORD.validationMessage);
                    buttonConnection.setEnabled(false);
                } else {
                    if (emailInput.getError() == null && !emailInput.getText().toString().isEmpty()) {
                        buttonConnection.setEnabled(true);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * Vérifie que le password saisie fit avec l'object dans le DB
     * @param u user
     * @return boolean
     */
    private boolean passwordVerification(User u) {
        boolean ok = false;
        // Vérifie s'il exist et que son password est OK sinon Boite de dialogue
        if (u != null && u.getPassword().equals(passwordInput.getText().toString())) {
            ok = true;
        } else {
            // Boite de dialogue Error
            new AlertDialog.Builder(getActivity())
                    .setTitle("Error")
                    .setMessage("Email ou mot de passe invalide ! Voulez vous vous inscrire ? ").setNegativeButton("Non", null)
                    .setPositiveButton("Oui", (dialog, which) -> {
                        InscriptionFragment inscriptionFragment = new InscriptionFragment();
                        replaceFragmentInFrame(inscriptionFragment);
                    }).create()
                    .show();
        }
        return ok;
    }

    /**
     * Vérifie avec un regex si le email a le bon format
     *
     * @param email email
     * @return bool
     */
    private boolean isValidate(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    private void replaceFragmentInFrame(Fragment frag) {
        if (frag != null) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame, frag);
            transaction.commit();
        }
    }

}
