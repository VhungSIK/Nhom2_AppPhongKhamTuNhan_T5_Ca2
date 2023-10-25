package com.example.doandidong.medical_appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.doandidong.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditAppointmentActivity extends AppCompatActivity {
    TextView editTextNewDate, editTextNewTime;
    TextView tvDoctorName;
    TextView tvType;
    Button btnSave;

    FirebaseFirestore db;
    DocumentReference docRef;
    String appointmentId;
    int initialHour, initialMinute;
    int initialYear, initialMonth, initialDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment);

        getSupportActionBar().setTitle("Sửa thông tin lịch đã đặt");
        editTextNewDate = findViewById(R.id.editTextNewDate);
        editTextNewTime = findViewById(R.id.editTextNewTime);
        tvDoctorName = findViewById(R.id.tvDoctorName);
        tvType = findViewById(R.id.tvType);
        btnSave = findViewById(R.id.btnSave);
        editTextNewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        editTextNewTime = findViewById(R.id.editTextNewTime);
        String initialTime = getIntent().getStringExtra("initialTime");
        if (initialTime != null) {
            String[] timeParts = initialTime.split(":");
            if (timeParts.length == 2) {
                initialHour = Integer.parseInt(timeParts[0]);
                initialMinute = Integer.parseInt(timeParts[1]);
            }
            editTextNewTime.setText(initialTime);
        }

        editTextNewTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });


        db = FirebaseFirestore.getInstance();

        appointmentId = getIntent().getStringExtra("appointmentId");
        docRef = db.collection("Appointment").document(appointmentId);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String doctorName = documentSnapshot.getString("DoctorName");
                    String type = documentSnapshot.getString("Type");

                    tvDoctorName.setText(doctorName);
                    tvType.setText(type);

                    String existingDate = documentSnapshot.getString("Date");
                    String existingTime = documentSnapshot.getString("Time");
                    editTextNewDate.setText(existingDate);
                    editTextNewTime.setText(existingTime);
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newDate = editTextNewDate.getText().toString();
                String newTime = editTextNewTime.getText().toString();

                if (!newDate.isEmpty() && !newTime.isEmpty()) {
                    docRef.update("Date", newDate, "Time", newTime);

                    Intent intent = new Intent(EditAppointmentActivity.this, AppointmentInfoActivity.class);
                    intent.putExtra("appointmentId", appointmentId);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    private void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                EditAppointmentActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                        editTextNewTime.setText(selectedTime);
                    }
                },
                initialHour,
                initialMinute,
                true
        );

        timePickerDialog.show();
    }
    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                EditAppointmentActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = (month + 1) + "/" + dayOfMonth + "/" + year;
                        editTextNewDate.setText(selectedDate);
                    }
                },
                initialYear,
                initialMonth,
                initialDay
        );

        datePickerDialog.show();
    }
}