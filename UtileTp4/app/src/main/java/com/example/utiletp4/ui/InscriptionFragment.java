package com.example.utiletp4.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.utiletp4.MainActivity;
import com.example.utiletp4.R;
import com.example.utiletp4.database.DatabaseManager;
import com.example.utiletp4.modele.Field;
import com.example.utiletp4.modele.User;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InscriptionFragment extends Fragment {

    private EditText name;
    private EditText email;
    private EditText password;
    private EditText address;
    private EditText phone;
    private Button createAccountButton;

    private MainActivity mainActivity;
    DatabaseManager dbm;

    // list of boolean values corresponding to each field validation
    private List<Boolean> areFieldsValid;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_inscription, container, false);
        initAttributes(root);
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
     * initialize fields
     */
    private void initAttributes(View v) {
        // set all fields as invalid fields
        areFieldsValid = new ArrayList<Boolean>();
        for (int i = 0; i < 3; i++) areFieldsValid.add(false); // only 3 fields to validate

        // add validation on fields that need validation
        name = (EditText) v.findViewById(R.id.name);
        email = (EditText) v.findViewById(R.id.email);
        password = (EditText) v.findViewById(R.id.password);
        address = (EditText) v.findViewById(R.id.address);
        phone = (EditText) v.findViewById(R.id.phone);
        createAccountButton = (Button) v.findViewById(R.id.createAccount);
        name.addTextChangedListener(getTextWatcher(Field.NAME, name));
        email.addTextChangedListener(getTextWatcher(Field.EMAIL, email));
        password.addTextChangedListener(getTextWatcher(Field.PASSWORD, password));
        address.addTextChangedListener(getTextWatcher(Field.EMAIL, email));
        password.addTextChangedListener(getTextWatcher(Field.PASSWORD, password));
        dbm = new DatabaseManager(getActivity());

        // set field values and disable email field if User already loggedin
        if (mainActivity.getCurrentUser() != null) {
            name.setText(mainActivity.getCurrentUser().getName());
            email.setText(mainActivity.getCurrentUser().getEmail());
            email.setEnabled(false);
            password.setText(mainActivity.getCurrentUser().getPassword());
            address.setText(mainActivity.getCurrentUser().getAddress());
            phone.setText(mainActivity.getCurrentUser().getPhone());
            createAccountButton.setText("METTRE À JOUR");
            createAccountButton.setEnabled(true);
        }

        // add click listener on createAccount button
        createAccountButton.setOnClickListener((view) -> {

            // insérer ou updater le user suivant loggedin ou pas
            if (mainActivity.getCurrentUser() == null) {
                User newUser = new User(
                        name.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        address.getText().toString(),
                        phone.getText().toString(),
                        0);
                mainActivity.setCurrentUser(newUser);

                dbm.insertUser(newUser);

                // rendre visible
                mainActivity.setNavigationDrawer();
                Toast.makeText(getActivity(), "compte créé, veuillez vous connecter", Toast.LENGTH_SHORT).show();

                // aller à la connection
                ConnectionFragment connectionFragment = new ConnectionFragment();
                replaceFragmentInFrame(connectionFragment);
            } else {
                // update currentUser in Mainactivity
                User user = mainActivity.getCurrentUser();
                user.setName(name.getText().toString());
                user.setPassword(password.getText().toString());
                user.setAddress(address.getText().toString());
                user.setPhone(phone.getText().toString());
                // update user in DB
                dbm.updateUser(user);

            }
        });

        // disable button createAccount
        createAccountButton.setEnabled(false);
    }

    /**
     * Returns a TextWatcher object that checks if the text is valid
     * @param enumField enumField
     * @param editText editText
     * @return TextWatcher
     */
    private TextWatcher getTextWatcher(final Field enumField, final EditText editText) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                verifyField(enumField, editText);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        };
    }

    /**
     * Verify given field:
     * - shows error message if not valid
     * - sets boolean to true if valid in corresponding areFieldsValid list
     * - sets createAccount button to Enabled if all fields are valid
     * @param enumField enumField
     * @param editText editText
     */
    private void verifyField(Field enumField, EditText editText) {
        boolean isValidField = isValidField(editText, enumField);
        if (!isValidField) editText.setError(enumField.validationMessage);
        areFieldsValid.set(enumField.ordinal(), isValidField);
        createAccountButton.setEnabled(!areFieldsValid.contains(false));
    }

    /**
     * Check if field is valid (applies to all fields including email)
     * @param editText editText
     * @param enumField enumField
     * @return boolean true if valid
     */
    private boolean isValidField(EditText editText, Field enumField) {
        return enumField == Field.EMAIL ? isValidEmailField(email.getText().toString()) :
                isValidCommonField(editText.getText().toString(), enumField);
    }

    /**
     * Check if common field (nom, prenom, password, country) is valid
     * @param string string
     * @param enumField enumField
     * @return boolean true if valid
     */
    private boolean isValidCommonField(String string, Field enumField) {
        return string.length() >= enumField.minLength;
    }

    /**
     * Checks the given string against a regex
     * @param email email
     * @return boolean true if string matches the regex
     */
    private static boolean isValidEmailField(String email) {
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
