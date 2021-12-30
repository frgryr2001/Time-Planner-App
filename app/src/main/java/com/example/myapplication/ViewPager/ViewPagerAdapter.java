package com.example.myapplication.ViewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.Fragment.CategoryFragment;
import com.example.myapplication.Fragment.HomeFragment;
import com.example.myapplication.Fragment.OptionFragment;
import com.example.myapplication.Fragment.ReminderFragment;
import com.example.myapplication.Fragment.ScheduleFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new ReminderFragment();
            case 2:
                return new ScheduleFragment();
            case 3:
                return new CategoryFragment();
            case 4:
                return new OptionFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
