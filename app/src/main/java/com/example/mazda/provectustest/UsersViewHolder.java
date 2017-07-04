package com.example.mazda.provectustest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mazda.provectustest.interfases.OnUserClickListener;
import com.example.mazda.provectustest.models.User;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mazda on 30.06.2017.
 */

public class UsersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Context context;
    private CircleImageView imageView;
    private TextView name, lastname,phone;
    private OnUserClickListener listener;


    public UsersViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        imageView = (CircleImageView) itemView.findViewById(R.id.profile_image);
        lastname = (TextView) itemView.findViewById(R.id.lastname);
        name = (TextView) itemView.findViewById(R.id.name);
        phone = (TextView) itemView.findViewById(R.id.phone);
        itemView.setOnClickListener(this);
    }

    public void initView(User user){
        if(user!=null) {
            Picasso.with(context).load(user.getResults().get(0).getPicture().getLarge()).into(imageView);
            lastname.setText(user.getResults().get(0).getName().getLast());
            name.setText(user.getResults().get(0).getName().getFirst());
            phone.setText(user.getResults().get(0).getPhone());
        }
    }

    public void setListener(OnUserClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onUserClick(getAdapterPosition());
    }
}
