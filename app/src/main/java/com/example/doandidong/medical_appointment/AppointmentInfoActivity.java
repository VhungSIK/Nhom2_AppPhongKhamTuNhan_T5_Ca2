package com.example.doandidong.medical_appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doandidong.R;
import com.example.doandidong.medical_appointment.Appointment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AppointmentInfoActivity extends AppCompatActivity {

    ImageView ivApp;
    TextView tvIdA, tvDoctorName, tvType, tvDate, tvTime;
    String appointmentId;
    FirebaseFirestore db;
    int initialHour, initialMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_info);

        getSupportActionBar().setTitle("Thông Tin Lịch Khám");

        appointmentId=getIntent().getStringExtra("appointmentId");
        ivApp=findViewById(R.id.ivApp);
        tvIdA=findViewById(R.id.tvIdA);
        tvDoctorName=findViewById(R.id.tvDoctorName);
        tvType=findViewById(R.id.tvType);
        tvDate=findViewById(R.id.tvDate);
        tvTime=findViewById(R.id.tvTime);
        TextView btnEditAppointment = findViewById(R.id.btnEditAppointment);
        btnEditAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppointmentInfoActivity.this, EditAppointmentActivity.class);
                intent.putExtra("appointmentId", appointmentId);
                intent.putExtra("initialHour", initialHour); // Gán giờ ban đầu
                intent.putExtra("initialMinute", initialMinute); // Gán phút ban đầu
                startActivity(intent);

                // Chuyển đến trang chỉnh sửa (EditAppointmentActivity) và chuyển theo thông tin cuộc hẹn.
                intent.putExtra("appointmentId", appointmentId); // Truyền appointmentId cho trang chỉnh sửa
                startActivity(intent);
            }
        });

        db=FirebaseFirestore.getInstance();
        DocumentReference docRef=db.collection("Appointment").document(appointmentId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    Appointment appointment=documentSnapshot.toObject(Appointment.class);
                    tvIdA.setText("Lịch khám ");
                    tvDoctorName.setText(appointment.getDoctorName());
                    tvType.setText(appointment.getType());
                    tvDate.setText(appointment.getDate());
                    tvTime.setText(appointment.getTime());
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