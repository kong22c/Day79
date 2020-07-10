package com.example.day79;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.day79.adapter.VpAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar mTb;
    private ViewPager mVp;
    private TabLayout mTl;
    private LinearLayout mLl;
    private NavigationView mNv;
    private DrawerLayout mDl;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initLister();
        res();
    }

    private void initLister() {
        View headerView = mNv.getHeaderView(0);
        ImageView iv = headerView.findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "我想开学", Toast.LENGTH_SHORT).show();
            }
        });
        mDl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                int right = drawerView.getRight();
                mLl.setX(right);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
       mTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
           @Override
           public void onTabSelected(TabLayout.Tab tab) {
               mTb.setTitle(tab.getText());
           }

           @Override
           public void onTabUnselected(TabLayout.Tab tab) {

           }

           @Override
           public void onTabReselected(TabLayout.Tab tab) {

           }
       });
       mNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               switch (menuItem.getItemId()){
                   case R.id.item4:
                       Intent intent = new Intent();
                       intent.setData(Uri.parse("tel:10086"));
                       intent.setAction(Intent.ACTION_CALL);//直接拨出电话
                       startActivity(intent);

                       break;
               }
               return false;
           }
       });
    }

    private void initViews() {
        mTb = findViewById(R.id.tb);
        mVp = findViewById(R.id.vp);
        mTl = findViewById(R.id.tl);
        mLl = findViewById(R.id.ll);
        mNv = findViewById(R.id.nv);
        mDl = findViewById(R.id.dl);
        setSupportActionBar(mTb);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mTb, R.string.app_name, R.string.app_name);
        mDl.addDrawerListener(toggle);
        toggle.syncState();
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new KnownFragment());
        fragments.add(new NavFragment());
        VpAdapter adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        mVp.setAdapter(adapter);
        mTl.setupWithViewPager(mVp);
        mTl.getTabAt(0).setText("首页").setIcon(R.drawable.select1);
        mTl.getTabAt(1).setIcon(R.drawable.select2).setText("知识体");
        mTl.getTabAt(2).setText("导航").setIcon(R.drawable.select4);
        mTb.setTitle("");

    }
    public void res(){
        String[] permiss = {
                Manifest.permission.CALL_PHONE};
        //简单处理  ,直接申请
        ActivityCompat.requestPermissions(this, permiss, 100);
    }


}
