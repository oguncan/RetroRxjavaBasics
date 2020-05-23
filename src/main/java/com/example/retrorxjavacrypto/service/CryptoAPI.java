package com.example.retrorxjavacrypto.service;

import com.example.retrorxjavacrypto.model.CryptoModel;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {
    @GET("currencies/ticker?key=ca9832880575626e04ca5fe68c1bf810")
    Observable<List<CryptoModel>> getData();

    //for RETROFIIIIIIIIIIT
    //Call<List<CryptoModel>> getData();
}
