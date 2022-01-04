package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.myapplication.Fragment.BottomSheetDialog;
import com.example.myapplication.Fragment.BottomSheetDialog;
import com.example.myapplication.Object.MissionClass;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MisstionNewActivity extends AppCompatActivity {
    RadioButton rdbtn1,rdbtn2,rdbtn3;
    TableRow rowNotes;
    ImageButton ibSaveMissionNew,ibBackMission;
    EditText etMisssion;
    LinearLayout lnCategoryMission;
    private DatabaseReference userRef;
    private DatabaseReference MissionNewRef;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misstion_new);
        init();
        changeTextClick();
        OpenNoteActivity();
        addMissionNew();
        ibBackMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lnCategoryMission.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        BottomSheetDialog bottomSheet = new BottomSheetDialog();
                        bottomSheet.show(getSupportFragmentManager(),
                                "ModalBottomSheet");
                    }
                });
    }
    private void init(){
        rdbtn1 = findViewById(R.id.rdbtn1);
        rdbtn2 = findViewById(R.id.rdbtn2);
        rdbtn3 = findViewById(R.id.rdbtn3);
        rowNotes = findViewById(R.id.rowNotes);
        ibSaveMissionNew = findViewById(R.id.ibSaveMissionNew);
        ibBackMission = findViewById(R.id.ibBackMission);
        lnCategoryMission = findViewById(R.id.lnCategoryMission);
    }
    private void changeTextClick(){
        rdbtn1.setTextColor(getResources().getColor(R.color.green));
        rdbtn2.setTextColor(getResources().getColor(R.color.yellow));
        rdbtn3.setTextColor(getResources().getColor(R.color.red));
    }
    private void OpenNoteActivity(){
        rowNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MisstionNewActivity.this , NotesActivity.class);
                startActivity(intent);
            }
        });
    }
    private void addMissionNew(){

        ibSaveMissionNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                String uid = "";
//                if (user != null) {
//                    uid = user.getUid();
//                }
//                if(!(uid).isEmpty()) {
//                    userRef = database.getReference(uid);
//                    MissionNewRef = userRef.child("MissionNew");
//                    MissionClass m = new MissionClass("","Đi học",false,1);
//                    String id = MissionNewRef.push().getKey();
//                    m.setId(id);
//                    MissionNewRef.child(id).setValue(m);
//                    userRef.child("MissionNew").child(m.getId()).setValue(m);
//                }
            }
        });

    }
}