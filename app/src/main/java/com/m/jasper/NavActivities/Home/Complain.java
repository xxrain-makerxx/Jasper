package com.m.jasper.NavActivities.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.m.jasper.NavActivities.MessMenu.RecyclerAdapterMess;
import com.m.jasper.Pojo;
import com.m.jasper.R;
import com.m.jasper.RecyclerAdapterComplaint;

import java.util.ArrayList;

public class Complain extends Fragment {
    public Complain(){

    }
    FloatingActionButton fab;
    ArrayList<Pojo> listsPojo=new ArrayList<>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    RelativeLayout relativeLayout;
    FragmentManager fragmentManager;
    FirebaseAuth firebaseAuth;
    ValueEventListener valueEventListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view=inflater.inflate(R.layout.fragment_complaint,container,false);
        swipeRefreshLayout=view.findViewById(R.id.swipe_refresh);
        fab=view.findViewById(R.id.fab);
        recyclerView=view.findViewById(R.id.recycler_view_complaint);
        relativeLayout=view.findViewById(R.id.new_complaint_container);
        layoutManager=new LinearLayoutManager(getContext());
        fragmentManager = getActivity().getSupportFragmentManager();
        firebaseAuth=FirebaseAuth.getInstance();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragmentManager.findFragmentByTag("my")==null)
                    fragmentManager.beginTransaction().add(R.id.new_complaint_container, new NewComplaint(),"my").addToBackStack(null).commit();
            }
        });
        firebaseDatabase=FirebaseDatabase.getInstance();

        databaseReference=firebaseDatabase.getReference().child("Complaints").child(firebaseAuth.getCurrentUser().getUid());
        valueEventListener=new ValueEventListener() {
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
                Refresh();
            }
        });

        adapter=new RecyclerAdapterComplaint(getContext(),listsPojo,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }
    public void Refresh(){
        databaseReference.removeEventListener(valueEventListener);
        valueEventListener=new ValueEventListener() {
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
        swipeRefreshLayout.setRefreshing(false);
    }
    public void Delete(String s){
        Query query=databaseReference.orderByChild("data").equalTo(s);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child:dataSnapshot.getChildren())
                {
                    child.getRef().removeValue();
                    Refresh();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
