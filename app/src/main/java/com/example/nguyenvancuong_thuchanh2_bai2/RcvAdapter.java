package com.example.nguyenvancuong_thuchanh2_bai2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyenvancuong_thuchanh2_bai2.model.Quyengop;

import java.util.List;

public class RcvAdapter extends RecyclerView.Adapter<RcvAdapter.Holder> {
    private Activity activity;
    private SqlOpenHelp db;
    private Context mContext;
    private List<Quyengop> list;
    public RcvAdapter(Activity activity, List<Quyengop> list,SqlOpenHelp db){
        this.activity = activity;
        this.db = db;
        this.list = list;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new Holder(activity.getLayoutInflater().inflate(R.layout.item_rcv,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull RcvAdapter.Holder holder, int position) {
        Quyengop m = list.get(position);
        holder.date.setText(m.getDate());
        holder.thanhpho.setText(String.valueOf(m.getThanhpho()));
        holder.id.setText(""+m.getId());
        holder.name.setText(m.getName());
        holder.sotien.setText(""+m.getSotien());
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,UpdateActivity.class);
                intent.putExtra("Quyengop",m);
                ((Activity)mContext).startActivityForResult(intent,22);

            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete(m.getId());
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    class Holder extends RecyclerView.ViewHolder{
        TextView id,name,sotien,thanhpho,date;
        Button btnDelete, btnUpdate;
        public Holder(@NonNull View v) {
            super(v);
            id =v.findViewById(R.id.id);
            name = v.findViewById(R.id.name);
            sotien = v.findViewById(R.id.sotien);
            thanhpho = v.findViewById(R.id.thanhpho);
            date = v.findViewById(R.id.date);
            btnDelete = v.findViewById(R.id.btnDelete);
            btnUpdate = v.findViewById(R.id.btnUpdate);
        }
    }
    public double getSotien(){
        double sum = 0;
        for(Quyengop i :list){
            sum+=i.getSotien();
        }
        return sum;
    }
    public void setList(List<Quyengop> l){
        this.list = l;
    }
}