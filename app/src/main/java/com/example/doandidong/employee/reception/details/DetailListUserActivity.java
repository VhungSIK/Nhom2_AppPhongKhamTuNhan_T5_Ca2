package com.example.doandidong.employee.reception.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.doandidong.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DetailListUserActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserDetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list_user);

        recyclerView = findViewById(R.id.recyclerViewUserDetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        displayUserDetails();
    }

    private void displayUserDetails() {
        Intent intent = getIntent();
        if (intent != null) {
            String userId = intent.getStringExtra("userId");

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("Appointment")
                    .whereEqualTo("Request", "wait")
                    .whereEqualTo("UserId", userId)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            ArrayList<String> userDetailsList = new ArrayList<>();
                            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                                // Log các giá trị từ Firebase để kiểm tra
                                Log.d("FirebaseData", "Document ID: " + documentSnapshot.getId());

                                String userName = documentSnapshot.getString("UserName");
                                String userPhone = documentSnapshot.getString("UserPhone");
                                String date = documentSnapshot.getString("Date");
                                String doctorName = documentSnapshot.getString("DoctorName");
                                String time = documentSnapshot.getString("Time");
                                String type = documentSnapshot.getString("Type");
                                String doctorId = documentSnapshot.getString("DoctorId");

                                Log.d("FirebaseData", "User Name: " + userName);
                                Log.d("FirebaseData", "Doctor Name: " + doctorName);

                                String userDetails = userName + " - " + userPhone + " - " + date + " - " +
                                        doctorName + " - " + time + " - " + type + " - " + doctorId;
                                userDetailsList.add(userDetails);
                            }
                            adapter = new UserDetailsAdapter(userDetailsList);
                            recyclerView.setAdapter(adapter);
                        }
                    });

        }
    }
}
