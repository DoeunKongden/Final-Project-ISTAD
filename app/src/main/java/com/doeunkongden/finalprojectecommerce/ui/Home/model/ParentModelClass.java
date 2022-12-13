package com.doeunkongden.finalprojectecommerce.ui.Home.model;

import java.util.List;

public class ParentModelClass {

    String parent_rv_title;
    List<ChildModelClass> childModelClassList;

    //Constructor
    public ParentModelClass(String parent_rv_title, List<ChildModelClass> childModelClassList) {
        this.parent_rv_title = parent_rv_title;
        this.childModelClassList = childModelClassList;
    }


    //Getter and Setter
    public String getParent_rv_title() {
        return parent_rv_title;
    }

    public void setParent_rv_title(String parent_rv_title) {
        this.parent_rv_title = parent_rv_title;
    }

    public List<ChildModelClass> getChildModelClassList() {
        return childModelClassList;
    }

    public void setChildModelClassList(List<ChildModelClass> childModelClassList) {
        this.childModelClassList = childModelClassList;
    }
}
