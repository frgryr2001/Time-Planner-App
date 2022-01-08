package com.example.myapplication.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.example.myapplication.Adapter.ReminderTypeSpinnerAdapter;
import com.example.myapplication.Object.ReminderClass;
import com.example.myapplication.Object.ReminderDurationClass;
import com.example.myapplication.Object.ReminderLoopClass;
import com.example.myapplication.Object.ReminderTypeClass;
import com.example.myapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReminderDetailActivity extends AppCompatActivity {
    private TextView tvReminderDetailTime,tvReminderDetailDate;
    private EditText etReminderDetailName;
    private Spinner spnReminderDetailType, spnReminderDetailDuration, spnReminderDetailLoop;
    private Switch swReminderDetailCapcha;
    private Button btnReminderDetailBack, btnReminderDetailSave, btnReminderDetailDelete;
    private TableRow trReminderDetailTime,trReminderDetailDate;
    private ReminderClass reminder;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reminderRef;
    String userId = MainActivity.userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_detail);
        initWidgets();
        setSpinnerData();

        //Lấy dữ liệu reminder từ bundle
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        if (bundle != null) {
            reminder = (ReminderClass) bundle.getSerializable("reminder");
            etReminderDetailName.setText(reminder.getName().toString());
            tvReminderDetailTime.setText(reminder.getTime().toString());
            tvReminderDetailDate.setText(reminder.getDate().toString());
            spnReminderDetailType.setSelection(reminder.getType());
            spnReminderDetailDuration.setSelection(reminder.getDuration());
            spnReminderDetailLoop.setSelection(reminder.getLoopTime());
            if (reminder.isCapcha() == true) {
                swReminderDetailCapcha.setChecked(true);
            } else {
                swReminderDetailCapcha.setChecked(false);
            }
        }

        // Nhấn nút THOÁT
        btnReminderDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Nhấn nút SAVE -> Cap nhat nhac nho
        btnReminderDetailSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReminderClass r = new ReminderClass();
                r.setId(reminder.getId().toString());
                r.setName(etReminderDetailName.getText().toString());
                r.setTime(tvReminderDetailTime.getText().toString());
                r.setDate(tvReminderDetailDate.getText().toString());
                r.setType(spnReminderDetailType.getSelectedItemPosition());
                r.setDuration(spnReminderDetailDuration.getSelectedItemPosition());
                r.setLoopTime(spnReminderDetailLoop.getSelectedItemPosition());
                if (swReminderDetailCapcha.isChecked()) {
                    r.setCapcha(true);
                } else {
                    r.setCapcha(false);
                }
                updateReminder(r);
                finish();
            }
        });

        // Nhan nut XOA -> Xoa
        btnReminderDetailDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDialog();
            }
        });

        // Xử lý sự kiện click Time Picker -> chọn thời gian
        trReminderDetailTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTime();
            }
        });

        // Xử lý sự kiện click Date Picker -> chọn ngày
        trReminderDetailDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate();
            }
        });
    }

    // Ánh xạ
    public void initWidgets() {
        tvReminderDetailTime = findViewById(R.id.tvReminderDetailTime);
        tvReminderDetailDate = findViewById(R.id.tvReminderDetailDate);
        etReminderDetailName = findViewById(R.id.etReminderDetailName);
        spnReminderDetailType = findViewById(R.id.spnReminderDetailType);
        spnReminderDetailDuration = findViewById(R.id.spnReminderDetailDuration);
        spnReminderDetailLoop = findViewById(R.id.spnReminderDetailLoop);
        swReminderDetailCapcha = findViewById(R.id.swReminderDetailCapcha);
        btnReminderDetailBack = findViewById(R.id.btnReminderDetailBack);
        btnReminderDetailSave = findViewById(R.id.btnReminderDetailSave);
        trReminderDetailTime = findViewById(R.id.trReminderDetailTime);
        trReminderDetailDate = findViewById(R.id.trReminderDetailDate);
        btnReminderDetailDelete = findViewById(R.id.btnReminderDetailDelete);
    }

    // Đổ dữ liệu cho spinner
    public void setSpinnerData() {
        // Loại nhắc nhở -> type
        ReminderTypeSpinnerAdapter reminderTypeAdapter = new ReminderTypeSpinnerAdapter(this, R.layout.spinner_value, ReminderTypeClass.initList());
        spnReminderDetailType.setAdapter(reminderTypeAdapter);

        // Thời lượng -> duration
        ReminderDurationSpinnerAdapter reminderDurationAdapter = new ReminderDurationSpinnerAdapter(this, R.layout.spinner_value, ReminderDurationClass.initList());
        spnReminderDetailDuration.setAdapter(reminderDurationAdapter);

        // Số lần lặp -> loop
        ReminderLoopSpinnerAdapter reminderLoopAdapter = new ReminderLoopSpinnerAdapter(this, R.layout.spinner_value, ReminderLoopClass.initList());
        spnReminderDetailLoop.setAdapter(reminderLoopAdapter);
    }

    // Chọn time
    public void selectTime() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(ReminderDetailActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                tvReminderDetailTime.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    // Chọn Date
    public void selectDate() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(ReminderDetailActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                tvReminderDetailDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }

    // Cap nhat nhac nho
    public void updateReminder(ReminderClass r) {
        reminderRef = database.getReference(userId).child("Reminders");
        reminderRef.child(r.getId()).setValue(r);
    }

    // Xoa nhac nho
    public void deleteReminder() {
        reminderRef = database.getReference(userId).child("Reminders");
        reminderRef.child(reminder.getId()).removeValue();
    }

    //    Delete dialog
    private void showDeleteDialog() {
        new AlertDialog.Builder(this)
                .setTitle("XÓA NHẮC NHỞ")
                .setMessage("BẠN MUỐN XÓA NHẮC NHỞ NÀY?")
                .setPositiveButton("ĐỒNG Ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteReminder();
                        finish();
                    }
                })
                .setNegativeButton("HỦY BỎ",null)
                .show();
    }
}