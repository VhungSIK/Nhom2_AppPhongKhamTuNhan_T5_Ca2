package com.example.doandidong.user.billusser;

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
import com.example.doandidong.employee.accountant.DetailPrescriptionUserActivity;
import com.example.doandidong.employee.accountant.ListPrescriptionUserActivity;
import com.example.doandidong.employee.accountant.PrescriptionAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BillUserActivity extends AppCompatActivity {
    private RecyclerView recyclerViewbill;
    private BillUserAdapter adapter;
    private FirebaseFirestore db;
    private String UserId;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_user);
        recyclerViewbill = findViewById(R.id.recyclerViewbill);
        recyclerViewbill.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        UserId=auth.getCurrentUser().getUid();

        Log.d("ListPrescriptionUser", "User ID: " + UserId);

        // Fetch prescription data from Firestore and populate RecyclerView
        fetchPrescriptionData();
    }

    private void fetchPrescriptionData() {
        CollectionReference prescriptionsCollection = db.collection("BillId");

        // Query to fetch prescriptions where userId matches the logged-in userId
        prescriptionsCollection.whereEqualTo("userId", UserId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Prescription> prescriptions = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Prescription prescription = document.toObject(Prescription.class);
                                prescriptions.add(prescription);
                            }
                            Log.d("PrescriptionActivity", "Prescriptions fetched successfully: " + prescriptions.size());

                            // Create and set the adapter with fetched data
                            adapter = new BillUserAdapter(prescriptions, UserId, "");
                            recyclerViewbill.setAdapter(adapter);

                            adapter.setOnItemClickListener(new BillUserAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(String billId) {
                                    Intent intent = new Intent(BillUserActivity.this, DetailsBillUserActivity.class);
                                    intent.putExtra("billId", billId); // Pass the billId to DetailsBillUserActivity
                                    startActivity(intent);
                                    Log.d("ListPrescriptionUser", "Clicked prescriptionId: " + billId);

                                }
                            });


                        } else {
                            Log.d("PrescriptionActivity", "Error getting prescriptions: ", task.getException());
                            Toast.makeText(BillUserActivity.this, "Error getting prescriptions", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}