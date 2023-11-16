package com.example.doandidong.user.billusser;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doandidong.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UserPayActivity extends AppCompatActivity {
    private ArrayList<String> usedPayIds = new ArrayList<>(); // Danh sách lưu trữ các payId đã sử dụng
    TextView newInfoEditText1, newInfoEditText2;
    private FirebaseFirestore firestoreDB;

    // Hàm để tạo payId ngẫu nhiên và duy nhất
    private String generatePayId() {
        Random random = new Random();
        StringBuilder payIdBuilder = new StringBuilder();
        String newPayId;

        do {
            payIdBuilder.setLength(0); // Đặt lại chuỗi payIdBuilder
            // Tạo chuỗi 6 số ngẫu nhiên
            for (int i = 0; i < 6; i++) {
                int digit = random.nextInt(10); // Sinh số ngẫu nhiên từ 0 đến 9
                payIdBuilder.append(digit);
            }

            newPayId = payIdBuilder.toString();
        } while (usedPayIds.contains(newPayId)); // Kiểm tra xem payId đã tồn tại hay chưa

        usedPayIds.add(newPayId); // Thêm payId mới vào danh sách đã sử dụng
        return newPayId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pay);
        Intent intent = getIntent();
        if (intent != null) {
            String userId = intent.getStringExtra("userId");
            String userName = intent.getStringExtra("userName");
            String total = intent.getStringExtra("total");

            // Tạo payId ngẫu nhiên và duy nhất
            String payId = generatePayId();
            firestoreDB = FirebaseFirestore.getInstance();
            // Hiển thị dữ liệu đã nhận được và payId trong TextViews
            TextView userIdTextView = findViewById(R.id.userIdTextView);
            TextView userNameTextView = findViewById(R.id.userNameTextView);
            TextView totalTextView = findViewById(R.id.totalTextView);
            TextView payIdTextView = findViewById(R.id.payIdTextView);
            newInfoEditText1 = findViewById(R.id.newInfoEditText1);
            newInfoEditText1.setInputType(InputType.TYPE_CLASS_NUMBER);
            newInfoEditText2 = findViewById(R.id.newInfoEditText1);
            newInfoEditText2.setInputType(InputType.TYPE_CLASS_NUMBER);


            userIdTextView.setText("User ID: " + userId);
            userNameTextView.setText("User Name: " + userName);
            totalTextView.setText("Total: " + total);
            payIdTextView.setText("Pay ID: " + payId);
            Button saveButton = findViewById(R.id.saveButton);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveDataToFirestore();
                }
            });
        }
    }
        private void saveDataToFirestore () {
            String userId = getIntent().getStringExtra("userId");
            String billId = getIntent().getStringExtra("billId");
            String userName = getIntent().getStringExtra("userName");
            String total = getIntent().getStringExtra("total");
            String payId = ((TextView) findViewById(R.id.payIdTextView)).getText().toString();
            String newInfo1 = newInfoEditText1.getText().toString();
            String newInfo2 = newInfoEditText2.getText().toString();

            // Tạo một object Map để lưu dữ liệu
            Map<String, Object> userPayInfo = new HashMap<>();
            userPayInfo.put("userId", userId);
            userPayInfo.put("billId", billId);
            userPayInfo.put("userName", userName);
            userPayInfo.put("total", total);
            userPayInfo.put("payId", payId);
            userPayInfo.put("newInfo1", newInfo1);
            userPayInfo.put("newInfo2", newInfo2);

            // Lưu dữ liệu lên Firestore
            firestoreDB.collection("Userpay")
                    .document(payId)
                    .set(userPayInfo)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Thực hiện khi lưu thành công
                            Toast.makeText(UserPayActivity.this, "Data saved to Firestore", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Xử lý khi lưu thất bại
                            Toast.makeText(UserPayActivity.this, "Failed to save data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
}