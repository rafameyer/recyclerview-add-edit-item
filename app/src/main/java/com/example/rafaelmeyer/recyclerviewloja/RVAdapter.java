package com.example.rafaelmeyer.recyclerviewloja;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import model.Item;

/**
 * Created by rafael.meyer on 8/24/16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    public List<Item> items;
    public RecyclerViewOnClickListener myRecyclerViewOnClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        public CardView cardViewItem;
        public TextView textViewName;
        public View viewColor;

        public ViewHolder(View itemView) {
            super(itemView);
            cardViewItem = (CardView)itemView.findViewById(R.id.cardViewItem);
            textViewName = (TextView)itemView.findViewById(R.id.nomeItem);
            viewColor = itemView.findViewById(R.id.viewColor);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            if (myRecyclerViewOnClickListener != null) {
                Toast.makeText(itemView.getContext(), textViewName.getText(), Toast.LENGTH_SHORT).show();
                myRecyclerViewOnClickListener.onClickListener(v, getAdapterPosition());
            }
        }
    }

    public RVAdapter(List<Item> items) {
        this.items = items;
    }

    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardviewitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewName.setText(items.get(position).getName());
        int colorViewItem = Color.parseColor(items.get(position).getColor());
        holder.viewColor.setBackgroundColor(colorViewItem);
    }

    @Override
    public int getItemCount() {
        if (items == null){
            return 0;
        }
        return items.size();
    }

    public interface RecyclerViewOnClickListener {
        void onClickListener(View view, int position);
    }

    public void setMyRecyclerViewOnClickListener(RecyclerViewOnClickListener myRecyclerViewOnClickListener) {
        this.myRecyclerViewOnClickListener = myRecyclerViewOnClickListener;
    }

}
