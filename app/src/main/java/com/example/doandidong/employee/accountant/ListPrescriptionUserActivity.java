package com.example.doandidong.employee.accountant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.doandidong.R;
import com.example.doandidong.doctor.doctor_appointment.doctor_request.Prescription;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListPrescriptionUserActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PrescriptionAdapter adapter;
    private FirebaseFirestore db;
    private String UserId; // Move userId declaration here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_prescription_user);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        UserId = intent.getStringExtra("userId"); // Đảm bảo tên trường truyền đúng
        Log.d("ListPrescriptionUser", "User ID: " + UserId);

        // Fetch prescription data from Firestore and populate RecyclerView
        fetchPrescriptionData();
    }
    private void fetchPrescriptionData() {
        CollectionReference prescriptionsCollection = db.collection("Prescriptions");

        prescriptionsCollection.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Prescription> prescriptions = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Prescription prescription = document.toObject(Prescription.class);
                                prescriptions.add(prescription);
                            }
                            // Log to check if data retrieval is successful
                            Log.d("PrescriptionActivity", "Prescriptions fetched successfully: " + prescriptions.size());

                            // Update RecyclerView with the fetched data
                            adapter = new PrescriptionAdapter(prescriptions, UserId);
                            recyclerView.setAdapter(adapter);
                        } else {
                            // Log the error if data retrieval fails
                            Log.d("PrescriptionActivity", "Error getting prescriptions: ", task.getException());
                            Toast.makeText(ListPrescriptionUserActivity.this, "Error getting prescriptions", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}