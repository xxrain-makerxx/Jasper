package com.m.jasper.NavActivities.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.m.jasper.R;

public class Home extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        tabLayout=view.findViewById(R.id.tab_layout);
        viewPager=view.findViewById(R.id.main_viewpager);
        final Notifications notifications=new Notifications();
        final Complain complain=new Complain();
        FragmentPagerAdapter fragmentPagerAdapter=new FragmentPagerAdapter(getChildFragmentManager()) {
            String[] title={"Notifications","Complaints"};
            @Override
            public Fragment getItem(int i) {
                switch (i) {
                    case 0:
                        return notifications;
                    case 1:
                        return complain;
                }
                return null;
            }
            @Override
            public int getCount() {
                return 2;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        };
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
