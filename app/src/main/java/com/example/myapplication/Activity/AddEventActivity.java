package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.R;

public class AddEventActivity extends AppCompatActivity {
    private EditText etAddEventName, etAddEventNote, etAddEventLocation;
    private ImageButton ibAddEventBack, ibAddEventSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        initWidgets();

        // Nhan nut Back
        ibAddEventBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Nhan nut Save
        ibAddEventSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etAddEventName.getText().toString().isEmpty() ||
                        etAddEventLocation.getText().toString().isEmpty() ||
                        etAddEventNote.getText().toString().isEmpty()) {
                    Toast.makeText(AddEventActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, etAddEventName.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, etAddEventLocation.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, etAddEventNote.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_TIMEZONE, "19:55");
                    intent.putExtra(CalendarContract.Events.EVENT_END_TIMEZONE, "20:00");
                    intent.putExtra(CalendarContract.Events.ALL_DAY, true);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(AddEventActivity.this, "Ứng dụng không hỗ trợ thao tác này", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initWidgets() {
        etAddEventName = findViewById(R.id.etAddEventName);
        etAddEventNote = findViewById(R.id.etAddEventNote);
        etAddEventLocation = findViewById(R.id.etAddEventLocation);
        ibAddEventBack = findViewById(R.id.ibAddEventBack);
        ibAddEventSave = findViewById(R.id.ibAddEventSave);
    }
}