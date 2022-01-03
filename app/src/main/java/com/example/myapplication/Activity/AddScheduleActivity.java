package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddScheduleActivity extends AppCompatActivity {
    private EditText etAddScheduleName, etAddScheduleNote;
    private TextView tvAddScheduleDate, tvAddScheduleTimeStart, tvAddScheduleTimeEnd;
    private Spinner spnAddSchedule;
    private ImageButton ibAddScheduleBack, ibAddScheduleSave;
    private TableRow trAddScheduleDate, trAddScheduleTimeStart, trAddScheduleTimeEnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        initWidgets();

        // Nhan nut Back
        ibAddScheduleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Nhan nut Save
        ibAddScheduleSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Chon ngay
        trAddScheduleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDate();
            }
        });

        // Chon thoi gian bat dau
        trAddScheduleTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTimeStart();
            }
        });

        // Chon thoi gian ket thuc
        trAddScheduleTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTimeEnd();
            }
        });

    }

    // Anh xa
    public void initWidgets() {
        etAddScheduleName = findViewById(R.id.etAddScheduleName);
        tvAddScheduleDate = findViewById(R.id.tvAddScheduleDate);
        tvAddScheduleTimeStart = findViewById(R.id.tvAddScheduleTimeStart);
        tvAddScheduleTimeEnd = findViewById(R.id.tvAddScheduleTimeEnd);
        spnAddSchedule = findViewById(R.id.spnAddSchedule);
        etAddScheduleNote = findViewById(R.id.etAddScheduleNote);
        ibAddScheduleBack = findViewById(R.id.ibAddScheduleBack);
        ibAddScheduleSave = findViewById(R.id.ibAddScheduleSave);
        trAddScheduleDate = findViewById(R.id.trAddScheduleDate);
        trAddScheduleTimeStart = findViewById(R.id.trAddScheduleTimeStart);
        trAddScheduleTimeEnd = findViewById(R.id.trAddScheduleTimeEnd);
    }

    // Chon ngày
    private void selectDate(){
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                tvAddScheduleDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.setTitle("Chon ngay");
        datePickerDialog.show();
    }

    // Chọn Time start
    private void selectTimeStart() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(AddScheduleActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                tvAddScheduleTimeStart.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Chon thoi gian bat dau");
        mTimePicker.show();
    }

    // Chọn Time end
    private void selectTimeEnd() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(AddScheduleActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                tvAddScheduleTimeEnd.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Chon thoi gian ket thuc");
        mTimePicker.show();
    }
}