package com.example.day79.model;

import com.example.day79.base.BaseModel;
import com.example.day79.bean.BandBean;
import com.example.day79.net.ApiService;
import com.example.day79.net.BandCallBack;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BandModel extends BaseModel {
    public void getData(BandCallBack<BandBean>callBack){
        ResourceSubscriber<BandBean> resourceSubscriber = new Retrofit.Builder()
                .baseUrl(ApiService.BASS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getBand()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<BandBean>() {
                    @Override
                    public void onNext(BandBean bandBean) {
                        callBack.onSucess(bandBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        callBack.onFain(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        addDisposable(resourceSubscriber);
    }
}
