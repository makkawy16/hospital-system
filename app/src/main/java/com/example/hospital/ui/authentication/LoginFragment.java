package com.example.hospital.ui.authentication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.hospital.ui.HomeActivity;
import com.example.hospital.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Utils utils = new Utils();

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
                if (!binding.emailtxt.getText().toString().isEmpty() && !binding.password.getText().toString().isEmpty())
                    login();
                else
                    Toast.makeText(getContext(), "Please Enter all Fields", Toast.LENGTH_SHORT).show();

            }

        });

        sharedPreferences =
                getActivity().getSharedPreferences("sharedData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


    }

    private void login() {

        utils.waitnig("Login", "Wait...", getContext());

        RetrofitClient.getWepService().getResponse(binding.emailtxt.getText().toString()
                , binding.password.getText().toString()).enqueue(new Callback<LoginResponseitem>() {
            @Override
            public void onResponse(Call<LoginResponseitem> call, Response<LoginResponseitem> response) {
                //               Navigation.findNavController(getView()).navigate();

                if (response.body().getStatus() != 0) {
                    editor.putString("email", binding.emailtxt.getText().toString().trim());
                    editor.putString("password", binding.password.getText().toString().trim());
                    editor.putString("type", response.body().getData().getType());
                    editor.apply();
                    Toast.makeText(getContext(), "Success Login", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getActivity(), HomeActivity.class));
                }
                else {
                    utils.alertDialog("Error",response.body().getMessage(),getContext());
                }
                utils.mloadingBar.dismiss();

            }

            @Override
            public void onFailure(Call<LoginResponseitem> call, Throwable t) {
                Log.d("ssssssssss", "onFailure: " + t.getMessage());
                Log.d("ssssssssss", "onFailure: " + t.getLocalizedMessage());
                utils.mloadingBar.dismiss();
                Toast.makeText(getContext(), "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


}