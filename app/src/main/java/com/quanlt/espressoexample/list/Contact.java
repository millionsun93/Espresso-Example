package com.quanlt.espressoexample.list;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by quanlt on 04/01/2017.
 */

public class Contact implements Parcelable {
    public String name;
    public String avatar;

    public Contact(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.avatar);
    }

    protected Contact(Parcel in) {
        this.name = in.readString();
        this.avatar = in.readString();
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    @Override
    public String toString() {
        return name;
    }
}
