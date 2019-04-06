package com.m.jasper.NavActivities.MessMenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.m.jasper.R;

import java.util.ArrayList;

public class RecyclerAdapterMess extends RecyclerView.Adapter<RecyclerAdapterMess.Holder> {

    ArrayList<MessPojo> list;
    Context context;
    @NonNull
    @Override
    public RecyclerAdapterMess.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.rv_mess,viewGroup,false);
        Holder holder=new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterMess.Holder holder, int i) {
        holder.day.setText(list.get(i).getDay());
    }

    public RecyclerAdapterMess(ArrayList<MessPojo> list,Context context){
    this.list=list;
    this.context=context;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView day;
        TextView breakFast;
        TextView dinner;
        TextView lunch;
        public Holder(@NonNull View itemView) {
            super(itemView);
            day=itemView.findViewById(R.id.tv_day);
            breakFast=itemView.findViewById(R.id.tv_rv_breakfast);
            lunch=itemView.findViewById(R.id.tv_rv_lunch);
            dinner=itemView.findViewById(R.id.tv_rv_dinner);
        }
    }
}
