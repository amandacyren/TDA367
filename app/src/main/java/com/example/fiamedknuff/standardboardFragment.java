package com.example.fiamedknuff;

import android.os.Bundle;

import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A class standardboardFragment that ...
 *
 * Created by
 * @author Emma Stålberg
 */

public class standardboardFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_standardboard, container, false);

        Group group = view.findViewById(R.id.positions);
        int refIds[] = group.getReferencedIds();

        return view;
    }

}