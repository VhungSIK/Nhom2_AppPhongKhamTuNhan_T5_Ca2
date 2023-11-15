package com.example.doandidong.doctor.doctor_appointment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doandidong.R;

import com.example.doandidong.medical_appointment.AppointmentAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DoctorListAppointmentActivity extends AppCompatActivity implements DoctorAppointmentAdapter.Listener {
    RecyclerView rvAppointments1;
    String doctorId;

    ArrayList<DoctorAppointment> doctorAppointments;
    DoctorAppointmentAdapter doctorAppointmentAdapter;
    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list_appointment);
        getSupportActionBar().setTitle("Lịch Khám");
        rvAppointments1 = findViewById(R.id.rvAppointments1);
        db=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        doctorId=auth.getCurrentUser().getUid();
        doctorAppointments=new ArrayList<>();
        doctorAppointmentAdapter=new DoctorAppointmentAdapter(this,doctorAppointments);
        rvAppointments1.setAdapter(doctorAppointmentAdapter);
        rvAppointments1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvAppointments1.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        db.collection("Appointment")
                .whereEqualTo("DoctorId",doctorId)
                .whereEqualTo("Request", "wait")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document: task.getResult()) {
                            String Id = document.getId();
                            String DoctorName = document.get("DoctorName").toString();
                            String Type = document.get("Type").toString();
                            String Date = document.get("Date").toString();
                            String Time = document.get("Time").toString();
                            String UserName = document.get("UserName").toString();
                            String UserPhone = document.get("UserPhone").toString();
                            String UserEmail = document.get("UserEmail").toString();
                            String Request = document.get("Request").toString();
                            if ("wait".equals(Request)) {
                                String userId = document.get("UserId").toString();
                                DoctorAppointment doctorAppointment = new DoctorAppointment(Id, DoctorName, Type, Date, Time, UserName, UserPhone, UserEmail, userId);
                                doctorAppointments.add(doctorAppointment);
                                Log.d("DoctorListActivity", "User ID: " + userId);
                                Log.d("DoctorListAppointmentActivity", "doctorId: " + doctorId);

                            }
                        }
                        doctorAppointmentAdapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DoctorListAppointmentActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onClickItemListener(DoctorAppointment doctorAppointment) {
        Intent intent = new Intent(this, DetailApointmentDTActivity.class);
        intent.putExtra("doctorappointmentId", doctorAppointment.getIdA());
        intent.putExtra("userId", doctorAppointment.getUserId());
        startActivity(intent);
    }
}
