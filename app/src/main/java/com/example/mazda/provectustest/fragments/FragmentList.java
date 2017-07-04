package com.example.mazda.provectustest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mazda.provectustest.adapters.Adapter;
import com.example.mazda.provectustest.R;
import com.example.mazda.provectustest.interfases.OnFloatingButtonClickListener;
import com.example.mazda.provectustest.interfases.OnUserClickListener;
import com.example.mazda.provectustest.models.User;

import java.util.ArrayList;

/**
 * Created by mazda on 30.06.2017.
 */

public class FragmentList extends Fragment implements View.OnClickListener, OnUserClickListener {

    private static final String USERS = "USERS";
    private RecyclerView rvList;
    private LinearLayoutManager mLayoutManager;
    private Adapter rvAdapter;
    private android.support.design.widget.FloatingActionButton fabAddingUsers;
    private OnFloatingButtonClickListener listener;
    private OnUserClickListener onUserClickListener;
    private ArrayList<User> users;


    public static FragmentList newInstance() {
        FragmentList fragment = new FragmentList();
        return fragment;
    }

    public FragmentList(){
        rvAdapter = new Adapter();
        rvAdapter.setOnUserClickListener(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        rvList = (RecyclerView)view.findViewById(R.id.recyclerView);
        fabAddingUsers = (FloatingActionButton) view.findViewById(R.id.fabAddingUser);
        mLayoutManager = new LinearLayoutManager(getActivity());
        rvList.setLayoutManager(mLayoutManager);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState!=null){
            users = savedInstanceState.getParcelableArrayList(USERS);
            listener = (OnFloatingButtonClickListener) getActivity();
            onUserClickListener = (OnUserClickListener) getActivity();
            rvAdapter.setData(users);

        }
        fabAddingUsers.setOnClickListener(this);

        rvList.setAdapter(rvAdapter);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(USERS,users);
    }

    public void setUsers(ArrayList<User> users){
        this.users = users;
        if(users!=null && rvAdapter != null)
            rvAdapter.setData(users);
    }

    public ArrayList<User> getUsers(){
        return rvAdapter.getData();
    }

    public void setFloatingButtonListener(OnFloatingButtonClickListener listener){
        this.listener = listener;
    }

    public void setOnUserClickListener(OnUserClickListener listener){
        onUserClickListener = listener;
    }
    @Override
    public void onClick(View v) {
        listener.onFloatingButtonClick();
    }

    @Override
    public void onUserClick(int position) {
        if(onUserClickListener != null)
        onUserClickListener.onUserClick(position);
    }
}
