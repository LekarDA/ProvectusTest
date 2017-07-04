package com.example.mazda.provectustest.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mazda.provectustest.R;
import com.example.mazda.provectustest.UsersViewHolder;
import com.example.mazda.provectustest.interfases.OnUserClickListener;
import com.example.mazda.provectustest.models.User;

import java.util.ArrayList;

/**
 * Created by mazda on 30.06.2017.
 */

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnUserClickListener {

    private ArrayList<User> users;
    private LayoutInflater inflater;
    private UsersViewHolder viewHolder;
    private OnUserClickListener listener;

    public Adapter(){
        users = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_of_user,parent,false);
        viewHolder = new UsersViewHolder(view);
        viewHolder.setListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((UsersViewHolder)holder).initView(users.get(position));
    }

    @Override
    public int getItemCount() {
        if (users == null) return 0;
        else return users.size();
    }


    public void setData(ArrayList<User> listOfUsers){
        users = listOfUsers;
        Log.e("ADAPTER","set data size "+ String.valueOf(users.size()));
        notifyDataSetChanged();
    }

    public ArrayList<User> getData(){
        Log.e("ADAPTER"," get data size "+ String.valueOf(users.size()));
        return users;
    }

    public void setOnUserClickListener(OnUserClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onUserClick(int position) {
        listener.onUserClick(position);
    }
}
