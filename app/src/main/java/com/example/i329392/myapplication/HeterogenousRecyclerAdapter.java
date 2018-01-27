package com.example.i329392.myapplication;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i329392 on 27/01/18.
 */

public class HeterogenousRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Object> totalData;
    List<CardData> displayList = new ArrayList<CardData>();
    List<TextData> textList = new ArrayList<TextData>();
    private static final int PHOTO = 1;
    private static  final int TEXT = 2;
    Context context;
    DisplayImageAdapter mDisplayImageAdapter;
    TextAdapter mTextAdapter;

    public HeterogenousRecyclerAdapter(List<Object> totalData,List<CardData> displayList, List<TextData> textList, Context context) {
        this.totalData = totalData;
        this.displayList = displayList;
        this.textList = textList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType){
            case PHOTO :
                view = inflater.inflate(R.layout.photo_horizontal_list, parent, false);
                holder =  new PhotoViewholder(view);
                break;
            case TEXT :
                view = inflater.inflate(R.layout.text_vertical_list, parent, false);
                holder = new TextViewholder(view);
                break;
            default:
                view = inflater.inflate(R.layout.text_vertical_list, parent, false);
                holder = new TextViewholder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == PHOTO)
            photoView((PhotoViewholder) holder);
        else if (holder.getItemViewType() == TEXT)
            TextWindowView((TextViewholder) holder);
    }

    private void photoView(PhotoViewholder holder) {
        DisplayImageAdapter adapter1 = new DisplayImageAdapter(Singleton.getPhototList(), context);
        holder.photoRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.photoRecyclerView.setAdapter(adapter1);
    }

    private void TextWindowView(TextViewholder holder) {
        TextAdapter adapter2 = new TextAdapter(Singleton.getTextList(), context);
        holder.textRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.textRecyclerView.setAdapter(adapter2);
    }
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//        switch (holder.getItemViewType()){
//            case PHOTO :
//                mDisplayImageAdapter = new DisplayImageAdapter(displayList, context);
//                ((PhotoViewholder)holder).photoRecyclerView.setLayoutManager(new LinearLayoutManager(context));
//                ((PhotoViewholder)holder).photoRecyclerView.setAdapter(mDisplayImageAdapter);
//                break;
//            case TEXT :
//                 mTextAdapter = new TextAdapter(textList, context);
//                ((TextViewholder)holder).textRecyclerView.setLayoutManager(new LinearLayoutManager(context));
//                ((TextViewholder)holder).textRecyclerView.setAdapter(mTextAdapter);
//                break;
//            default:
//                break;
//
//        }
//    }

    @Override
    public int getItemViewType(int position) {
        if(totalData != null) {
            Object object = totalData.get(position);
            if(object instanceof CardData){
//            displayList.add((CardData)totalData.get(position));
                return PHOTO;
            }
            if(object  instanceof  TextData){
//            textList.add((TextData) totalData.get(position));
                return TEXT;
            }


        }


        return -1;
    }

    @Override
    public int getItemCount() {
        if( totalData != null)
            return totalData.size();
        return 0;
    }

    public class PhotoViewholder extends RecyclerView.ViewHolder {
        RecyclerView photoRecyclerView;
        public PhotoViewholder(View itemView) {
            super(itemView);
            photoRecyclerView = itemView.findViewById(R.id.photo_recycler);
        }
    }

    public class TextViewholder extends RecyclerView.ViewHolder {
        RecyclerView textRecyclerView;
        public TextViewholder(View itemView) {
            super(itemView);
            textRecyclerView = itemView.findViewById(R.id.text_recycler);
        }
    }

    public void clear() {
        if( totalData != null) {
            totalData.clear();
        }
    }

    public void addData(Object data){
        totalData.add(data);
    }

    public void addAll(List<CardData> dataList){
        totalData.addAll(dataList);
    }


    public List<CardData> getDisplayList() {
        return displayList;
    }

    public void setDisplayList(List<CardData> displayList) {
        this.displayList = displayList;
//        if(mDisplayImageAdapter != null ){
//            mDisplayImageAdapter.notifyDataSetChanged();
//        }

    }

    public List<TextData> getTextList() {
        return textList;
    }

    public void setTextList(List<TextData> textList) {
        this.textList = textList;
//        mTextAdapter.notifyDataSetChanged();
//        if(mTextAdapter != null ){
//            mTextAdapter.notifyDataSetChanged();
//        }
    }

    public List<Object> getTotalData() {
        return totalData;
    }

    public void setTotalData(List<Object> totalData) {
        this.totalData = totalData;
        notifyDataSetChanged();
    }
}
