package com.wbm.smart.networking;

import com.wbm.smart.utils.ApiKeys;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private ApiService apiService;
    private static RetrofitClient retrofitClient = null;
    private static final String BASE_URL = ApiKeys.BASE_URL;

    public static RetrofitClient getInstance() {
        if (retrofitClient == null) {
            return retrofitClient = new RetrofitClient();
        } else
            return retrofitClient;

    }

    private RetrofitClient() {
/*
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        apiService = retrofit.create(ApiService.class);*/

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        OkHttpClient okHttpClientUnsafe = UnsafeOkHttpClient.getUnsafeOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientUnsafe)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}
