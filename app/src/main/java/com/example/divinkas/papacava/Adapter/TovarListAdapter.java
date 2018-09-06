package com.example.divinkas.papacava.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.divinkas.papacava.Data.Product;
import com.example.divinkas.papacava.R;

import java.util.List;

public class TovarListAdapter extends RecyclerView.Adapter<TovarListAdapter.ViewHolder> {
    private final static int TYPE_VIEW_ONE = 0;
    private final static int TYPE_VIEW_TWO = 1;

    private LayoutInflater inflater;
    private Context context;
    public List<Product> productList;

    public TovarListAdapter(Context context, List<Product> products) {
        this.context = context;
        productList = products;
        inflater = LayoutInflater.from(this.context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case TYPE_VIEW_TWO:
                view = inflater.inflate(R.layout.fragment_last_zakaz, parent, false);
                break;
            default:
                view = inflater.inflate(R.layout.fragment_tovar_item, parent, false);
                break;
        }
        return new ViewHolder(view, viewType);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position != 1 && productList.get(position) != null) {
            holder.setIsRecyclable(false);
            //setHasStableIds(true);

            holder.tovName.setText(productList.get(position).getName());
            holder.tovPrice.setText(String.valueOf(productList.get(position).getPrice()) + "грн.");
            Glide.with(context).load(productList.get(position).getImage_url()).into(holder.tovImage);
            if (!productList.get(position).getFirst_marker().isEmpty()) {
                holder.saleOrange.setText(productList.get(position).getFirst_marker());
                holder.saleOrange.setVisibility(View.VISIBLE);

            }
            else {holder.saleOrange.setHeight(0);}
            if (!productList.get(position).getSecond_marker().isEmpty()) {
                holder.saleRed.setText(productList.get(position).getSecond_marker());
                holder.saleRed.setVisibility(View.VISIBLE);
            }else { holder.saleRed.setHeight(0);}
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 1 || productList.get(position) == null){
                return TYPE_VIEW_TWO;
        }
        else{
            return TYPE_VIEW_ONE;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tovName;
        TextView tovPrice;
        ImageView tovImage;
        TextView saleOrange;
        TextView saleRed;

        @SuppressLint("CutPasteId")
        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            if(viewType ==TYPE_VIEW_ONE) {
                tovName = itemView.findViewById(R.id.tovarItemName);
                tovPrice = itemView.findViewById(R.id.tovarItemPrice);
                tovImage = itemView.findViewById(R.id.tovarItemImage);
                saleOrange = itemView.findViewById(R.id.tvSaleOrange);
                saleRed = itemView.findViewById(R.id.tvSaleRed);
            }
        }
    }
}
