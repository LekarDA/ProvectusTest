package com.example.mazda.provectustest.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mazda.provectustest.R;
import com.example.mazda.provectustest.fragments.FragmentUserInfo;
import com.example.mazda.provectustest.models.User;

import java.util.ArrayList;

/**
 * Created by mazda on 01.07.2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<User> users;
    private Context context;


    public ViewPagerAdapter(FragmentManager fm, ArrayList<User> users,Context context) {
        super(fm);
        this.users = users;
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        FragmentUserInfo fragmentUserInfo  =  FragmentUserInfo.newInstance();
        fragmentUserInfo.setUser(users.get(position));
        return fragmentUserInfo;
    }

    @Override
    public int getCount() {
        return users == null ? 0 : users.size();
    }

}
