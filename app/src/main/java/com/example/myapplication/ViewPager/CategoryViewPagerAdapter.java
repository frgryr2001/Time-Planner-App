package com.example.myapplication.ViewPager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.Fragment.CategoriesFragment;
import com.example.myapplication.Fragment.DailyScheduleFragment;
import com.example.myapplication.Fragment.FilterFragment;
import com.example.myapplication.Fragment.MonthScheduleFragment;

public class CategoryViewPagerAdapter extends FragmentStatePagerAdapter {

    public CategoryViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CategoriesFragment();
            case 1:
                return new FilterFragment();
            default:
                return new CategoriesFragment();
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
                return "Các danh mục";
            case 1:
                return "Bộ lọc";
            default:
                return "Các danh mục";
        }

    }
}
