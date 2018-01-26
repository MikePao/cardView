package com.example.i329392.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i329392 on 26/01/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<CardData> mList = new ArrayList<CardData>();
    private Context context;
    public MyAdapter(List<CardData> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        CardData card = mList.get(position);
//        holder.text.setText(card.getText());
        Picasso.with(context).load(card.getPhotoUrl()).into(holder.photo);
    }

    @Override
    public int getItemCount() {
        if( mList != null )
            return mList.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        ImageView photo;
        SquareImageView photo;
//        TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo);
//            photo.getLayoutParams().height = photo.getMeasuredWidth();
//            text = itemView.findViewById(R.id.description);
        }
    }

    public void clear(){
        if(mList != null)
            mList.clear();
    }

    public void addAll(List<CardData> data) {
        mList=data;
    }

    public List<CardData> getmList() {
        return mList;
    }

    public void setmList(List<CardData> mList) {
        this.mList = mList;
    }
}
