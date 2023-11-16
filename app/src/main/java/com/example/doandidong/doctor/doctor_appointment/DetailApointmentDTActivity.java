package com.example.doandidong.doctor.doctor_appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doandidong.R;
import com.example.doandidong.doctor.doctor_appointment.doctor_request.DoctorrequestActivity;
import com.example.doandidong.medical_appointment.Appointment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailApointmentDTActivity extends AppCompatActivity {
    ImageView ivApp1;
    TextView tvIdA1, tvDoctorName1, tvType1, tvDate1, tvUserName1, tvUserPhone1, tvUserEmail1, btn_request ;
    String doctorappointmentId;
    String userId ;
    String doctorId;
    FirebaseAuth auth;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_apointment_dtactivity);
        getSupportActionBar().setTitle("Thông Tin Lịch Khám");
        auth=FirebaseAuth.getInstance();
        doctorId=auth.getCurrentUser().getUid();
        doctorappointmentId=getIntent().getStringExtra("doctorappointmentId");
        ivApp1=findViewById(R.id.ivApp1);
        tvIdA1=findViewById(R.id.tvIdA1);
        tvDoctorName1=findViewById(R.id.tvDoctorName1);
        tvType1=findViewById(R.id.tvType1);
        tvDate1=findViewById(R.id.tvDate1);
        tvUserName1=findViewById(R.id.tvUserName1);
        tvUserPhone1=findViewById(R.id.tvUserPhone1);
        tvUserEmail1=findViewById(R.id.tvUserEmail1);
        btn_request=findViewById(R.id.btn_request);
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailApointmentDTActivity.this, DoctorrequestActivity.class);

                // Truyền thông tin cuộc hẹn qua Intent
                intent.putExtra("doctorappointmentId", doctorappointmentId);
                intent.putExtra("doctorId", doctorId);
                intent.putExtra("doctorName", tvDoctorName1.getText().toString());
                intent.putExtra("appointmentType", tvType1.getText().toString());
                intent.putExtra("appointmentDate", tvDate1.getText().toString());
                intent.putExtra("userName", tvUserName1.getText().toString());
                intent.putExtra("userPhone", tvUserPhone1.getText().toString());
                intent.putExtra("userEmail", tvUserEmail1.getText().toString());
                userId = getIntent().getStringExtra("userId");
                intent.putExtra("userId", userId); // Chắc chắn gán userId trước khi log
                Log.d("DetailActivity", "User ID: " + userId);Log.d("DetailApointmentDTActivity", "doctorId: " + doctorId);

                startActivity(intent);
            }
        });


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