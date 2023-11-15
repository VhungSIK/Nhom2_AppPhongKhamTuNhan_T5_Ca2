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

        // Tham chiếu đến các View
        Intent i = getIntent();
        String doctorName = i.getStringExtra("doctorName");
        String appointmentType = i.getStringExtra("appointmentType");
        String appointmentDate = i.getStringExtra("appointmentDate");
        String userName = i.getStringExtra("userName");
        String note = i.getStringExtra("note");
        currentTimeTextView = findViewById(R.id.currentTimeTextView);
        bloodGroupEditText = findViewById(R.id.bloodGroupEditText);
        quantificationEditText = findViewById(R.id.quantificationEditText);
        indexEditText = findViewById(R.id.indexEditText);
        totalAnalysisEditText = findViewById(R.id.totalAnalysisEditText);
        saveButton = findViewById(R.id.saveButton);

        // Hiển thị thông tin từ trang trước
        TextView doctorNameTextView = findViewById(R.id.doctorNameTextView);
        TextView appointmentTypeTextView = findViewById(R.id.appointmentTypeTextView);
        TextView appointmentDateTextView = findViewById(R.id.appointmentDateTextView);
        TextView userNameTextView = findViewById(R.id.userNameTextView);

        // Lấy và hiển thị giờ hiện tại
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String currentTime = sdf.format(new Date());
        currentTimeTextView.setText("Current Time: " + currentTime);

// Trong các dòng sau, sử dụng các biến đã khai báo từ trước
        doctorNameTextView.setText(doctorName);
        appointmentTypeTextView.setText(appointmentType);
        appointmentDateTextView.setText(appointmentDate);
        userNameTextView.setText(userName);
        String UserId = i.getStringExtra("UserId");
        String doctorId = i.getStringExtra("doctorId");
        Log.d("SendRequireActivity", "User ID1: " + UserId);
        Log.d("SendRequireActivity", "Usersssssdd ID1: " + doctorId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy dữ liệu từ các trường nhập
                String bloodGroup = bloodGroupEditText.getText().toString();
                String quantification = quantificationEditText.getText().toString();
                String index = indexEditText.getText().toString();
                String totalAnalysis = totalAnalysisEditText.getText().toString();
                Intent i = new Intent(SendRequireActivity.this, SendRequireActivity.class);
                i.putExtra("UserId", UserId);
                i.putExtra("doctorId", doctorId);
                Log.d("SendRequireActivity", "User ID2: " + UserId);
                Log.d("SendRequireActivity", "User ID2dco: " + doctorId);
                // Tạo đối tượng ResultModel và thêm thuộc tính "request"
                ResultModel result = new ResultModel(doctorNameTextView.getText().toString(),
                        userNameTextView.getText().toString(),
                        appointmentDateTextView.getText().toString(),
                        appointmentTypeTextView.getText().toString(),
                        currentTime,
                        bloodGroup,
                        quantification,
                        index,
                        totalAnalysis,
                        doctorId, // Sử dụng doctorId lấy từ Intent thay vì UserId
                        UserId);


                // Thêm đối tượng dữ liệu vào Firebase Firestore
                db.collection("Results")
                        .add(result)
                        .addOnSuccessListener(documentReference -> {
                            // Kết quả xét nghiệm đã được lưu thành công
                            Toast.makeText(SendRequireActivity.this, "Kết quả xét nghiệm đã gửi thành công.", Toast.LENGTH_SHORT).show();

                            // Chuyển sang trang ReceiveActivity
                            Intent receiveIntent = new Intent(SendRequireActivity.this, ReceiveActivity.class);
                            startActivity(receiveIntent);
                        })
                        .addOnFailureListener(e -> {
                            // Xảy ra lỗi khi lưu kết quả xét nghiệm
                            Toast.makeText(SendRequireActivity.this, "Lỗi khi lưu kết quả xét nghiệm: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });

    }
}
