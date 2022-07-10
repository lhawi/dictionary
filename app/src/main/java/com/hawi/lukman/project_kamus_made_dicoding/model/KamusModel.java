package com.hawi.lukman.project_kamus_made_dicoding.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import static android.provider.BaseColumns._ID;
import static com.hawi.lukman.project_kamus_made_dicoding.db.DatabaseContract.KamusColumns.FIELD_ARTI;
import static com.hawi.lukman.project_kamus_made_dicoding.db.DatabaseContract.KamusColumns.FIELD_KATA;

public class KamusModel implements Parcelable {
    private int id;
    private String kata;
    private String deskripsi;
    private String category;

    public KamusModel(String kata, String deskripsi) {
        this.kata = kata;
        this.deskripsi = deskripsi;
    }

    public KamusModel(int id, String kata, String deskripsi) {
        this.id = id;
        this.kata = kata;
        this.deskripsi = deskripsi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKata() {
        return kata;
    }

    public void setKata(String kata) {
        this.kata = kata;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public KamusModel() {
    }

    public KamusModel(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
        this.kata = cursor.getString(cursor.getColumnIndexOrThrow(FIELD_KATA));
        this.deskripsi = cursor.getString(cursor.getColumnIndexOrThrow(FIELD_ARTI));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.kata);
        dest.writeString(this.deskripsi);
        dest.writeString(this.category);
    }

    protected KamusModel(Parcel in) {
        this.id = in.readInt();
        this.kata = in.readString();
        this.deskripsi = in.readString();
        this.category = in.readString();
    }

    public static final Creator<KamusModel> CREATOR = new Creator<KamusModel>() {
        @Override
        public KamusModel createFromParcel(Parcel source) {
            return new KamusModel(source);
        }

        @Override
        public KamusModel[] newArray(int size) {
            return new KamusModel[size];
        }
    };
}
