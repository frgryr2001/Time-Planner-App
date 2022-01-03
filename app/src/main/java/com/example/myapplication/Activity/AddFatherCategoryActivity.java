package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.Object.ParentCategoryClass;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import petrov.kristiyan.colorpicker.ColorPicker;

public class AddFatherCategoryActivity extends AppCompatActivity {

    EditText etCate;
    Button btnColor;
    ImageButton ibBack, ibSave;
    int colorPick = 0;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference userRef;
    private DatabaseReference CategoryRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_father_category);

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
                if(!(uid).isEmpty()) {
                    userRef = database.getReference(uid);
                    ParentCategoryClass p = new ParentCategoryClass("","123",R.drawable.add_test_1,colorPick);
                    CategoryRef = userRef.child("Category");
                    String id = CategoryRef.push().getKey();
                    p.setId(id);
                    CategoryRef.child(id).setValue(p);
                    userRef.child("Category").child(p.getId()).setValue(p);
                }
            }
        });

    }


    private void init(){
        etCate = findViewById(R.id.etCate);
        btnColor = findViewById(R.id.btnColor);
        ibBack = findViewById(R.id.ibBack);
        ibSave = findViewById(R.id.ibSave);
    }

    public void chooseColor() {
        final ColorPicker colorPicker = new ColorPicker(this);
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