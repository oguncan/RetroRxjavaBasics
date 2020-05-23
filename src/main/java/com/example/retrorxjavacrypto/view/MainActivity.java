package com.example.retrorxjavacrypto.view;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.CrossProcessCursor;
import android.os.Bundle;

import com.example.retrorxjavacrypto.adapter.CryptoRecyclerAdapter;
import com.example.retrorxjavacrypto.model.CryptoModel;
import com.example.retrorxjavacrypto.R;
import com.example.retrorxjavacrypto.service.CryptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL = "https://api.nomics.com/v1/";
    Retrofit retrofit;
    CryptoRecyclerAdapter cryptoRecyclerAdapter;
    RecyclerView recyclerView;

    io.reactivex.disposables.CompositeDisposable compositeDisposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        // https://api.nomics.com/v1/currencies/ticker?key=ca9832880575626e04ca5fe68c1bf810
        //Retrofit && JSON
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loadData();

    }

    private void loadData(){
        CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(cryptoAPI.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse));


        /*Call<List<CryptoModel>> call = cryptoAPI.getData();
        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if(response.isSuccessful()){
                    List<CryptoModel> responseList = response.body();
                    cryptoModels = new ArrayList<>(responseList);
                    cryptoRecyclerAdapter = new CryptoRecyclerAdapter(MainActivity.this, cryptoModels);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(cryptoRecyclerAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {

            }
        });*/

    }
    private void handleResponse(List<CryptoModel> cryptoModel){
        cryptoModels = new ArrayList<>(cryptoModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        cryptoRecyclerAdapter = new CryptoRecyclerAdapter(MainActivity.this, cryptoModels);
        recyclerView.setAdapter(cryptoRecyclerAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
