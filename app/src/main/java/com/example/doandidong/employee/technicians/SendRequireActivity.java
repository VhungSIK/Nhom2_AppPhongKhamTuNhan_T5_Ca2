package com.example.doandidong.employee.technicians;

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
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SendRequireActivity extends AppCompatActivity {
    private TextView doctorNameTextView, userNameTextView, appointmentDateTextView, appointmentTypeTextView, currentTimeTextView;
    private EditText bloodGroupEditText, quantificationEditText, indexEditText, totalAnalysisEditText;
    private Button saveButton;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_require);

        db = FirebaseFirestore.getInstance();

        Intent i = getIntent();
        String doctorName = i.getStringExtra("doctorName");
        String appointmentType = i.getStringExtra("appointmentType");
        String appointmentDate = i.getStringExtra("appointmentDate");
        String userName = i.getStringExtra("userName");

        // Initialize views
        currentTimeTextView = findViewById(R.id.currentTimeTextView);
        bloodGroupEditText = findViewById(R.id.bloodGroupEditText);
        quantificationEditText = findViewById(R.id.quantificationEditText);
        indexEditText = findViewById(R.id.indexEditText);
        totalAnalysisEditText = findViewById(R.id.totalAnalysisEditText);
        saveButton = findViewById(R.id.saveButton);

        // Display information from the previous page
        doctorNameTextView = findViewById(R.id.doctorNameTextView);
        appointmentTypeTextView = findViewById(R.id.appointmentTypeTextView);
        appointmentDateTextView = findViewById(R.id.appointmentDateTextView);
        userNameTextView = findViewById(R.id.userNameTextView);

        // Set current time
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String currentTime = sdf.format(new Date());
        currentTimeTextView.setText("Current Time: " + currentTime);

        doctorNameTextView.setText(doctorName);
        appointmentTypeTextView.setText(appointmentType);
        appointmentDateTextView.setText(appointmentDate);
        userNameTextView.setText(userName);

        String userId = i.getStringExtra("UserId");
        String doctorId = i.getStringExtra("doctorId");
        Log.d("SendRequireActivity", "User ID1: " + userId);
        Log.d("SendRequireActivity", "Usersssssdd ID1: " + doctorId);

        // Code trong onCreate()
        saveButton.setOnClickListener(view -> {
            String bloodGroup = bloodGroupEditText.getText().toString();
            String quantification = quantificationEditText.getText().toString();
            String index = indexEditText.getText().toString();
            String totalAnalysis = totalAnalysisEditText.getText().toString();

            ResultModel result = new ResultModel(
                    doctorNameTextView.getText().toString(),
                    doctorId, // Sử dụng doctorId được truyền từ Intent
                    userNameTextView.getText().toString(),
                    appointmentDateTextView.getText().toString(),
                    appointmentTypeTextView.getText().toString(),
                    currentTime,
                    bloodGroup,
                    quantification,
                    index,
                    totalAnalysis,
                    userId // Sử dụng userId được truyền từ Intent
            );

            db.collection("Results")
                    .add(result)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(SendRequireActivity.this, "Kết quả xét nghiệm đã gửi thành công.", Toast.LENGTH_SHORT).show();
                        Intent receiveIntent = new Intent(SendRequireActivity.this, ReceiveActivity.class);
                        startActivity(receiveIntent);
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(SendRequireActivity.this, "Lỗi khi lưu kết quả xét nghiệm: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });

    }
}