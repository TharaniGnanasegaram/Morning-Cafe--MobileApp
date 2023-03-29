package com.example.tharanignanasegaram_project2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    List<String> titles = new ArrayList<>();
    List<String> images = new ArrayList<>();
    List<Double> prices = new ArrayList<>();
    List<Menu> menus = new ArrayList<>();
    List<Menu> cardMenus = new ArrayList<>();
    List<MenuCart> cartItems = new ArrayList<>();
    Context mcontext;
    LayoutInflater inflater;

    public MenuAdapter(Context context, List<Menu> productMenus) {
        mcontext = context;
        menus = productMenus;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mcontext).inflate(R.layout.product_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Menu currentMenu = menus.get(position);
        holder.title.setText(currentMenu.getMenuName());
        holder.price.setText("$" + String.valueOf(currentMenu.getPrice()));
        Picasso.with(mcontext).load(currentMenu.getMenuImage()).into(holder.image);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("TITLE", currentMenu.getMenuName());
            intent.putExtra("IMAGE", currentMenu.getMenuImage());
            intent.putExtra("PRICE", ("$" + String.valueOf(currentMenu.getPrice())));
            intent.putExtra("DESC", currentMenu.getMenuDescription());
            holder.itemView.getContext().startActivity(intent);
        });



        holder.incrButton.setOnClickListener(view -> {

            int currentCount = Integer.parseInt(holder.countProduct.getText().toString()) ;
            holder.countProduct.setText(Integer.toString(currentCount+1));

            if(cartItems.isEmpty()){
                    Log.i("info", "one");
                    MenuCart menuCart2 = new MenuCart(currentMenu.getMenuName(), currentMenu.getPrice(), currentMenu.getMenuImage(), (currentCount+1));
                    cartItems.add(menuCart2);
            }
            else{
                boolean isFound = false;
                for (int i = 0; i < cartItems.size(); i++) {
                    if (cartItems.get(i).getMenuName().equals(currentMenu.getMenuName())) {
                        isFound = true;
                        Log.i("info", "two");
                        cartItems.get(i).setCountItem(cartItems.get(i).getCountItem()+1);
                        break;
                    }
                    else {
                        isFound = false;
                    }
                }

                if(!isFound){
                    Log.i("info", "three");
                    MenuCart menuCart2 = new MenuCart(currentMenu.getMenuName(), currentMenu.getPrice(), currentMenu.getMenuImage(), (currentCount+1));
                    cartItems.add(menuCart2);
                }
            }

            Log.i("info", "Adding");
            Log.i("info", cartItems.toString());
            Toast.makeText(view.getContext(), "Added to cart", Toast.LENGTH_SHORT).show();


        });

        holder.decrButton.setOnClickListener(view -> {

            int currentCount = Integer.parseInt(holder.countProduct.getText().toString()) ;
            if(currentCount > 0){
                holder.countProduct.setText(Integer.toString(currentCount-1));

                if(!cartItems.isEmpty()){

                    for (int i = 0; i < cartItems.size(); i++) {
                        if (cartItems.get(i).getMenuName().equals(currentMenu.getMenuName())) {
                            if(cartItems.get(i).getCountItem()==1){
                                cartItems.remove(cartItems.get(i));
                            }else {
                                cartItems.get(i).setCountItem(cartItems.get(i).getCountItem()-1);
                            }
                        }
                    }

                }
            }
            Log.i("info", "Deleting");
            Log.i("info", cartItems.toString());
            Toast.makeText(view.getContext(), "Removed from cart", Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public View view;
        TextView title;
        ImageView image;
        TextView price;
        TextView countProduct;
        Button incrButton;
        Button decrButton;

        public ViewHolder(@NotNull View itemView){
            super(itemView);
            view = itemView;
            title = itemView.findViewById(R.id.txtMenuProdName);
            image = itemView.findViewById(R.id.imgMenuProdImage);
            price = itemView.findViewById(R.id.txtMenuProdPrice);
            countProduct = itemView.findViewById(R.id.txtCount);
            incrButton = itemView.findViewById(R.id.btnIncrease);
            decrButton = itemView.findViewById(R.id.btnDecrease);

        }

    }
}
