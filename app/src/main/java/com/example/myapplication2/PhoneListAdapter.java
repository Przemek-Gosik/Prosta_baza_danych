package com.example.myapplication2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneListAdapter extends RecyclerView.Adapter<PhoneListAdapter.PhoneViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<Phone> mPhoneList;
    private OnItemClickListener onItemClickListener;


    public PhoneListAdapter(Context context,OnItemClickListener onItemClickListener){
        mLayoutInflater = LayoutInflater.from(context);
        this.mPhoneList=null;
        this.onItemClickListener=onItemClickListener;



    }

    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View wiersz = mLayoutInflater.inflate(R.layout.wiersz,parent,false);
        return new PhoneViewHolder(wiersz,onItemClickListener);
    }
    public class PhoneViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView1,textView4;
        public OnItemClickListener onItemClickListener;


        public TextView getTextView4() {
            return textView4;
        }



        public PhoneViewHolder(@NonNull View elementWiersza,OnItemClickListener onItemClickListener){
            super(elementWiersza);
            this.onItemClickListener=onItemClickListener;
            textView1=(TextView) elementWiersza.findViewById(R.id.textView);
            textView4=(TextView) elementWiersza.findViewById(R.id.textView4);
            elementWiersza.setOnClickListener(this);
        }




        public TextView getTextView1(){
            return textView1;
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemCLickListener(getAdapterPosition());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneListAdapter.PhoneViewHolder holder, int position) {
        Phone phone = (Phone) mPhoneList.get(position);
        holder.getTextView1().setText(phone.getMKolumna2());

        holder.getTextView4().setText(phone.getMKolumna4());

    }

    @Override
    public int getItemCount(){
        if(mPhoneList !=null)
            return mPhoneList.size();
        return 0;

    }
    public void setPhoneList(List<Phone> phoneList){
        this.mPhoneList = phoneList;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener{
        void onItemCLickListener(int position);
    }


    }

