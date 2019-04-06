package com.m.jasper.NavActivities.Home;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.m.jasper.Pojo;
import com.m.jasper.R;
import com.m.jasper.RecyclerAdapter;

import java.util.ArrayList;


public class Notifications extends Fragment {
    public Notifications(){

    }
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<Pojo> listsPojo=new ArrayList<>();;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    SwipeRefreshLayout swipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_notification,container,false);
        firebaseDatabase=FirebaseDatabase.getInstance();
        recyclerView=view.findViewById(R.id.recycler_view);
        swipeRefreshLayout=view.findViewById(R.id.swipe_refresh);
        layoutManager=new LinearLayoutManager(getContext());


        databaseReference=firebaseDatabase.getReference().child("Notifications");
        final ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listsPojo.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    listsPojo.add(snapshot.getValue(Pojo.class));
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                databaseReference.removeEventListener(valueEventListener);
                databaseReference.addListenerForSingleValueEvent(valueEventListener);
                swipeRefreshLayout.setRefreshing(false);

            }
        });

        adapter=new RecyclerAdapter(getContext(),listsPojo);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

}
