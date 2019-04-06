package com.m.jasper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.chip.Chip;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
    private Context context;
    private ArrayList<Pojo> listsPojo;
    @NonNull
    @Override
    public RecyclerAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.rv_notifications,viewGroup,false);
        Holder holder=new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.Holder holder, int i) {
    holder.textView.setText(listsPojo.get(i).getData());
    holder.descrip.setText(listsPojo.get(i).getDescrp());
        }
    public RecyclerAdapter(Context context,ArrayList<Pojo> listsPojo){
        this.context=context;
        this.listsPojo=listsPojo;

    }

    @Override
    public int getItemCount() {
        return listsPojo.size();
        }

    public class Holder extends RecyclerView.ViewHolder {
        TextView descrip;
        private TextView textView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv_rv_title);
            descrip=itemView.findViewById(R.id.tv_rv_description);

        }
    }
}
