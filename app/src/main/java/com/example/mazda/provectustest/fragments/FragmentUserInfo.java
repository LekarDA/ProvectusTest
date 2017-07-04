package com.example.mazda.provectustest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mazda.provectustest.R;
import com.example.mazda.provectustest.models.User;
import com.squareup.picasso.Picasso;

import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mazda on 01.07.2017.
 */

public class FragmentUserInfo extends Fragment {

    private static final String USER = "USER";
    private User user;
    private TextView name,surname,state,city,street,phone,code,email;
    private CircleImageView photo;
    private String gender = "male";
    private ImageView background;
    private int[]back = {R.drawable.nature_sea,R.drawable.nature_mountan,
            R.drawable.nature_field_small,R.drawable.nature_forest_small,R.drawable.nature_sky_small};


    public static FragmentUserInfo newInstance() {
        Bundle args = new Bundle();
        FragmentUserInfo fragment = new FragmentUserInfo();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info,container,false);
        name = (TextView) view.findViewById(R.id.username);
        surname = (TextView) view.findViewById(R.id.usersurname);
        photo = (CircleImageView) view.findViewById(R.id.photo);
        background =   (ImageView) view.findViewById(R.id.profileBackground);
        state = (TextView)view.findViewById(R.id.state);
        city = (TextView)view.findViewById(R.id.city);
        street = (TextView)view.findViewById(R.id.street);
        phone = (TextView)view.findViewById(R.id.phone);
        code = (TextView)view.findViewById(R.id.code);
        email  = (TextView)view.findViewById(R.id.email);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState != null)
            user = savedInstanceState.getParcelable(USER);
        name.setText(user.getResults().get(0).getName().getFirst());
        surname.setText(user.getResults().get(0).getName().getLast());
        state.setText(user.getResults().get(0).getLocation().getState());
        city.setText(user.getResults().get(0).getLocation().getCity());
        street.setText(user.getResults().get(0).getLocation().getStreet());
        phone.setText(user.getResults().get(0).getPhone());
        code.setText(String.valueOf(user.getResults().get(0).getLocation().getPostcode()));
        email.setText(user.getResults().get(0).getEmail());
        setBackground();
        setGenderColor();
        Picasso.with(getActivity()).load(user.getResults().get(0).getPicture().getLarge()).into(photo);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(USER,user);
    }

    private void setGenderColor() {
        int color = user.getResults().get(0).getGender().equals(gender) ? getResources().getColor(R.color.colorPrimary)
                : getResources().getColor(R.color.colorAccent);
        photo.setBorderColor(color);
    }

    private void setBackground() {
        Random r = new Random();
        int random = r.nextInt(back.length);
        background.setBackgroundResource(back[random]);
    }

    public void setUser(User user){
        this.user = user;
    }


}
