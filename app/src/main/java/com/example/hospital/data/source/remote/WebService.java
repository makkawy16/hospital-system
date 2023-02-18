package com.example.hospital.data.source.remote;


import com.example.hospital.data.model.LoginResponseitem;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface WebService {

    /*@POST("login")
    Call<LoginResponseitem> getResponse(@Query("mobile") String mobile , @Query("password") String password);*/

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponseitem> getResponse(
            @Field("email") String email ,
            @Field("password") String password);

}
