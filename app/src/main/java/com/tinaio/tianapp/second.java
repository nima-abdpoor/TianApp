package com.tinaio.tianapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.tinaio.tianapp.Services.MyServicesFragment;

import java.util.ArrayList;
import java.util.List;


public class second extends AppCompatActivity {
    List<Fragment> fragmentList;
    ViewPager viewPager;
    MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        InitViewItems();
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> id = new ArrayList<>();
        list.add("servic1");
        list.add("servic2");
        list.add("servic3");
        list.add("servic4");
        list.add("servic5");
        list.add("servic5\nfasaf");
        list.add("servic5\nfasaf");
        list.add("servic5\nfasaf");
        list.add("servic5\nfasaf");
        fragmentList = new ArrayList<>();
        MyServicesFragment fragment = MyServicesFragment.NewInstance(list, id);
        MyServicesFragment fragment2 = MyServicesFragment.NewInstance(list, id);
        fragmentList.add(fragment);
        fragmentList.add(fragment2);
        adapter=new MyPagerAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
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
