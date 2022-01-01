package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class MisstionNewActivity extends AppCompatActivity {
    RadioButton rdbtn1,rdbtn2,rdbtn3;
    TableRow rowNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misstion_new);
        init();
        changeTextClick();
        OpenNoteActivity();
    }
    private void init(){
        rdbtn1 = findViewById(R.id.rdbtn1);
        rdbtn2 = findViewById(R.id.rdbtn2);
        rdbtn3 = findViewById(R.id.rdbtn3);
        rowNotes = findViewById(R.id.rowNotes);
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
}