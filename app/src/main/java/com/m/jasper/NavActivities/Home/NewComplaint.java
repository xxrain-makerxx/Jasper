package com.m.jasper.NavActivities.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.m.jasper.Pojo;
import com.m.jasper.R;


public class NewComplaint extends Fragment {
    public NewComplaint(){
    }
Button button;
DatabaseReference databaseReference;
FirebaseDatabase firebaseDatabase;
EditText editText;
FirebaseAuth firebaseAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_new_complaint,container,false);
        firebaseDatabase=FirebaseDatabase.getInstance();
        editText=view.findViewById(R.id.etv_complain);
        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference=firebaseDatabase.getReference().child("Complaints").child(firebaseAuth.getCurrentUser().getUid());
        button=view.findViewById(R.id.bt_register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.push().setValue(new Pojo(editText.getText().toString(),editText.getText().toString()));
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
        return view;
    }




}
