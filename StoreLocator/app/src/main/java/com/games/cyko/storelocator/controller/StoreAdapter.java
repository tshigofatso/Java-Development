package com.games.cyko.storelocator.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.games.cyko.storelocator.R;
import com.games.cyko.storelocator.models.StoreItems;

import java.util.List;

/**
 * Created by Cyko on 9/29/2016.
 */

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreHolder> {


    private List<StoreItems> listData;
    private LayoutInflater inflater;


    public StoreAdapter(List<StoreItems> listData, Context context){
        this.inflater = LayoutInflater.from(context);
        this.listData = listData;
    }

    @Override
    public StoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.store_item, parent, false);


        return new StoreHolder(view);
    }

    @Override
    public void onBindViewHolder(StoreHolder holder, int position) {
        StoreItems item = listData.get(position);
        holder.title.setText(item.getStoreName());
        holder.subtitle.setText(item.getStoreArea());
        holder.icon.setImageResource(item.getImageResId());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class StoreHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView subtitle;
        private ImageView icon;
        private View container;

        public StoreHolder(View itemview){
            super(itemview);


            title = (TextView) itemview.findViewById(R.id.lbl_store_name);
            subtitle = (TextView)itemview.findViewById(R.id.lbl_store_area);
            icon = (ImageView) itemview.findViewById(R.id.img_icon);
            container = itemview.findViewById(R.id.con_item_root);
        }
    }



}
