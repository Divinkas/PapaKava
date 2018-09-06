package com.example.divinkas.papacava.Retrofit;

import com.example.divinkas.papacava.Data.CavaObject;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IMyProducts {
    @GET("products")
    Observable<CavaObject> getAPI_cavaItems();
}
