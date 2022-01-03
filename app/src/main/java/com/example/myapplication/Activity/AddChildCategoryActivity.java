package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.myapplication.Object.ActivityClass;
import com.example.myapplication.Object.ChildCategoryClass;
import com.example.myapplication.Object.MissionClass;
import com.example.myapplication.Object.ParentCategoryClass;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private DatabaseReference CategoryChildRef;
    ArrayAdapter<ParentCategoryClass> adapterParent;
    private List<ParentCategoryClass> listParentCategoryClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child_category);

        init();

        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseColor();
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
                    ArrayList<ChildCategoryClass> ChildCategoryClass1
                            = new ArrayList<ChildCategoryClass>();

//                    ChildCategoryClass c = new ChildCategoryClass(
//                            "1",
//                            "test",
//                            R.drawable.ic_baseline_folder_24,
//                            colorPick,new ArrayList<MissionClass>(),
//                            new ArrayList<ActivityClass>());
//                    ChildCategoryClass c1 = new ChildCategoryClass(
//                            "2",
//                            "test",
//                            R.drawable.ic_baseline_folder_24,
//                            colorPick,new ArrayList<MissionClass>(),
//                            new ArrayList<ActivityClass>());
//                    ChildCategoryClass1.add(c);
//                    ChildCategoryClass1.add(c1);
                    ChildCategoryClass c = new ChildCategoryClass("",
                            etCate.getText().toString().trim(),
                            R.drawable.ic_baseline_folder_24,
                            colorPick, new ArrayList<MissionClass>()
                            , new ArrayList<ActivityClass>());

                    CategoryChildRef = userRef.child("CategoryChild");
                    String id = CategoryChildRef.push().getKey();
                    c.setId(id);
                    CategoryChildRef.child(id).setValue(c);
                    userRef.child("CategoryChild").child(c.getId()).setValue(c);
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
                        etCate.setTextColor(color);
                        btnColor.setBackgroundColor(color);
                    }


                    @Override
                    public void onCancel() {

                    }
                }).show();
    }
}