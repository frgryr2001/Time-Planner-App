package com.example.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.ViewPager.CustomViewPager;
import com.example.myapplication.ViewPager.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    private CustomViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set bottom navigation and view pager
        bottomNav = findViewById(R.id.bottomNav);
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setPagingEnable(false);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);

        // Click bottom navigation item
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeBottomMenu:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.scheduleBottomNav:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.categoryBottomNav:
                        mViewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

        // View Pager paging
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNav.getMenu().findItem(R.id.homeBottomMenu).setChecked(true);
                        break;
                    case 1:
                        bottomNav.getMenu().findItem(R.id.scheduleBottomNav).setChecked(true);
                        break;
                    case 2:
                        bottomNav.getMenu().findItem(R.id.categoryBottomNav).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    // Transfer to AddReminder activity
    public void openActivity(View view) {
        Intent intent = new Intent(this, AddReminderActivity.class);
        startActivity(intent);
    }

    public void openActivity1(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }
}