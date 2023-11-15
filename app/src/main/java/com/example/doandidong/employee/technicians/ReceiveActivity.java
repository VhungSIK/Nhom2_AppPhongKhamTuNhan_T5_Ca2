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
import com.google.firebase.firestore.QueryDocumentSnapshot;

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

        // Khởi tạo RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo Adapter trống cho RecyclerView
        requestAdapter = new RequestAdapter("", "");


        // Gắn Adapter vào RecyclerView
        recyclerView.setAdapter(requestAdapter);

        requestCollection.get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<RequestModel> requestList = new ArrayList<>();
            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                RequestModel request = documentSnapshot.toObject(RequestModel.class);

                // Assuming "userId" is the field in your Firestore document containing the User ID
                String userId = documentSnapshot.getString("userId");
                String doctorId = documentSnapshot.getString("doctorId");

                // Set the userId for each request
                request.setUserId(userId);
                request.setDoctorId(doctorId);

                requestList.add(request);
            }

            // Initialize the adapter with an empty userId (""), as the userId will be set individually for each request
            requestAdapter = new RequestAdapter("", "");

            // Set the updated request list to the adapter
            requestAdapter.setRequests(requestList);

            recyclerView.setAdapter(requestAdapter);
        }).addOnFailureListener(e -> {
            // Handle the failure
        });
    }
}
