package com.xzb.basecore.entitys;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Author: xiangzhenbiao
 * @Date: 2019-03-17 23:16
 * @Description: 待传输的实体类，需要进行序列化()
 */

public class Student implements Parcelable{

    private String userName;

    private int age;

    public Student(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    protected Student(Parcel in) {
        userName = in.readString();
        age = in.readInt();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getUserName() {
        return userName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeInt(age);
    }
}
