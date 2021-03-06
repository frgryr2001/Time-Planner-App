package com.example.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.Object.ChildCategoryClass;
import com.example.myapplication.Object.MissionClass;
import com.example.myapplication.Object.ParentCategoryClass;
import com.example.myapplication.Object.ScheduleClass;
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

import petrov.kristiyan.colorpicker.ColorPicker;

public class AddChildCategoryActivity extends AppCompatActivity {

    EditText etCate;
    Button btnColor;
    ImageButton ibBack, ibSave;
    Spinner spinnerFatherCate;
    int colorPick = 0;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userRef;
    private DatabaseReference CategoryRef;
    private DatabaseReference CategoryChildRef;

    ArrayAdapter<ParentCategoryClass> adapter_cate_parent;
    ArrayList<ChildCategoryClass> lstParentCate;
    List<String> lstNameParentCate;


    String nameSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child_category);

        init();
        initFirebase();
        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseColor();
            }
        });

        spinnerFatherCate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                nameSpinner = String.valueOf(spinnerFatherCate.getItemAtPosition(i));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ibSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = "";
                if (user != null) {
                    uid = user.getUid();
                }
                if (!(uid).isEmpty()) {
                    userRef = database.getReference(uid);
                    CategoryRef = userRef.child("Category");

                    CategoryRef.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            ParentCategoryClass p = snapshot.getValue(ParentCategoryClass.class);
                            ArrayList<ChildCategoryClass> lstParentCateTemp;

                            int stt = 0;
                            int size = p.getChildCategories().size();
                            if(p.getChildCategories().size() !=0){
                                stt = Integer.parseInt(p.getChildCategories().get(size - 1).getId()) +1;
                            }
                            if(p.getName().equals(nameSpinner)){
                                lstParentCateTemp = new ArrayList<>();
                                lstParentCateTemp = p.getChildCategories();
                                ChildCategoryClass childCate = new ChildCategoryClass(
                                        //String.valueOf(size),
                                        //String.valueOf((p.getChildCategories()).size())
                                        String.valueOf(stt),
                                        etCate.getText().toString().trim(),
                                        R.drawable.ic_baseline_folder_24,
                                        colorPick,new ArrayList<MissionClass>()
                                        ,new ArrayList<ScheduleClass>());
                                lstParentCateTemp.add(childCate);

                                p.setChildCategories(lstParentCateTemp);
                                CategoryRef.child(p.getId()).setValue(p);
                                finish();
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
            }
        });
    }

    private void init(){
        etCate = findViewById(R.id.etCate);
        btnColor = findViewById(R.id.btnColor);
        ibBack = findViewById(R.id.ibBack);
        ibSave = findViewById(R.id.ibSave);
        spinnerFatherCate = findViewById(R.id.spinnerFatherCate);

    }

    public void initFirebase(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = "";
        if (user != null) {
            uid = user.getUid();
        }
        if(!(uid).isEmpty()) {
            userRef = database.getReference(uid);
            CategoryRef = userRef.child("Category");

            lstParentCate = new ArrayList<>();
            lstNameParentCate = new ArrayList<>();

            CategoryRef.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    /*if(lstParentCate!=null || !lstParentCate.isEmpty())
                        lstParentCate.clear();
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        ParentCategoryClass c =dataSnapshot.getValue(ParentCategoryClass.class);
                        lstParentCate.add(c);
                    }*/
                    if(lstNameParentCate!=null || !lstNameParentCate.isEmpty())
                        lstNameParentCate.clear();
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        ParentCategoryClass c =dataSnapshot.getValue(ParentCategoryClass.class);
                        lstNameParentCate.add(c.getName());
                    }
                    adapter_cate_parent.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            //adapter_cate_parent = new ArrayAdapter(this,android.R.layout.simple_spinner_item,lstParentCate);
            adapter_cate_parent = new ArrayAdapter(this,android.R.layout.simple_spinner_item,lstNameParentCate);
            adapter_cate_parent.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
            spinnerFatherCate.setAdapter(adapter_cate_parent);

        }
    }

    public void chooseColor() {
        ColorPicker colorPicker = new ColorPicker(this);
        ArrayList<String> colorList = new ArrayList<>();
        colorList.add("#f44336");
        colorList.add("#e91e63");
        colorList.add("#9c27b0");
        colorList.add("#673ab7");

        colorList.add("#3f51b5");
        colorList.add("#2196f3");
        colorList.add("#03a9f4");
        colorList.add("#00bcd4");

        colorList.add("#009688");
        colorList.add("#4caf50");
        colorList.add("#8bc34a");
        colorList.add("#cddc39");

        colorList.add("#ffeb3b");
        colorList.add("#ffc107");
        colorList.add("#ff9800");
        colorList.add("#ff5722");

        colorList.add("#d84315");
        colorList.add("#ff7043");
        colorList.add("#9e9e9e");
        colorList.add("#263238");

        colorPicker.setColors(colorList)
                .setColumns(4)
                .setRoundColorButton(true)
                .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        colorPick = color;
                        etCate.setTextColor(color);
                        btnColor.setBackgroundColor(color);
                    }


                    @Override
                    public void onCancel() {

                    }
                }).show();
    }
}