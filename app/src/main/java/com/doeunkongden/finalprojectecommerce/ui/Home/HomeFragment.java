package com.doeunkongden.finalprojectecommerce.ui.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doeunkongden.finalprojectecommerce.R;
import com.doeunkongden.finalprojectecommerce.data.model.api.ProductAttributes;
import com.doeunkongden.finalprojectecommerce.ui.AddProduct.AddProductActivity;
import com.doeunkongden.finalprojectecommerce.ui.Home.adapters.ParentRecyclerAdapter;
import com.doeunkongden.finalprojectecommerce.ui.Home.model.ChildModelClass;
import com.doeunkongden.finalprojectecommerce.ui.Home.model.ParentModelClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    FloatingActionButton addButton;
    RecyclerView parent_RecyclerView;
    ParentRecyclerAdapter parentRecyclerAdapter;
    List<ParentModelClass> parentModelClassList;
    ParentModelClass parentModelClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view,parentModelClassList);
        initEvent(view);

        //setting data to the model class
        ProductAttributes productAttributes = new ProductAttributes();
        List<ParentModelClass> parentModelClassList = new ArrayList<>();

        // second step : Add data to the child Recyclerview
        List<ChildModelClass> childModelClassList = new ArrayList<>();
        childModelClassList.add(new ChildModelClass("Adidas","30" , 1));
        childModelClassList.add(new ChildModelClass("Puma","40" , 12));
        childModelClassList.add(new ChildModelClass("Gucci","90" , 2));
        childModelClassList.add(new ChildModelClass("LV","10" , 4));
        childModelClassList.add(new ChildModelClass("Crocodile","60" , 6));

        // second step : Add data to the child Recyclerview
        List<ChildModelClass> childModelClassList2 = new ArrayList<>();
        childModelClassList2.add(new ChildModelClass("Adidas","30" , 1));
        childModelClassList2.add(new ChildModelClass("Puma","40" , 12));
        childModelClassList2.add(new ChildModelClass("Gucci","90" , 2));
        childModelClassList2.add(new ChildModelClass("LV","10" , 4));
        childModelClassList2.add(new ChildModelClass("Crocodile","60" , 6));

        // second step : Add data to the child Recyclerview
        List<ChildModelClass> childModelClassList3 = new ArrayList<>();
        childModelClassList3.add(new ChildModelClass("Adidas","30" , 1));
        childModelClassList3.add(new ChildModelClass("Puma","40" , 12));
        childModelClassList3.add(new ChildModelClass("Gucci","90" , 2));
        childModelClassList3.add(new ChildModelClass("LV","10" , 4));
        childModelClassList3.add(new ChildModelClass("Crocodile","60" , 6));

        // second step : Add data to the child Recyclerview
        List<ChildModelClass> childModelClassList4 = new ArrayList<>();
        childModelClassList4.add(new ChildModelClass("Adidas","30" , 1));
        childModelClassList4.add(new ChildModelClass("Puma","40" , 12));
        childModelClassList4.add(new ChildModelClass("Gucci","90" , 2));
        childModelClassList4.add(new ChildModelClass("LV","10" , 4));
        childModelClassList4.add(new ChildModelClass("Crocodile","60" , 6));

        // second step : Add data to the child Recyclerview
        List<ChildModelClass> childModelClassList5 = new ArrayList<>();
        childModelClassList5.add(new ChildModelClass("Adidas","30" , 1));
        childModelClassList5.add(new ChildModelClass("Puma","40" , 12));
        childModelClassList5.add(new ChildModelClass("Gucci","90" , 2));
        childModelClassList5.add(new ChildModelClass("LV","10" , 4));
        childModelClassList5.add(new ChildModelClass("Crocodile","60" , 6));

        parentModelClassList.add(new ParentModelClass("Shoes",childModelClassList));
        parentModelClassList.add(new ParentModelClass("Clothes",childModelClassList2));
        parentModelClassList.add(new ParentModelClass("Tech",childModelClassList3));
        parentModelClassList.add(new ParentModelClass("Food",childModelClassList4));
        parentModelClassList.add(new ParentModelClass("Accessories",childModelClassList5));

        setParentTitleRecycler(view,parentModelClassList);

        return view;
    }

    private void initEvent(View view){
        addButton.setOnClickListener(view1 -> {
            Intent intent = new Intent(view.getContext(), AddProductActivity.class);
            startActivity(intent);
        });
    }

    private void initView(View view, List<ParentModelClass> parentModelClassList) {
        addButton = view.findViewById(R.id.floatingActionAddButton);
        parent_RecyclerView = view.findViewById(R.id.rv_parent);
    }

    private void setParentTitleRecycler(View view ,List<ParentModelClass> parentModelClassList){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        parent_RecyclerView.setLayoutManager(layoutManager);
        parentRecyclerAdapter  = new ParentRecyclerAdapter(view.getContext(),parentModelClassList);
        parent_RecyclerView.setAdapter(parentRecyclerAdapter);
    }
}