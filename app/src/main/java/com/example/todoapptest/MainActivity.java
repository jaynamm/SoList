package com.example.todoapptest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.todoapptest.Adapter.PagerAdapter;
import com.example.todoapptest.Fragment.CalendarFragment;
import com.example.todoapptest.Fragment.CustomCalendarFragment;
import com.example.todoapptest.Fragment.HomeFragment;
import com.example.todoapptest.Fragment.ListFragment;
import com.example.todoapptest.Fragment.SettingFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // viewPager id 가져오기
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        // adapter 생성 후 연결
        adapter = new PagerAdapter(getSupportFragmentManager());
        setViewPager(viewPager);

        // tabLayout 에 tab icon 추가하기
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.baseline_home_black_18dp_2x));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.sharp_format_list_bulleted_black_18dp_2x));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.baseline_calendar_today_black_18dp_2x));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.baseline_send_black_18dp_2x));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.sharp_more_horiz_black_18dp_2x));

        // custom view 가져와서 tab 꾸미기
        // tabLayout.addTab(tabLayout.newTab().setCustomView());

        // view pager 의 페이지가 변경될 때 알려주는 리스너
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        // tab이 선택될 때 알려주는 리스너
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            // tab을 선택했을 때 position에 대한 fragment를 가져온다.
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void setViewPager(ViewPager viewPager) {
        adapter.addFragmentList(new HomeFragment());
        adapter.addFragmentList(new ListFragment());
        adapter.addFragmentList(new CalendarFragment());
        adapter.addFragmentList(new CustomCalendarFragment());
        adapter.addFragmentList(new SettingFragment());
        viewPager.setAdapter(adapter);
    }

    /* tab layout 을 직접 꾸밀 때 custom xml 파일을 가져와서 만든다.
    private View createTabView(Drawable drawable) {
        View tabView = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        ImageView imageView = (ImageView) tabView.findViewById(R.id.tab_image_view);
        imageView.setImageDrawable(drawable);
        return tabView;
    }*/
}

