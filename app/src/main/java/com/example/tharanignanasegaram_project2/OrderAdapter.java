package com.example.tharanignanasegaram_project2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    List<MenuCart> cardMenus = new ArrayList<>();
    Context mcontext;
    LayoutInflater inflater;


    public OrderAdapter(OrderActivity context, @NonNull List<MenuCart> productMenus) {
        mcontext = context;
        cardMenus = productMenus;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mcontext).inflate(R.layout.orders_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MenuCart cardMenu = cardMenus.get(position);

        Double unitPrice = Math.round(cardMenu.getPrice() * 100.0)/100.0;
        int quantity = cardMenu.getCountItem();

        holder.orderName.setText(cardMenu.getMenuName());
        holder.orderPrice.setText("$" + String.valueOf(unitPrice*quantity));
        holder.orderCount.setText(mcontext.getString(R.string.quantity)+Integer.toString(quantity));
        Picasso.with(mcontext).load(cardMenu.getMenuImage()).into(holder.orderImg);

    }

    @Override
    public int getItemCount() {
        return cardMenus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public View view;
        TextView orderName;
        ImageView orderImg;
        TextView orderPrice;
        TextView orderCount;

        public ViewHolder(@NotNull View itemView){
            super(itemView);
            view = itemView;
            orderName = itemView.findViewById(R.id.orderName);
            orderImg = itemView.findViewById(R.id.orderImage);
            orderPrice = itemView.findViewById(R.id.orderPrice);
            orderCount = itemView.findViewById(R.id.orderCount);

        }

    }
}
