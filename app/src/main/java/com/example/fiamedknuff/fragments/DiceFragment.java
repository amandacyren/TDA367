package com.example.fiamedknuff.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.fiamedknuff.R;
import com.example.fiamedknuff.model.Piece;
import com.example.fiamedknuff.viewModels.GameViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiceFragment extends Fragment {

    View view;
    ConstraintLayout constraintLayout;
    GameViewModel gameViewModel;

    ImageView diceImage;
    List<Integer> diceImages;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_standardboard, container, false);

        gameViewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);

        constraintLayout = view.findViewById(R.id.constraintLayout);

        initDice();

        return view;
    }

    private void initDice() {
        diceImage = view.findViewById(R.id.diceImage);
        initDiceImages();

        diceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rolledValue = gameViewModel.rollDice();

                // If the dice is rolled, the dice should be rotated and
                // the method diceRolled should be called.
                if (rolledValue != -1) {
                    rotateDice(rolledValue);
                    gameViewModel.diceRolled();
                }
            }
        });
    }

    /**
     * Initiates the diceimages and puts them in a list "diceImages".
     */
    private void initDiceImages() {
        diceImages = new ArrayList<>();
        diceImages.add(R.drawable.dice1);
        diceImages.add(R.drawable.dice2);
        diceImages.add(R.drawable.dice3);
        diceImages.add(R.drawable.dice4);
        diceImages.add(R.drawable.dice5);
        diceImages.add(R.drawable.dice6);
    }

    /**
     * Method that rotates the dice and sets the new rolled value.
     * @param rolledValue is the rolled value
     */
    private void rotateDice(int rolledValue) {
        Animation anim = AnimationUtils.loadAnimation(requireActivity().getApplicationContext(), R.anim.rotate);
        diceImage.startAnimation(anim); // animate the roll of the dice
        diceImage.setImageResource(diceImages.get(rolledValue - 1)); // sets the rolled value
    }

}
