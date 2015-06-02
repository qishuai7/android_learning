package com.alibaba.uilearning.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Students implements Parcelable{
    public int id;
    public String name;
    public String sex;
    
@Override
public boolean equals(Object obj) {
    // TODO Auto-generated method stub
    return super.equals(obj);
}
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int falg) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(sex);
    }

    public static final Parcelable.Creator<Students> CREATOR = new Creator<Students>() {
        
        @Override
        public Students[] newArray(int size) {
            return new Students[size];
        }
        
        @Override
        public Students createFromParcel(Parcel source) {
            Students student = new Students();
            student.id = source.readInt();
            student.name = source.readString();
            student.sex = source.readString();
            
            return student;
        }
    };
    
}
