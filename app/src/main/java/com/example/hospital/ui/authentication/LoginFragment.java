package com.example.hospital.ui.authentication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hospital.R;
import com.example.hospital.data.model.LoginResponseitem;
import com.example.hospital.data.source.remote.RetrofitClient;
import com.example.hospital.databinding.FragmentLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentLoginBinding.bind(view);


        binding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login() {

        RetrofitClient.getWepService().getResponse(binding.phonenumber.getText().toString()
                ,binding.password.getText().toString()).enqueue(new Callback<LoginResponseitem>() {
            @Override
            public void onResponse(Call<LoginResponseitem> call, Response<LoginResponseitem> response) {
 //               Navigation.findNavController(getView()).navigate();

                Toast.makeText(getContext(), "Success Login", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<LoginResponseitem> call, Throwable t) {
                Log.d("ssssssssss", "onFailure: " +t.getLocalizedMessage());
            }
        });

    }


}