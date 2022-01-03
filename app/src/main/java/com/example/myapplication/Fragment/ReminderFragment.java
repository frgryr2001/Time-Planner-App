package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapplication.Activity.AddReminderActivity;
import com.example.myapplication.Activity.MainActivity;
import com.example.myapplication.Adapter.ReminderAdapter;
import com.example.myapplication.Object.ReminderClass;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReminderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReminderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private RecyclerView rvListReminders;
    private FloatingActionButton btnMoveToAddScheduleActivityDaily;
    ArrayList<ReminderClass> listReminders;
    ReminderAdapter reminderAdapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reminderRef;
    String userId = MainActivity.userId;
    List<String> mKeys = new ArrayList<String>();

    public ReminderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReminderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReminderFragment newInstance(String param1, String param2) {
        ReminderFragment fragment = new ReminderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_reminder, container, false);
        initWidgets();

        Log.d("test", "userId: " + userId);
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            MainActivity.userId = user.getUid();
//        }

        // Lấy dữ liệu list reminders
        getData();

//        ArrayList<ReminderClass> listReminders = ReminderClass.getRemindersList();
//        reminderRef = database.getReference(userId).child("Reminders");
//        for (int i = 0; i < listReminders.size(); i++) {
//            ReminderClass reminder = listReminders.get(i);
//            String id = reminderRef.push().getKey();
//            reminder.setId(id);
//            reminderRef.child(id).setValue(reminder);
//        }
        // Adapter
        reminderAdapter = new ReminderAdapter(listReminders, view.getContext());
        rvListReminders.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvListReminders.setHasFixedSize(true);
        rvListReminders.setAdapter(reminderAdapter);

        // Click icon add -> chuyển đến trang thêm nhắc nhở
        btnMoveToAddScheduleActivityDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(view.getContext(), AddReminderActivity.class));
            }
        });

        return view;
    }

    public void initWidgets() {
        rvListReminders = view.findViewById(R.id.rvListReminders);
        btnMoveToAddScheduleActivityDaily = view.findViewById(R.id.btnMoveToAddScheduleActivityDaily);
    }

    //    Get data
    public void getData() {
        reminderRef = database.getReference(userId).child("Reminders");
        listReminders = new ArrayList<ReminderClass>();

        reminderRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ReminderClass r = snapshot.getValue(ReminderClass.class);
                if (r != null) {
                    listReminders.add(r);
                    String key = snapshot.getKey();
                    mKeys.add(key);
                    reminderAdapter .notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ReminderClass r = snapshot.getValue(ReminderClass.class);
                if (r == null || listReminders == null || listReminders.isEmpty()) {
                    return;
                }

                String key = snapshot.getKey();
                int index = mKeys.indexOf(key);
                listReminders.set(index, r);
                reminderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                ReminderClass r = snapshot.getValue(ReminderClass.class);
                if (r == null || listReminders == null || listReminders.isEmpty()) {
                    return;
                }

                String key = snapshot.getKey();
                int index = mKeys.indexOf(key);
                if (index != -1) {
                    listReminders.remove(index);
                    mKeys.remove(index);
                }
                reminderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}