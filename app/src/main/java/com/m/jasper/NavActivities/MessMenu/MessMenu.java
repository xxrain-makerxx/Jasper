package com.m.jasper.NavActivities.MessMenu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.m.jasper.R;

import java.util.ArrayList;

public class MessMenu extends Fragment{
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    ArrayList<MessPojo> messMenuList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mess_menu,container,false);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("MessMenu");
        recyclerView=view.findViewById(R.id.recycler_view_mess);
        adapter=new RecyclerAdapterMess(messMenuList,getContext());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messMenuList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {messMenuList.add(snapshot.getValue(MessPojo.class));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }
}
