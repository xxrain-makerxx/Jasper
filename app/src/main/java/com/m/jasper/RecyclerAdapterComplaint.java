package com.m.jasper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.chip.Chip;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.m.jasper.NavActivities.Home.Complain;

import java.util.ArrayList;


public class RecyclerAdapterComplaint extends RecyclerView.Adapter<RecyclerAdapterComplaint.Holder> {
    private Context context;
    private ArrayList<Pojo> listsPojo;
    Complain complain;
    @NonNull
    @Override
    public RecyclerAdapterComplaint.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.rv_complaint,viewGroup,false);
        Holder holder=new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.textView.setText(listsPojo.get(i).getData());
        holder.descrip.setText(listsPojo.get(i).getDescrp());
        holder.chip.setTag(listsPojo.get(i).getData());
        holder.chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complain.Delete(v.getTag().toString());
            }
        });
    }

    public RecyclerAdapterComplaint(Context context,ArrayList<Pojo> listsPojo,Complain complain){
        this.context=context;
        this.listsPojo=listsPojo;
        this.complain=complain;
    }

    @Override
    public int getItemCount() {
        return listsPojo.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView descrip;
        Chip chip;
        private TextView textView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv_rv_title);
            descrip=itemView.findViewById(R.id.tv_rv_complaint_des);
            chip= itemView.findViewById(R.id.delete_complaint);
        }
    }
}
