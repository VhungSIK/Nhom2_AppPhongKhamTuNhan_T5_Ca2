package com.example.doandidong.doctor.doctor_appointment.doctor_request;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        getSupportActionBar().setTitle("Phiếu yêu cầu xét nghiệm");
        Intent intent = getIntent();
        String userId = getIntent().getStringExtra("userId");
        String doctorappointmentId = intent.getStringExtra("doctorappointmentId");
        String doctorId = intent.getStringExtra("doctorId");
        String doctorName = intent.getStringExtra("doctorName");
        String appointmentType = intent.getStringExtra("appointmentType");
        String appointmentDate = intent.getStringExtra("appointmentDate");
        String userName = intent.getStringExtra("userName");
        String userPhone = intent.getStringExtra("userPhone");
        TextView tvCurrentTime = findViewById(R.id.tvCurrentTime);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String currentTime = sdf.format(new Date());
        EditText etNote = findViewById(R.id.etNote);
        String note = etNote.getText().toString();

        tvCurrentTime.setText(currentTime);

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
        TextView btnSave = findViewById(R.id.btnSavere);
        Log.d("DoctorrequestActivity", "dddoctorId: " + doctorId);

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
                EditText etNote = findViewById(R.id.etNote);
                String note = etNote.getText().toString();
                String userId = getIntent().getStringExtra("userId");
                Log.d("DoctorrequestActivity", "doctorId: " + doctorId);

                // Firebase setup
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference requestCollection = db.collection("Request");

                // Prepare request data
                Map<String, Object> requestData = new HashMap<>();
                requestData.put("doctorappointmentId", doctorappointmentId);
                requestData.put("doctorName", doctorName);
                requestData.put("appointmentType", appointmentType);
                requestData.put("appointmentDate", appointmentDate);
                requestData.put("userName", userName);
                requestData.put("userPhone", userPhone);
                requestData.put("note", note);
                requestData.put("doctorId", doctorId);
                requestData.put("userId", userId);
                requestCollection.add(requestData)
                        .addOnSuccessListener(documentReference -> {
                            // Yêu cầu xét nghiệm đã được lưu thành công
                            Toast.makeText(DoctorrequestActivity.this, "Yêu cầu xét nghiệm đã được gửi đến kĩ thuật viên.", Toast.LENGTH_SHORT).show();

                            // Sau khi lưu yêu cầu xét nghiệm thành công, bạn có thể cập nhật bất kỳ thông tin nào khác tại đây.

                            db.collection("Appointment")
                                    .document(doctorappointmentId)
                                    .update("Request", "complete")
                                    .addOnSuccessListener(aVoid -> {
                                        // Xử lý thành công khi cập nhật thông tin trong "Appointment"
                                    })
                                    .addOnFailureListener(e -> {
                                        // Xảy ra lỗi khi cập nhật thông tin trong "Appointment"
                                        Toast.makeText(DoctorrequestActivity.this, "Lỗi khi cập nhật thông tin trong Appointment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    });

                            // Cuối cùng, khi bạn đã hoàn thành tất cả công việc, bạn có thể kết thúc Activity.
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            // Xảy ra lỗi khi lưu yêu cầu xét nghiệm
                            Toast.makeText(DoctorrequestActivity.this, "Lỗi khi lưu yêu cầu xét nghiệm: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}