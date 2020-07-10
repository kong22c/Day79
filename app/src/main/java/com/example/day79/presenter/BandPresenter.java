package com.example.day79.presenter;

import com.example.day79.base.BasePreseneter;
import com.example.day79.bean.BandBean;
import com.example.day79.model.BandModel;
import com.example.day79.net.BandCallBack;
import com.example.day79.view.BandView;

public class BandPresenter extends BasePreseneter<BandView> {

    private BandModel bandModel;


    public void getData(){
        bandModel.getData(new BandCallBack<BandBean>() {
            @Override
            public void onSucess(BandBean bandBean) {
                mView.setData(bandBean);
            }

            @Override
            public void onFain(String str) {
mView.showToast(str);
            }
        });
    }

    @Override
    protected void initModel() {
        bandModel = new BandModel();
        addModel(bandModel);
    }
}
