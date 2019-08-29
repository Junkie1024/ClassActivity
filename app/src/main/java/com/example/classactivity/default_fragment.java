package com.example.classactivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class default_fragment extends Fragment {


    ImageView pokemonball;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_default_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pokemonball = view.findViewById(R.id.pokemonball);

        View v = pokemonball;

        ObjectAnimator animation = ObjectAnimator.ofFloat(v, "rotationY",0.0f,360f);
        animation.setDuration(3000);
        animation.setRepeatCount(ValueAnimator.INFINITE);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.start();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }



}
