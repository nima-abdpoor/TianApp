package com.tinaio.tianapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.tinaio.tianapp.DataBase.DataBaseHelper;
import com.tinaio.tianapp.Services.MyServicesFragment;
import com.tinaio.tianapp.Services.ServiceBuilder;

import java.util.ArrayList;
import java.util.List;


public class second extends AppCompatActivity {
    List<Fragment> fragmentList;
    ViewPager viewPager;
    MyPagerAdapter adapter;
    private int servicecountcheck = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        InitViewItems();
        //AddService();
        ArrayList<String> id = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        servicecountcheck = dataBaseHelper.GetServicesCount();
        for (int i=0;i<servicecountcheck;++i){
            id.add(dataBaseHelper.GetServices().get(0).get(i));
            name.add(dataBaseHelper.GetServices().get(1).get(i));
        }
        fragmentList = new ArrayList<>();
        MyServicesFragment fragment = MyServicesFragment.NewInstance(name, id);
        fragmentList.add(fragment);
        adapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
    }

    private void AddService() {
        ServiceBuilder serviceBuilder = new ServiceBuilder();
        serviceBuilder.addService(this, "Attendance", ServiceBuilder.Attendance);
        serviceBuilder.addService(this, "FoodReservation", ServiceBuilder.FoodReservation);
        serviceBuilder.addService(this, "Hotel", ServiceBuilder.Hotel);
        serviceBuilder.addService(this, "Book", ServiceBuilder.Book);
    }

    private void InitViewItems() {
        viewPager = findViewById(R.id.view_pager);
    }


}

class MyPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments;

    public MyPagerAdapter(FragmentManager fm, List<Fragment> frag) {
        super(fm);
        this.fragments = frag;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
