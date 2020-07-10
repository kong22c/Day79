package com.example.day79.base;

import java.util.ArrayList;

public abstract class BasePreseneter <V extends BaseView> {
    public V mView;
    private ArrayList<BaseModel>baseModels;
    public BasePreseneter(){
        initModel();
    }
    public void addModel(BaseModel baseModel){
        if (baseModels==null)
            baseModels=new ArrayList<>();
        baseModels.add(baseModel);
    }

    protected abstract void initModel();
    public void bindView(V view){
        mView=view;
    }

}
