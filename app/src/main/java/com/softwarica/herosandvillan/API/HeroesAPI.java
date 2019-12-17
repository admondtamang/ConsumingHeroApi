package com.softwarica.herosandvillan.API;

import com.softwarica.herosandvillan.Model.Heroes;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HeroesAPI {
    // using object
    @POST("heroes")
    Call<Void> addHero(@Body Heroes heroes);

    // Using Field
    @FormUrlEncoded
    @POST("heroes")
    Call<Void> addHero(@Field("name") String name,@Field("desc") String  desc);

    // Using FieldMap
    @FormUrlEncoded
    @POST("heroes")
    Call<Void> addHero(@FieldMap Map<String ,String > map);
}
