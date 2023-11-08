package com.example.doandidong.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.doandidong.R;
import com.example.doandidong.doctor.doctor_appointment.DoctorListAppointmentActivity;
import com.example.doandidong.doctor.doctor_appointment.doctor_result.DoctorListResultActivity;

public class DoctorActivity extends AppCompatActivity {
    private TextView btn_list_appointment, btn_list_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        btn_list_appointment = findViewById(R.id.btn_list_appointment);
        btn_list_result = findViewById(R.id.btn_list_result);
        btn_list_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorActivity.this, DoctorListAppointmentActivity.class);
                startActivity(intent);
            }
        });
        btn_list_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorActivity.this, DoctorListResultActivity.class);
                startActivity(intent);
            }
        });
    }
}