package com.example.mazda.provectustest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mazda.provectustest.R;

/**
 * Created by mazda on 03.07.2017.
 */

public class ProgressFragment extends Fragment implements View.OnTouchListener {
    public static final String TAG = "PROGRESS_FRAGMENT";
    public static final String TAG2 = "PROGRESS_FRAGMENT2";
    private LinearLayout linearLayout;

    public static ProgressFragment newInstance() {
        ProgressFragment fragment = new ProgressFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_progress,container,false);
        linearLayout = (LinearLayout)view.findViewById(R.id.fragment_progress_linearLayout);
        linearLayout.setOnTouchListener(this);
        return view;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}