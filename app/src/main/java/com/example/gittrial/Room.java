package com.example.gittrial;

import android.os.Parcel;
import android.os.Parcelable;

public class Room implements Parcelable {

    private final String name;

    public String getName() {
        return name;
    }

    public int getChaircount() {
        return chaircount;
    }

    public static Creator<Room> getCREATOR() {
        return CREATOR;
    }

    private final int chaircount;

    public Room(String name, int chaircount) {
        this.name = name;
        this.chaircount = chaircount;
    }

    protected Room(Parcel in) {
        name = in.readString();
        chaircount = in.readInt();
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(chaircount);
    }
}
