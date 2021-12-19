package com.example.myapplication.ViewPager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.Fragment.DailyScheduleFragment;
import com.example.myapplication.Fragment.MonthScheduleFragment;

public class ScheduleViewPagerAdapter extends FragmentStatePagerAdapter {
    public ScheduleViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DailyScheduleFragment();
            case 1:
                return new MonthScheduleFragment();
            default:
                return new DailyScheduleFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Hôm nay";
            case 1:
                return "Tổng quan";
            default:
                return "Hôm nay";
        }
    }
}
