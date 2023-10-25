package com.example.doandidong.doctor.doctor_appointment.doctor_request;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doandidong.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DoctorrequestActivity extends AppCompatActivity {
    String doctorappointmentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorrequest);
        // Nhận thông tin từ Intent
        Intent intent = getIntent();
        String doctorappointmentId = intent.getStringExtra("doctorappointmentId");
        String doctorName = intent.getStringExtra("doctorName");
        String appointmentType = intent.getStringExtra("appointmentType");
        String appointmentDate = intent.getStringExtra("appointmentDate");
        String userName = intent.getStringExtra("userName");
        String userPhone = intent.getStringExtra("userPhone");
        String userEmail = intent.getStringExtra("userEmail");
        TextView tvCurrentTime = findViewById(R.id.tvCurrentTime);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String currentTime = sdf.format(new Date());
        EditText etNote = findViewById(R.id.etNote);
        String note = etNote.getText().toString();

        tvCurrentTime.setText("Giờ hiện tại: " + currentTime);

        // Hiển thị thông tin trên giao diện
        TextView tvDoctorName = findViewById(R.id.tvDoctorName);
        tvDoctorName.setText(doctorName);

        TextView tvAppointmentType = findViewById(R.id.tvAppointmentType);
        tvAppointmentType.setText(appointmentType);

        TextView tvAppointmentDate = findViewById(R.id.tvAppointmentDate);
        tvAppointmentDate.setText(appointmentDate);

        TextView tvUserName = findViewById(R.id.tvUserName);
        tvUserName.setText(userName);

        TextView tvUserPhone = findViewById(R.id.tvUserPhone);
        tvUserPhone.setText(userPhone);

        TextView tvUserEmail = findViewById(R.id.tvUserEmail);
        tvUserEmail.setText(userEmail);
        Button btnSave = findViewById(R.id.btnSavere);

        // Set a click listener for the "Save" button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the information from the views

                String doctorName = tvDoctorName.getText().toString();
                String appointmentType = tvAppointmentType.getText().toString();
                String appointmentDate = tvAppointmentDate.getText().toString();
                String userName = tvUserName.getText().toString();
                String userPhone = tvUserPhone.getText().toString();
                String userEmail = tvUserEmail.getText().toString();

                // Get the note text from the EditText (if you have one)
                EditText etNote = findViewById(R.id.etNote);
                String note = etNote.getText().toString();

                // Save the information to Firebase
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference requestCollection = db.collection("Request");

                Map<String, Object> requestData = new HashMap<>();
                requestData.put("doctorName", doctorName);
                requestData.put("appointmentType", appointmentType);
                requestData.put("appointmentDate", appointmentDate);
                requestData.put("userName", userName);
                requestData.put("userPhone", userPhone);
                requestData.put("userEmail", userEmail);
                requestData.put("note", note);

                // Add the request to Firebase
                requestCollection.add(requestData);

                // Finish the activity and return to the previous screen
                finish();
            }
        });
    }
}