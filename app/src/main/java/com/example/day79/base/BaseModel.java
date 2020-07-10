package com.example.day79.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseModel {
    private CompositeDisposable compositeDisposable=null;
        public void addDisposable(Disposable disposable){
            if (compositeDisposable==null){
                compositeDisposable=new CompositeDisposable();
            }
            compositeDisposable.add(disposable);
        }
}
