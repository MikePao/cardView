package com.example.i329392.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by i329392 on 27/01/18.
 */

public class DisplayImageAdapter extends RecyclerView.Adapter<DisplayImageAdapter.DisplayViewHolder> {
    private List<CardData> displayList;
    Context context;
    public DisplayImageAdapter(List<CardData> displayList, Context context) {
        this.displayList = displayList;
        this.context = context;
    }

    @Override
    public DisplayImageAdapter.DisplayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new DisplayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DisplayImageAdapter.DisplayViewHolder holder, int position) {
        CardData data = displayList.get(position);
        Picasso.with(context).load(data.getPhotoUrl()).into(holder.photo);
    }

    @Override
    public int getItemCount() {
        if(displayList != null )
            return displayList.size();
        return 0;
    }

    public class DisplayViewHolder extends RecyclerView.ViewHolder{
        SquareImageView photo;
        public DisplayViewHolder(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo);
        }
    }
}
