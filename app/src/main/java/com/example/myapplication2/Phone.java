package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "telefony")
public class Phone {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "identyfikator")
    private long mKolumna;
    @Ignore
    public Phone(){}
    @NonNull
    @ColumnInfo(name="producent")
    private String mKolumna2;


    @NonNull
    @ColumnInfo(name = "model")
    private String mKolumna3;

    @NonNull
    @ColumnInfo(name ="wesjaAndroida")
    private String mKolumna4;

    @NonNull
    @ColumnInfo(name = "stronaWWWW")
    private String mKolumna5;

    public Phone(@NonNull String mKolumna2,@NonNull String mKolumna3,@NonNull String mKolumna4,@NonNull String mKolumna5){
        this.mKolumna2= mKolumna2;
        this.mKolumna3= mKolumna3;
        this.mKolumna4=mKolumna4;
        this.mKolumna5=mKolumna5;
    }
    @Ignore
    public Phone(long mKolumna,String mKolumna2,String mKolumna3,String mKolumna4,String mKolumna5){
        this.mKolumna=mKolumna;
        this.mKolumna2=mKolumna2;
        this.mKolumna3=mKolumna3;
        this.mKolumna4=mKolumna4;
        this.mKolumna5=mKolumna5;

    }


    @NonNull
    public long getMKolumna() { return mKolumna; }
    @NonNull
    public String getMKolumna2() {
        return mKolumna2;
    }
    @NonNull
    public String getMKolumna3() {
        return mKolumna3;
    }
    @NonNull
    public String getMKolumna4() {
        return mKolumna4;
    }
    @NonNull
    public String getMKolumna5() {
        return mKolumna5;
    }

    public void setMKolumna(long mKolumna) {
        this.mKolumna = mKolumna;
    }

    public void setMKolumna2(@NonNull String mKolumna2) {
        this.mKolumna2 = mKolumna2;
    }

    public void setMKolumna3(@NonNull String mKolumna3) {
        this.mKolumna3 = mKolumna3;
    }

    public void setMKolumna4(@NonNull String mKolumna4) {
        this.mKolumna4 = mKolumna4;
    }

    public void setmKolumna5(@NonNull String mKolumna5) {
        this.mKolumna5 = mKolumna5;
    }
}

