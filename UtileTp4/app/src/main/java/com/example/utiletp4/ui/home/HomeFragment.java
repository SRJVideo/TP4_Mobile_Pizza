package com.example.utiletp4.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.utiletp4.MainActivity;
import com.example.utiletp4.R;
import com.example.utiletp4.ui.ConnectionFragment;
import com.example.utiletp4.ui.InscriptionFragment;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment implements Animation.AnimationListener {

    private ImageView imageViewHaut;
    private ImageView imageViewGauche;
    private ImageView imageViewDroite;
    ImageView imageViewAcceuil;
    Animation animFade;

    private MainActivity mainActivity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        Button loginButton = (Button) root.findViewById(R.id.loginButton);
        Button subscribeButton = (Button) root.findViewById(R.id.subscribeButton);
        loginButton.setEnabled(mainActivity.getCurrentUser() == null);
        subscribeButton.setEnabled(mainActivity.getCurrentUser() == null);
        imageViewHaut = (ImageView) root.findViewById(R.id.imageViewHaut);
        imageViewGauche = (ImageView) root.findViewById(R.id.imageViewGauche);
        imageViewDroite = (ImageView) root.findViewById(R.id.imageViewDroite);

        loginButton.setOnClickListener(this::login);
        subscribeButton.setOnClickListener(this::subscribe);


        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageViewAcceuil = view.findViewById(R.id.imageViewHaut);
        animFade = AnimationUtils.loadAnimation(this.getContext(),
                R.anim.fade );
        imageViewAcceuil.startAnimation(animFade);


    }

    public void login(View view){
        // Launch ConnectionFragment
        ConnectionFragment connectionFragment = new ConnectionFragment();
        replaceFragmentInFrame(connectionFragment);
    }

    public void subscribe(View view){
        // Launch InscriptionFragment
        InscriptionFragment inscriptionFragment = new InscriptionFragment();
        replaceFragmentInFrame(inscriptionFragment);
    }

    private void replaceFragmentInFrame(Fragment frag) {
        if (frag != null) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame, frag);
            transaction.commit();
        }
    }

    // Called when a fragment is first attached to its context.
    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            this.mainActivity = (MainActivity) context;
        }
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
}