package com.example.todoapptest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.todoapptest.Adapter.PagerAdapter;
import com.example.todoapptest.Fragment.CalendarFragment;
import com.example.todoapptest.Fragment.ListFragment;
import com.example.todoapptest.Fragment.SettingFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setViewPager(ViewPager viewPager) {
        adapter.addFragmentList(new ListFragment(), "리스트");
        adapter.addFragmentList(new CalendarFragment(), "달력");
        adapter.addFragmentList(new SettingFragment(), "설정");
        viewPager.setAdapter(adapter);
    }
}

