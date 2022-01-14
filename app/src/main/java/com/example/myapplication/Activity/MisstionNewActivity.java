package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//import com.example.myapplication.Fragment.BottomSheetDialog;
import com.example.myapplication.Object.ChildCategoryClass;
import com.example.myapplication.Object.MissionClass;
import com.example.myapplication.Object.ParentCategoryClass;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MisstionNewActivity extends AppCompatActivity {
    RadioButton rdbtn1,rdbtn2,rdbtn3;
    EditText tvNote;
    ImageButton ibSaveMissionNew,ibBackMission;
    EditText etMisssion;
    Spinner spinnerCate;
    LinearLayout lnCategoryMission;
    private DatabaseReference userRef;
    private DatabaseReference MissionNewRef;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference CategoryRef;
    ArrayAdapter<ParentCategoryClass> adapter_category;
    List<String> listSpinParentCate ;
    String userId = MainActivity.userId;
    String nameSpinner;
    int priority = 0;
    ArrayList<MissionClass> listMiss = new ArrayList<>();;
    MissionClass m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misstion_new);

        init();
        changeTextClick();

        ibBackMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initFirebase();
        spinnerCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nameSpinner= String.valueOf(spinnerCate.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        addMissionNew();

    }

    private void initFirebase() {
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        listSpinParentCate = new ArrayList<String>();
        ArrayList<String> listChild = new ArrayList<>();

            userRef = database.getReference(userId);
            CategoryRef = userRef.child("Category");

//            lstParentCate = new ArrayList<>();

            CategoryRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    ParentCategoryClass p = snapshot.getValue(ParentCategoryClass.class);
                    if(p!=null){
                        listSpinParentCate.add(p.getName());

                        p.getChildCategories().forEach((element -> {
                            listSpinParentCate.add(element.getName());
                        }));
                        adapter_category.notifyDataSetChanged();
                    }

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        adapter_category = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listSpinParentCate);
        adapter_category.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerCate.setAdapter(adapter_category);
    }

    private void init(){
        rdbtn1 = findViewById(R.id.rdbtn1);
        rdbtn2 = findViewById(R.id.rdbtn2);
        rdbtn3 = findViewById(R.id.rdbtn3);
        tvNote = findViewById(R.id.tvNote);
        etMisssion = findViewById(R.id.etMisssion);
        ibSaveMissionNew = findViewById(R.id.ibSaveMissionNew);
        ibBackMission = findViewById(R.id.ibBackMission);
        lnCategoryMission = findViewById(R.id.lnCategoryMission);
        spinnerCate = findViewById(R.id.spinnerCate);
    }
    private void changeTextClick(){
        rdbtn1.setTextColor(getResources().getColor(R.color.green));
        rdbtn2.setTextColor(getResources().getColor(R.color.yellow));
        rdbtn3.setTextColor(getResources().getColor(R.color.red));
        rdbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority = 1;
                Toast.makeText(MisstionNewActivity.this, ""+priority, Toast.LENGTH_SHORT).show();

            }
        });
        rdbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority = 2;
                Toast.makeText(MisstionNewActivity.this, ""+priority, Toast.LENGTH_SHORT).show();

            }
        });
        rdbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority = 3;
                Toast.makeText(MisstionNewActivity.this, ""+priority, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addMissionNew(){

        ibSaveMissionNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(userId).isEmpty()) {
                    userRef = database.getReference(userId);
                    CategoryRef = userRef.child("Category");
                    CategoryRef.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            ParentCategoryClass p = snapshot.getValue(ParentCategoryClass.class);


                            m = new MissionClass(
                                    String.valueOf((p.getMissions()).size()),
                                    etMisssion.getText().toString().trim(),
                                    false,
                                    priority,
                                    tvNote.getText().toString().trim()
                            );
                            if(p.getName().equals(nameSpinner)){
                                listMiss = p.getMissions();
                                listMiss.add(m);
                                p.setMissions(listMiss);
                                CategoryRef.child(p.getId()).setValue(p);
                            }else{

//                                p.getChildCategories().forEach(element -> {
//                                    if(element.getName().equals(nameSpinner)){
//                                        listMiss.add(m);
//                                        element.setMissions(listMiss);
//                                        CategoryRef.child(p.getId()).child("childCategories")
//                                                .child(element.getId()).setValue(element);
//
//                                    }
//
//                                });
                                for(int i = 0 ; i < p.getChildCategories().size();i++){
                                    ChildCategoryClass c = p.getChildCategories().get(i);
                                    if(c.getName().equals(nameSpinner)){
                                        listMiss.add(m);
                                        c.setMissions(listMiss);
                                        CategoryRef.child(p.getId()).child("childCategories")
                                                .child(c.getId()).setValue(c);
                                        break;
                                    }
                                }
                            }

                        }

                        @Override
                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                onBackPressed();
            }
        }

        );
//
    }
}