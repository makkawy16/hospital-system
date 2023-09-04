package com.example.hospital.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hospital.R;
import com.example.hospital.data.model.LoginResponseitem;
import com.example.hospital.data.source.remote.RetrofitClient;
import com.example.hospital.databinding.FragmentHomeBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    private String email;
    private String password;
    private String userName;
    private String type;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentHomeBinding.bind(view);

        SharedPreferences sharedPreferences =
                getActivity().getSharedPreferences("sharedData", Context.MODE_PRIVATE);

        email = sharedPreferences.getString("email", "no email");
        password = sharedPreferences.getString("password", "no password");
        type = sharedPreferences.getString("type" , "guest");
       // Log.d("sssssssssssssssssssss", "type: " +type);

       // Log.d("ssssssssssss", "onViewCreated: " + email + "   " + password);

        showUserData();

        if(type.equals("doctor")){
            binding.topLeftBTN.setText("Calls");
            binding.topLeftBTN.setIconResource(R.drawable.calls_ic);
            binding.topLeftBTN.setBackgroundColor(getResources().getColor(R.color.blue));
        }



    }

    private void showUserData() {

        RetrofitClient.getWepService().getResponse(email, password)
                .enqueue(new Callback<LoginResponseitem>() {
                    @Override
                    public void onResponse(Call<LoginResponseitem> call, Response<LoginResponseitem> response) {

                        userName = response.body().getData().getFirstName() + " " + response.body().getData().getLastName();

                        binding.username.setText(userName);
                        binding.specialist.setText(response.body().getData().getSpecialist());

                       /* Log.d("ssssssssss", "onResponse: " + response.body().getData().getFirstName());
                        Log.d("ssssssssss", "onResponse: " + userName);
                        Log.d("ssssssssss", "onResponse: " + response.body().getData().getAddress());
                        Log.d("ssssssssss", "onResponse: " + response.body().getData().getBirthday());
                        Log.d("ssssssssss", "onResponse: " + response.body().getData().getCreatedAt());
                        Log.d("ssssssssss", "onResponse: " + response.body().getData().getGender());
                        Log.d("ssssssssss", "onResponse: " + response.body().getData().getType());
                        Log.d("ssssssssss", "onResponse: " + response.body().getData().getAccessToken());
                        Log.d("ssssssssss", "onResponse: " + response.body().getData().getMobile());*/

                    }

                    @Override
                    public void onFailure(Call<LoginResponseitem> call, Throwable t) {

                    }
                });

    }

}