package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.Adapter.ReminderDurationSpinnerAdapter;
import com.example.myapplication.Adapter.ReminderLoopSpinnerAdapter;
import com.example.myapplication.Class.AlarmReceiver;
import com.example.myapplication.Fragment.ReminderFragment;
import com.example.myapplication.Object.ReminderClass;
import com.example.myapplication.Object.ReminderDurationClass;
import com.example.myapplication.Object.ReminderLoopClass;
import com.example.myapplication.R;
import com.example.myapplication.Object.ReminderTypeClass;
import com.example.myapplication.Adapter.ReminderTypeSpinnerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddReminderActivity extends AppCompatActivity {
    private TextView tvAddReminderTime,tvAddReminderDate;
    private Spinner spinnerTypeReminder, spinnerDurationReminder, spinnerLoopReminder;
    private TableRow rowTime,rowDate;
    private ImageButton ibBack, ibAddReminderSave;
    private EditText etAddReminderName;
    private Switch swAddReminderCapcha;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Calendar calendar;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reminderRef;
    String userId = MainActivity.userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        init();
        bindDataToSpinner();

        // Nhấn vào nút Back
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Nhấn vào nút Lưu -> Them nhac nho
        ibAddReminderSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReminderClass r = new ReminderClass();
                r.setName(etAddReminderName.getText().toString());
                r.setTime(tvAddReminderTime.getText().toString());
                r.setDate(tvAddReminderDate.getText().toString());
                r.setType(spinnerTypeReminder.getSelectedItemPosition());
                r.setDuration(spinnerDurationReminder.getSelectedItemPosition());
                r.setLoopTime(spinnerLoopReminder.getSelectedItemPosition());
                if (swAddReminderCapcha.isChecked()) {
                    r.setCapcha(true);
                } else {
                    r.setCapcha(false);
                }

                // Thêm nhắc nhở
                addReminder(r);

                // Tạo âm thanh báo nhắc nhở
                int notificationId = (int) (Math.random() * 999 + 1);
                // Get hour and minute
                int hour = Integer.parseInt(tvAddReminderTime.getText().toString().split(":")[0]);
                int minute = Integer.parseInt(tvAddReminderTime.getText().toString().split(":")[1]);

                // Set calendar
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);

                // Intent
                Intent intent = new Intent(AddReminderActivity.this, AlarmReceiver.class);
                intent.putExtra("notificationId", notificationId);
                intent.putExtra("action", "on");
                intent.putExtra("name", etAddReminderName.getText().toString());

                // PendingIntent
                pendingIntent = PendingIntent.getBroadcast(
                        AddReminderActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
                );

                // Set Alarm
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                finish();
            }
        });

        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        tvAddReminderTime.setText(currentTime);

        // Xử lý sự kiện click Time Picker -> chọn thời gian
        rowTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTime();
            }
        });

        // Xử lý sự kiện click Date Picker -> chọn date
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        tvAddReminderDate.setText(currentDate);
        rowDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate();
            }
        });

    }
    private void init(){
        tvAddReminderTime = findViewById(R.id.tvAddReminderTime);
        rowTime = findViewById(R.id.rowTime);
        tvAddReminderDate = findViewById(R.id.tvAddReminderDate);
        rowDate = findViewById(R.id.rowDate);
        spinnerTypeReminder = findViewById(R.id.spinnerTypereminder);
        spinnerDurationReminder = findViewById(R.id.spinnerDurationReminder);
        spinnerLoopReminder = findViewById(R.id.spinnerLoopReminder);
        ibBack = findViewById(R.id.ibBack);
        ibAddReminderSave = findViewById(R.id.ibAddReminderSave);
        etAddReminderName = findViewById(R.id.etAddReminderName);
        swAddReminderCapcha = findViewById(R.id.swAddReminderCapcha);
        calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    private void bindDataToSpinner() {
        // Loại nhắc nhở -> type
        ReminderTypeSpinnerAdapter reminderTypeAdapter = new ReminderTypeSpinnerAdapter(this, R.layout.spinner_value, ReminderTypeClass.initList());
        spinnerTypeReminder.setAdapter(reminderTypeAdapter);

        // Thời lượng -> duration
        ReminderDurationSpinnerAdapter reminderDurationAdapter = new ReminderDurationSpinnerAdapter(this, R.layout.spinner_value, ReminderDurationClass.initList());
        spinnerDurationReminder.setAdapter(reminderDurationAdapter);

        // Số lần lặp -> loop
        ReminderLoopSpinnerAdapter reminderLoopAdapter = new ReminderLoopSpinnerAdapter(this, R.layout.spinner_value, ReminderLoopClass.initList());
        spinnerLoopReminder.setAdapter(reminderLoopAdapter);
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
                tvAddReminderDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }

    // Chọn Time
    private void selectTime() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(AddReminderActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                tvAddReminderTime.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time

        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    // Them nhac nho
    private void addReminder(ReminderClass r) {
        reminderRef = database.getReference(userId).child("Reminders");
        String id = reminderRef.push().getKey();
        r.setId(id);
        reminderRef.child(id).setValue(r);
    }
}