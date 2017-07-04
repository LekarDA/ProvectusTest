package com.example.mazda.provectustest.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by mazda on 30.06.2017.
 */

public class User implements Parcelable{
    @SerializedName("results")
    private ArrayList<Results> results;
    @SerializedName("info")
    private Info info;

    protected User(Parcel in) {
        results = in.createTypedArrayList(Results.CREATOR);
        info = in.readParcelable(Info.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public ArrayList<Results> getResults() {
        return results;
    }

    public void setResults(ArrayList<Results> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(results);
        dest.writeParcelable(info, flags);
    }
}
