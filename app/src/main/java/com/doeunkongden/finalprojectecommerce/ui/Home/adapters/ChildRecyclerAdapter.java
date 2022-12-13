package com.doeunkongden.finalprojectecommerce.ui.Home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doeunkongden.finalprojectecommerce.R;
import com.doeunkongden.finalprojectecommerce.ui.Home.model.ChildModelClass;

import java.util.List;

public class ChildRecyclerAdapter extends RecyclerView.Adapter<ChildRecyclerAdapter.ChildViewHolder> {

    Context context;
    List<ChildModelClass> childModelClassList;

    public ChildRecyclerAdapter(Context context, List<ChildModelClass> childModelClassList) {
        this.context = context;
        this.childModelClassList = childModelClassList;
    }

    @NonNull
    @Override
    public ChildRecyclerAdapter.ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.child_recycler_item,parent,false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildRecyclerAdapter.ChildViewHolder holder, int position) {
        holder.product_title.setText(childModelClassList.get(position).getProduct_title());
        holder.product_price.setText(childModelClassList.get(position).getProduct_price());
    }

    @Override
    public int getItemCount() {
        return childModelClassList.size();
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        ImageView product_image;
        TextView product_title,product_price;

        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);

            product_title = itemView.findViewById(R.id.et_update_ttile);
            product_price = itemView.findViewById(R.id.product_price);
            product_image = itemView.findViewById(R.id.product_image);
        }
    }
}
