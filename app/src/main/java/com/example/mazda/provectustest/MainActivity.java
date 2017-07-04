package com.example.mazda.provectustest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mazda.provectustest.fragments.FragmentList;
import com.example.mazda.provectustest.fragments.FragmentViewPager;
import com.example.mazda.provectustest.fragments.ProgressFragment;
import com.example.mazda.provectustest.interfases.OnFloatingButtonClickListener;
import com.example.mazda.provectustest.interfases.OnUserClickListener;
import com.example.mazda.provectustest.models.User;
import com.example.mazda.provectustest.service.RetrofitConfig;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnFloatingButtonClickListener, OnUserClickListener {

    private static final String LIST_OF_USERS = "LIST_OF_USERS";


    private Toolbar toolbar;
//    private TextView toolbarTitle;
    private ArrayList<User> users;
    private int countOfUsers = 10;
    private FragmentList fragmentList;
    private int countOfLoaded = 0;
    private FragmentViewPager fragmentViewPager;
    private ProgressFragment progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
        setTitle(getResources().getString(R.string.list));
        users = new ArrayList<>();
        fragmentList = FragmentList.newInstance();
        fragmentList.setFloatingButtonListener(this);
        fragmentList.setOnUserClickListener(this);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentList).commit();
            countOfLoaded++;
            requestToServer();
        } else {
            users = savedInstanceState.getParcelableArrayList(LIST_OF_USERS);
            if(fragmentViewPager != null)
            fragmentViewPager.setUsers(users);
        }
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(LIST_OF_USERS, users);
        super.onSaveInstanceState(outState);
    }

    private void requestToServer() {
        showProgress();
        Call<User> callback = RetrofitConfig.getService().getUser();
        callback.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null) {
                    users.add(response.body());

                    int x = countOfLoaded > 1? countOfLoaded - 1 : 0;
                    if (users.size() - x * countOfUsers < countOfUsers)
                        requestToServer();
                    else {fragmentList.setUsers(users);
                        Log.e("activity", "users size " + String.valueOf(fragmentList.getUsers().size()));
                        dismiss();
                    }

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Check network connection", Toast.LENGTH_SHORT).show();
                if (users.size() - countOfLoaded * countOfUsers < countOfUsers)
                    requestToServer();
            }
        });
    }

    @Override
    public void onFloatingButtonClick() {
        countOfLoaded++;
        requestToServer();
    }

    @Override
    public void onUserClick(int position) {
        fragmentViewPager = FragmentViewPager.newInstance(position);
        fragmentViewPager.setUsers(users);
        fragmentViewPager.setPage(position);
        Log.e("ACTIVITY","users size"+String.valueOf(users.size()));
        getSupportFragmentManager().beginTransaction().addToBackStack(null).
                replace(R.id.container, fragmentViewPager).commit();
    }


    public void showProgress() {
//        isProgressShowing = true;
        getSupportFragmentManager().beginTransaction().replace(R.id.progress_container,
                ProgressFragment.newInstance(), ProgressFragment.TAG).commit();
    }


    public void dismiss() {
//        isProgressShowing = false;
        progress = (ProgressFragment)getSupportFragmentManager().findFragmentByTag(ProgressFragment.TAG);
        if (progress != null) {
//            isProgressShowing = false;
            getSupportFragmentManager().beginTransaction().remove(progress).commit();
        }
    }
}
