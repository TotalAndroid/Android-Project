package com.example.himel.recyclerview.CustomAdapterClass;

import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.himel.recyclerview.ModelClass.Item;
import com.example.himel.recyclerview.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HIMEL on 2/19/2018.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context context;
    private ArrayList<Item>items;

    public ItemAdapter(Context context, ArrayList<Item>itemArrayList){
        this.context =context;
        this.items=itemArrayList;


    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      View view= inflater.from(parent.getContext()).inflate(R.layout.item_cardview_layout, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = items.get(position);
        Picasso.with(context)
                .load(item.image)
                .placeholder(R.drawable.d)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.ivImg);
        holder.tvText.setText(item.nameImage);

    }

    @Override
    public int getItemCount() {


        if (items !=null) {
            return items.size();
        }
        return 0;
    }


    //viewholder class
    public static  class ItemViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public ImageView ivImg;
        public TextView tvText;


      public ItemViewHolder(View itemView) {
          super(itemView);
          cardView = itemView.findViewById(R.id.cvItem);
          ivImg = itemView.findViewById(R.id.ivImg);
          tvText = itemView.findViewById(R.id.tvText);
      }
  }


}
