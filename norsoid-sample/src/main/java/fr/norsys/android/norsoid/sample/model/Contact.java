package fr.norsys.android.norsoid.sample.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by atoumji on 04/11/16.
 */

public class Contact implements Parcelable {

    String name;
    String numberPhone;

    public Contact(String name, String numberPhone) {
        this.name = name;
        this.numberPhone = numberPhone;
    }

    protected Contact(Parcel in) {
        name = in.readString();
        numberPhone = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.numberPhone);

    }
}
