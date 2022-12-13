package com.doeunkongden.finalprojectecommerce.ui.Home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doeunkongden.finalprojectecommerce.R;
import com.doeunkongden.finalprojectecommerce.ui.Home.model.ChildModelClass;
import com.doeunkongden.finalprojectecommerce.ui.Home.model.ParentModelClass;

import java.util.List;

public class ParentRecyclerAdapter extends RecyclerView.Adapter<ParentRecyclerAdapter.ParentViewHolder> {

    Context context;
    List<ParentModelClass> parentModelClassList;

    public ParentRecyclerAdapter(Context context, List<ParentModelClass> parentModelClassList) {
        this.context = context;
        this.parentModelClassList = parentModelClassList;
    }

    @NonNull
    @Override
    public ParentRecyclerAdapter.ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.parent_recycler_item,parent,false);

        return new ParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentRecyclerAdapter.ParentViewHolder holder, int position) {
        holder.parent_tv_title.setText(parentModelClassList.get(position).getParent_rv_title());
        setChildItemRecycler(holder.child_recyclerView,parentModelClassList.get(position).getChildModelClassList());
    }

    @Override
    public int getItemCount() {
        return parentModelClassList.size();
    }

    public class ParentViewHolder extends RecyclerView.ViewHolder {
        TextView parent_tv_title;
        RecyclerView child_recyclerView;
        public ParentViewHolder(@NonNull View itemView) {
            super(itemView);
            parent_tv_title = itemView.findViewById(R.id.rv_tv_title);
            child_recyclerView = itemView.findViewById(R.id.rv_child);
        }
    }

    private void setChildItemRecycler(RecyclerView recyclerView, List<ChildModelClass> childModelClassList){
        ChildRecyclerAdapter childRecyclerAdapter = new ChildRecyclerAdapter(context,childModelClassList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        recyclerView.setAdapter(childRecyclerAdapter);

    }
}
