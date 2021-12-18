package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddReminder extends AppCompatActivity {
    TextView tvTime,tvDate;
    Spinner spinner;
    TableRow rowTime,rowDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        init();

        // Tạo nút Back về Home
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        // End

        // Xử lý sự kiện click Time Picker -> chọn thời gian
        rowTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddReminder.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tvTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        // end

        // add value spinner
//        String[] loopTypes = new String[]{
//                "Một Lần",
//                "Lặp Lại",
//                "Lặp lại theo vòng",
//                "Lặp theo chu kì trong khoảng thời gian",
//                "Ngẫu Nhiên"
//                };
//        Log.d("" + loopTypes, "onCreate: ");
//        final List<String> plantsList = new ArrayList<>(Arrays.asList(loopTypes));
//        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, plantsList);
//        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_value);
//        spinner.setAdapter(spinnerArrayAdapter);

        SpinnerAdapter customAdapter = new SpinnerAdapter(this, R.layout.spinner_value, ReminderType.initList());
        spinner.setAdapter(customAdapter);
        // end

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        tvDate.setText(currentDate);
        rowDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate();
            }
        });

    }
    private void init(){
        tvTime = findViewById(R.id.tvTime);
        rowTime = findViewById(R.id.rowTime);
        tvDate = findViewById(R.id.tvDate);
        rowDate = findViewById(R.id.rowDate);
        spinner = findViewById(R.id.spinner);
    }

    // function chon ngày
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
                tvDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
    // end

    // Tạo nút Back về Home
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menuSave:
                // Code to save
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // end
}