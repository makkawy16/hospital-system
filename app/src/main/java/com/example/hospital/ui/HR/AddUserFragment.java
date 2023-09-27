package com.example.hospital.ui.HR;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.hospital.R;
import com.example.hospital.data.model.RegisterResponse;
import com.example.hospital.data.source.remote.RetrofitClient;
import com.example.hospital.databinding.FragmentAddUserBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddUserFragment extends Fragment {

    private String[] items = {"Male", "Female"};
    private FragmentAddUserBinding binding;
    private ArrayAdapter<String> adapterItems;
    private String item;

    public AddUserFragment() {
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
        return inflater.inflate(R.layout.fragment_add_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = FragmentAddUserBinding.bind(view);
        genderList();

        binding.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_addUserFragment_to_employeeFragment);
            }
        });

        binding.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

        binding.genderAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                item = parent.getItemAtPosition(position).toString();
                // binding.genderAutoComplete.setText(item);
                Log.d("ssssssssss", "onItemClick: " + item);
            }
        });
    }

    private void genderList() {
        adapterItems = new ArrayAdapter<String>(getContext(), R.layout.gender_list, items);
        binding.genderAutoComplete.setAdapter(adapterItems);
    }

    private void addUser() {
        RetrofitClient.getWepService()
                .register(binding.emailnew.getText().toString(), binding.passwordnew.getText().toString()
                        , binding.firstname.getText().toString(), binding.lastname.getText().toString()
                        ,item,binding.specialistTxt.getText().toString() , binding.birthdate.getText().toString()
                        ,binding.statusTxt.getText().toString() , binding.addressnew.getText().toString()
                        , binding.phoneNumbernew.getText().toString() ,binding.specialistTxt.getText().toString()
                        , binding.specialistTxt.getText().toString()
                )
                .enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        //date of birth form 2020-12-12
                        Log.d("ssssssssssssssssssssss", "onResponse: register1   " +response.body());
                        if(response.body()!=null && response.body().getMessage()!= null) {
                            Toast.makeText(getContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getContext(), "check all fields or reformat date of birth  " + response.body().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {

                        Log.d("sssssssssssssssssss", "onFailure:  register   " + t.getLocalizedMessage() );
                    }
                });
    }

}