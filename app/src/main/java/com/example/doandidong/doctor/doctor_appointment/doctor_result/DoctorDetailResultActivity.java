package com.example.doandidong.doctor.doctor_appointment.doctor_result;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doandidong.R;
import com.example.doandidong.doctor.doctor_appointment.doctor_request.PrescriptionsActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DoctorDetailResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail_result);

        // Lấy thông tin đã truyền từ DoctorListResultActivity
        Intent intent = getIntent();
        String doctorName = intent.getStringExtra("doctorName");
        String userName = intent.getStringExtra("userName");
        String appointmentDate = intent.getStringExtra("appointmentDate");
        String appointmentType = intent.getStringExtra("appointmentType");
        String currentTime = intent.getStringExtra("currentTime");
        String bloodGroup = intent.getStringExtra("bloodGroup");
        String quantification = intent.getStringExtra("quantification");
        String index = intent.getStringExtra("index");
        String totalAnalysis = intent.getStringExtra("totalAnalysis");
        String userId = intent.getStringExtra("userId");
        String doctorId = intent.getStringExtra("doctorId");
        Log.d("DoctorDetailResult", "userId: " + userId);
        Log.d("DoctorDetailResult", "usessdrId: " + doctorId);


        // Hiển thị thông tin trên giao diện của DoctorDetailResultActivity
        TextView doctorNameTextView = findViewById(R.id.doctorNameTextView);
        TextView userNameTextView = findViewById(R.id.userNameTextView);
        TextView appointmentDateTextView = findViewById(R.id.appointmentDateTextView);
        TextView appointmentTypeTextView = findViewById(R.id.appointmentTypeTextView);
        TextView currentTimeTextView = findViewById(R.id.currentTimeTextView);
        TextView bloodGroupTextView = findViewById(R.id.bloodGroupTextView);
        TextView quantificationTextView = findViewById(R.id.quantificationTextView);
        TextView indexTextView = findViewById(R.id.indexTextView);
        TextView totalAnalysisTextView = findViewById(R.id.totalAnalysisTextView);

        doctorNameTextView.setText("Doctor Name: " + doctorName);
        userNameTextView.setText("User Name: " + userName);
        appointmentDateTextView.setText("Appointment Date: " + appointmentDate);
        appointmentTypeTextView.setText("Appointment Type: " + appointmentType);
        currentTimeTextView.setText("Current Time: " + currentTime);
        bloodGroupTextView.setText("Blood Group: " + bloodGroup);
        quantificationTextView.setText("Quantification: " + quantification);
        indexTextView.setText("Index: " + index);
        totalAnalysisTextView.setText("Total Analysis: " + totalAnalysis);
        Button viewPrescriptionsButton = findViewById(R.id.viewPrescriptionsButton);
        viewPrescriptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd", Locale.getDefault());
                String currentTime = sdf.format(calendar.getTime());

                // Chuyển sang trang PrescriptionsActivity và truyền dữ liệu
                Intent intent = new Intent(DoctorDetailResultActivity.this, PrescriptionsActivity.class);
                intent.putExtra("doctorName", doctorName);
                intent.putExtra("userName", userName);
                intent.putExtra("appointmentDate", appointmentDate);
                intent.putExtra("appointmentType", appointmentType);
                intent.putExtra("currentTime", currentTime);
                intent.putExtra("userId", userId);
                intent.putExtra("doctorId", doctorId);



                startActivity(intent);
            }
        });
    }
}
