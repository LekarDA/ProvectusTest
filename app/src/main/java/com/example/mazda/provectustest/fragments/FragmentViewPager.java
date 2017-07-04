package com.example.mazda.provectustest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mazda.provectustest.R;
import com.example.mazda.provectustest.adapters.ViewPagerAdapter;
import com.example.mazda.provectustest.models.User;

import java.util.ArrayList;

/**
 * Created by mazda on 01.07.2017.
 */

public class FragmentViewPager extends Fragment {

    private static final String USER_POSITION = "USER_POSITION";
    private static final String USERS = "USERS";

    private ViewPager viewPager;
    private ArrayList<User> users;
    private ViewPagerAdapter vpAdapter;
    private int currentItem;

    public static FragmentViewPager newInstance(int userPosition) {
        Bundle args = new Bundle();
        args.putInt(USER_POSITION,userPosition);
        FragmentViewPager fragment = new FragmentViewPager();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentViewPager(){
        users = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_pager,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.vpContainer);
        if(savedInstanceState != null)
            users = savedInstanceState.getParcelableArrayList(USERS);
        vpAdapter = new ViewPagerAdapter(getChildFragmentManager(),users,getActivity());
        viewPager.setAdapter(vpAdapter);
        viewPager.setCurrentItem(currentItem);
    }


    public void setPage(int position){
        currentItem = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(USERS,users);
    }

    public void setUsers(ArrayList<User> users){
        this.users.addAll(users);
    }
}
