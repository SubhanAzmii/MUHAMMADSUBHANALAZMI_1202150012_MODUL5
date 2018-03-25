package com.example.android.muhammadsubhanalazmi_1202150012_modul5;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by Kizuna on 25-Mar-18.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    //daftar variable
    private LayoutInflater inflan;
    private LinkedList<todo> mlist;

    //konstruktor untuk RecycleAdapter dan mContext
    public RecycleAdapter(Context context, LinkedList<todo> list) {
        inflan = LayoutInflater.from(context);
        this.mlist = list;
    }


    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View showitem =  inflan.inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(showitem,this);
    }

    @Override
    public void onBindViewHolder(RecycleAdapter.ViewHolder holder, int position) {
        //set text pada view receycleview
        holder.mNamaView.setText(mlist.get(position).getNama());
        holder.mDeskripsiView.setText(mlist.get(position).getDeskripsi());
        holder.mPriorityView.setText(mlist.get(position).getPriority());
    }

    @Override
    //pengembalian data untuk mwater
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //daftar variable
        private TextView mNamaView, mDeskripsiView, mPriorityView;
        final RecycleAdapter mAdapter;

        public ViewHolder(View itemView, RecycleAdapter adapter) {
            super(itemView);
            //inisialisasi textview berdasarkan R.id
            mNamaView = (TextView) itemView.findViewById(R.id.name);
            mDeskripsiView = (TextView) itemView.findViewById(R.id.desc);
            mPriorityView = (TextView) itemView.findViewById(R.id.priority);
            this.mAdapter = adapter;
        }

    }
    //delete data
    public boolean deleteData(int del) {
        DatabaseHelper db = new DatabaseHelper(inflan.getContext());
        //delete content
        Boolean deleted = db.hapusTodo(mlist.get(del));
        if (deleted) {
            mlist.remove();
            this.notifyItemRemoved(del);
        } else {
            
        }return true;
    }

}