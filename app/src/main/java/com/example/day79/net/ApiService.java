package com.example.day79.net;

import com.example.day79.bean.BandBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiService {
    String BASS="https://www.wanandroid.com/";
    @GET("banner/json")
    Flowable<BandBean>getBand();
}
