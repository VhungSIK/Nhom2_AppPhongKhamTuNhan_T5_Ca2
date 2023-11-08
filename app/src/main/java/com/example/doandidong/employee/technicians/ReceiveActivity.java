package com.example.doandidong.employee.technicians;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.doandidong.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ReceiveActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private CollectionReference requestCollection;
    private RecyclerView recyclerView;
    private RequestAdapter requestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        // Kết nối với Firebase
        db = FirebaseFirestore.getInstance();
        requestCollection = db.collection("Request");

        // Khởi tạo RecyclerView và Adapter
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        requestAdapter = new RequestAdapter();

        // Kết nối RecyclerView với Adapter
        recyclerView.setAdapter(requestAdapter);

        // Truy vấn dữ liệu từ Firebase và cập nhật Adapter
        requestCollection.get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<RequestModel> requestList = new ArrayList<>();
            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                RequestModel request = documentSnapshot.toObject(RequestModel.class);
                requestList.add(request);
            }
            requestAdapter.setRequests(requestList);
        }).addOnFailureListener(e -> {
            // Xử lý lỗi nếu cần
            Toast.makeText(ReceiveActivity.this, "Lỗi khi truy vấn dữ liệu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}
