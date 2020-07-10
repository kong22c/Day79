package com.example.day79.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day79.R;
import com.example.day79.bean.BandBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class BandAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<BandBean.DataBean> list;
    private int VIew_TYPE_ONE = 1;
    private int VIEW_TYPE_TWO = 2;

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIew_TYPE_ONE;
        } else {
            return VIEW_TYPE_TWO;
        }
    }

    public BandAdapter(Context context, ArrayList<BandBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIew_TYPE_ONE) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_band, null);
            return new ViewHolder1(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_band1, null);
            return new ViewHolder2(inflate);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType == VIew_TYPE_ONE) {
            ArrayList<String> imgs = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                imgs.add(list.get(i).getImagePath());
            }
            ViewHolder1 holder1 = (ViewHolder1) holder;
            holder1.mBanner
                    .setImageLoader(new LoadImg())
                    .setImages(imgs)
                    .start();
        }else {
            ViewHolder2 holder2= (ViewHolder2) holder;
            Glide.with(context).load(list.get(position-1).getImagePath()).into(holder2.mIvPic);
            holder2.mTvDesc.setText(list.get(position-1).getDesc());
            holder2.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickLister.onItemClick(position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public static class ViewHolder1 extends RecyclerView.ViewHolder {
        Banner mBanner;


        public ViewHolder1(View inflate) {
            super(inflate);
            this.mBanner = itemView.findViewById(R.id.banner);

        }
    }

    public class LoadImg extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }


    private class ViewHolder2 extends RecyclerView.ViewHolder {
        ImageView mIvPic;
        TextView mTvDesc;
        public ViewHolder2(View inflate) {
            super(inflate);
            this.mIvPic = itemView.findViewById(R.id.iv_pic);
            this.mTvDesc = itemView.findViewById(R.id.tv_desc);
        }
    }
    private OnItemClickLister onItemClickLister;

    public void setOnItemClickLister(OnItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }

    public interface OnItemClickLister{
        void onItemClick(int postion);
    }
}
