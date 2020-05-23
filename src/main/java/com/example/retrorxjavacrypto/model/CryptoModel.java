package com.example.retrorxjavacrypto.model;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

public class CryptoModel {
    @SerializedName("currency")
    public String currency;
    @SerializedName("name")
    public String name;
    @SerializedName("price")
    public String price;
    @SerializedName("logo_url")
    public String logo_url;
}
