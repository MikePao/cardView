package com.example.i329392.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by i329392 on 27/01/18.
 */

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.TextViewHolder> {
    private List<TextData> textList;
    Context context;

    public TextAdapter(List<TextData> textList, Context context) {
        this.textList = textList;
        this.context = context;
    }


    @Override
    public TextAdapter.TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_window, parent, false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TextAdapter.TextViewHolder holder, int position) {
        TextData data = textList.get(position);
        if(data.getTitle() != null)
        {
            holder.titleView.setText(data.getTitle());
        } else {
            holder.titleView.setVisibility(View.GONE);
        }

        if(data.getText() != null)
        {
            holder.textWindow.setText(data.getText());
        } else {
            holder.textWindow.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if(textList != null)
            return textList.size();
        return 0;
    }

    public class TextViewHolder extends RecyclerView.ViewHolder{
        TextView titleView;
        TextView textWindow;
        public TextViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.title);
            textWindow = itemView.findViewById(R.id.textWindow);
        }
    }
}
