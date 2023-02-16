package com.example.hospital.data.source.remote;

//import com.example.hospital.data.model.Data;


import com.example.hospital.data.model.LoginResponseitem;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebService {

    @POST("login")
    Call<LoginResponseitem> getResponse(@Query("mobile") String mobile , @Query("password") String password);

}
