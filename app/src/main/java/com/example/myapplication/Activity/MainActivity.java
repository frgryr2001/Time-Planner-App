package com.example.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.Object.ReminderClass;
import com.example.myapplication.R;
import com.example.myapplication.ViewPager.CustomViewPager;
import com.example.myapplication.ViewPager.ViewPagerAdapter;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static String userId = "";
    private BottomNavigationView bottomNav;
    private CustomViewPager mViewPager;
    private Button btnLogOut;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userRef;
    private DatabaseReference reminderRef;
    private List<ReminderClass> reminderList;
    List<String> mKeys = new ArrayList<String>();
//    public static String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set bottom navigation and view pager
        bottomNav = findViewById(R.id.bottomNav);
        mViewPager = findViewById(R.id.viewPager);
        mViewPager.setPagingEnable(false);
        btnLogOut = findViewById(R.id.btnLogOut);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mAuth = FirebaseAuth.getInstance();
        mGoogleSignInClient = GoogleSignIn.getClient(MainActivity.this , gso);

        // Thực hiện thêm user id vào firebase -> xử lý thêm nhắc nhở mới
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = "";
        if (user != null) {
            userId = user.getUid();
        }
//        Log.d("TAG" + uid, "onCreate: ");
        if(!userId.isEmpty()) {
            userRef = database.getReference(userId);
            reminderRef = userRef.child("Reminders");
//            userRef.child("Categories").setValue("Hello category");
//            ReminderClass r = new ReminderClass("0", "r1", "9:30", "1/1/2020", false, 1, 1, false);
//            String id = reminderRef.push().getKey();
//            r.setId(id);
//            reminderRef.child(id).setValue(r);
//            userRef.child("Reminders").child(r.getId()).setValue(r);
        }

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
                    case R.id.reminderBottomNav:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.scheduleBottomNav:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.categoryBottomNav:
                        mViewPager.setCurrentItem(3);
                        break;
                    case R.id.optionBottomNav:
                        mViewPager.setCurrentItem(4);
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
                        bottomNav.getMenu().findItem(R.id.reminderBottomNav).setChecked(true);
                        break;
                    case 2:
                        bottomNav.getMenu().findItem(R.id.scheduleBottomNav).setChecked(true);
                        break;
                    case 3:
                        bottomNav.getMenu().findItem(R.id.categoryBottomNav).setChecked(true);
                        break;
                    case 4:
                        bottomNav.getMenu().findItem(R.id.optionBottomNav).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //Đăng xuất
        btnLogOut.setOnClickListener(view -> {
            mGoogleSignInClient.signOut();
            mAuth.signOut();
            FacebookSdk.sdkInitialize(MainActivity.this);
            LoginManager.getInstance().logOut();
            AccessToken.setCurrentAccessToken(null);

            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });
    }

    // Lấy dữ liệu
//    public void getData(String keyword) {
//        reminderList = new ArrayList<ReminderClass>();
//        reminderRef = database.getReference("Reminders");
//
//        reminderRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                ReminderClass reminder = snapshot.getValue(ReminderClass.class);
//                if (reminder != null) {
//                    reminderList.add(reminder);
//                    String key = snapshot.getKey();
//                    mKeys.add(key);
//                    laptopAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                Laptop laptop = snapshot.getValue(Laptop.class);
//                if (laptop == null || listLaptop == null || listLaptop.isEmpty()) {
//                    return;
//                }
//
//                String key = snapshot.getKey();
//                int index = mKeys.indexOf(key);
//                listLaptop.set(index, laptop);
//                laptopAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//                Laptop laptop = snapshot.getValue(Laptop.class);
//                if (laptop == null || listLaptop == null || listLaptop.isEmpty()) {
//                    return;
//                }
//
//                String key = snapshot.getKey();
//                int index = mKeys.indexOf(key);
//                if (index != -1) {
//                    listLaptop.remove(index);
//                    mKeys.remove(index);
//                }
//                laptopAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
}