package com.example.doandidong.doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.doandidong.R;
import com.example.doandidong.doctor.doctor_appointment.DoctorListAppointmentActivity;
import com.example.doandidong.medical_appointment.Appointment;
import com.example.doandidong.medical_appointment.AppointmentAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class DoctorActivity extends AppCompatActivity {
    private Button btn_list_appointment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        btn_list_appointment = findViewById(R.id.btn_list_appointment);
        btn_list_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorActivity.this, DoctorListAppointmentActivity.class);
                startActivity(intent);
            }
        });
    }
}