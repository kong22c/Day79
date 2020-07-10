package com.example.day79.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

public abstract class BaseFragment<P extends BasePreseneter> extends Fragment implements BaseView{
    public P mPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayout(),null);
        ButterKnife.bind(this,inflate);
        initPresenter();
        if (mPresenter!=null){
            mPresenter.bindView(this);
        }
        initView();
        initData();
        return inflate;
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initPresenter();

    protected abstract int getLayout();


}
