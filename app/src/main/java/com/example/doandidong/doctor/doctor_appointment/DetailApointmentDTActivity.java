package com.example.doandidong.doctor.doctor_appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doandidong.R;
import com.example.doandidong.medical_appointment.Appointment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailApointmentDTActivity extends AppCompatActivity {
    ImageView ivApp1;
    TextView tvIdA1, tvDoctorName1, tvType1, tvDate1, tvTime1, tvUserName1, tvUserPhone1, tvUserEmail1 ;
    String doctorappointmentId;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_apointment_dtactivity);
        getSupportActionBar().setTitle("Thông Tin Lịch Khám");

        doctorappointmentId=getIntent().getStringExtra("doctorappointmentId");
        ivApp1=findViewById(R.id.ivApp1);
        tvIdA1=findViewById(R.id.tvIdA1);
        tvDoctorName1=findViewById(R.id.tvDoctorName1);
        tvType1=findViewById(R.id.tvType1);
        tvDate1=findViewById(R.id.tvDate1);
        tvTime1=findViewById(R.id.tvTime1);
        tvUserName1=findViewById(R.id.tvUserName1);
        tvUserPhone1=findViewById(R.id.tvUserPhone1);
        tvUserEmail1=findViewById(R.id.tvUserEmail1);

        db=FirebaseFirestore.getInstance();
        DocumentReference docRef=db.collection("Appointment").document(doctorappointmentId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    DoctorAppointment doctorAppointment=documentSnapshot.toObject(DoctorAppointment.class);
                    tvIdA1.setText("Lịch khám ");
                    tvDoctorName1.setText(doctorAppointment.getDoctorName());
                    tvType1.setText(doctorAppointment.getType());
                    tvDate1.setText(doctorAppointment.getDate());
                    tvUserName1.setText(doctorAppointment.getUserName());
                    tvUserPhone1.setText(doctorAppointment.getUserPhone());
                    tvUserEmail1.setText(doctorAppointment.getUserEmail());
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}