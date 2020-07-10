package com.example.day79.net;

public interface BandCallBack<T> {
    void onSucess(T t);
    void onFain(String str);
}
