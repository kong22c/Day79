package com.example.day79;


import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.day79.adapter.BandAdapter;
import com.example.day79.base.BaseFragment;
import com.example.day79.bean.BandBean;
import com.example.day79.presenter.BandPresenter;
import com.example.day79.view.BandView;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<BandPresenter> implements BandView {

    @BindView(R.id.rv)
    RecyclerView rv;
    private ArrayList<BandBean.DataBean> list;
    private BandAdapter adapter;

    @Override
    protected void initData() {
mPresenter.getData();
    }

    @Override
    protected void initView() {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        adapter = new BandAdapter(getActivity(), list);
        rv.setAdapter(adapter);
        adapter.setOnItemClickLister(new BandAdapter.OnItemClickLister() {
            @Override
            public void onItemClick(int postion) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url",list.get(postion-1).getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initPresenter() {
        mPresenter = new BandPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void setData(BandBean bandBean) {
            list.addAll(bandBean.getData());
            adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {

    }
}
