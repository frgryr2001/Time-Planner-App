package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.myapplication.Adapter.ReminderDurationSpinnerAdapter;
import com.example.myapplication.Adapter.ReminderLoopSpinnerAdapter;
import com.example.myapplication.Object.ReminderDurationClass;
import com.example.myapplication.Object.ReminderLoopClass;
import com.example.myapplication.R;
import com.example.myapplication.Object.ReminderTypeClass;
import com.example.myapplication.Adapter.ReminderTypeSpinnerAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddReminderActivity extends AppCompatActivity {
    TextView tvTime,tvDate;
    Spinner spinnerTypereminder, spinnerDurationReminder, spinnerLoopReminder;
    TableRow rowTime,rowDate;
    ImageButton ibBack, ibSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        init();

        // Click back and save image button
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        tvTime.setText(currentTime);

        // Xử lý sự kiện click Time Picker -> chọn thời gian
        rowTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddReminderActivity.this, new TimePickerDialog.OnTimeSetListener() {
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

        // Đổ dữ liệu cho spinner
//        String[] solanlap = new String[]{
//                "Một Lần",
//                "Lặp Lại",
//                "Lặp lại theo vòng",
//                "Lặp theo chu kì trong khoảng thời gian",
//                "Ngẫu Nhiên"
//        };
//        Log.d(""+solanlap, "onCreate: ");
//        final List<String> plantsList = new ArrayList<>(Arrays.asList(solanlap));
//        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, plantsList);
//        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_value);
//        spinner.setAdapter(spinnerArrayAdapter);

        // Loại nhắc nhở -> type
        ReminderTypeSpinnerAdapter reminderTypeAdapter = new ReminderTypeSpinnerAdapter(this, R.layout.spinner_value, ReminderTypeClass.initList());
        spinnerTypereminder.setAdapter(reminderTypeAdapter);

        // Thời lượng -> duration
        ReminderDurationSpinnerAdapter reminderDurationAdapter = new ReminderDurationSpinnerAdapter(this, R.layout.spinner_value, ReminderDurationClass.initList());
        spinnerDurationReminder.setAdapter(reminderDurationAdapter);

        // Số lần lặp -> loop
        ReminderLoopSpinnerAdapter reminderLoopAdapter = new ReminderLoopSpinnerAdapter(this, R.layout.spinner_value, ReminderLoopClass.initList());
        spinnerLoopReminder.setAdapter(reminderLoopAdapter);
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
        spinnerTypereminder = findViewById(R.id.spinnerTypereminder);
        spinnerDurationReminder = findViewById(R.id.spinnerDurationReminder);
        spinnerLoopReminder = findViewById(R.id.spinnerLoopReminder);
        ibBack = findViewById(R.id.ibBack);
        ibSave = findViewById(R.id.ibSave);
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
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                finish();
//                return true;
//            case R.id.menuSave:
//                // Code to save
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.save, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
    // end
}