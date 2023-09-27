package com.example.hospital.data.source.remote;


import com.example.hospital.data.model.LoginResponseitem;
import com.example.hospital.data.model.RegisterResponse;

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


    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse>register(
        @Field("email") String email,
        @Field("password") String password,
        @Field("first_name") String fName,
        @Field("last_name") String lName,
        @Field("gender") String gender,
        @Field("specialist") String specialist,
        @Field("birthday") String birthday,
        @Field("status") String status,
        @Field("address") String address,
        @Field("mobile") String mobile,
        @Field("specialist") String specialistt,
        @Field("type") String type


    );
}
