package com.example.hospital.ui.HR;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hospital.R;
import com.example.hospital.databinding.FragmentAddUserBinding;


public class AddUserFragment extends Fragment {

    String []items = {"Male","Female"};
    FragmentAddUserBinding binding;
    ArrayAdapter<String> adapterItems;

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

         binding.genderAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                 String item = parent.getItemAtPosition(position).toString();
                // binding.genderAutoComplete.setText(item);
                 Log.d("ssssssssss", "onItemClick: " + item);
             }
         });
    }

private void genderList(){
        adapterItems = new ArrayAdapter<String>(getContext(),R.layout.gender_list,items);
        binding.genderAutoComplete.setAdapter(adapterItems);
}

}