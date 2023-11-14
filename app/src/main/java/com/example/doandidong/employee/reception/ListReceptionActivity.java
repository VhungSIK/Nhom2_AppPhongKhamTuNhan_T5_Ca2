package com.example.doandidong.employee.reception;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.doandidong.R;
import com.example.doandidong.employee.reception.details.DetailListUserActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashSet;

public class ListReceptionActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private ArrayList<String> bookedUsersInfo;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reception);

        db = FirebaseFirestore.getInstance();
        bookedUsersInfo = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewBookedUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        displayBookedUsers();
    }

    private HashSet<String> uniqueUserIds = new HashSet<>();

    private void displayBookedUsers() {
        db.collection("Appointment")
                .whereEqualTo("Request", "wait")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                            String userId = document.getString("UserId");
                            Log.d("UserID", userId);

                            if (!uniqueUserIds.contains(userId)) {
                                uniqueUserIds.add(userId);

                                db.collection("User").document(userId)
                                        .get()
                                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                            @Override
                                            public void onSuccess(DocumentSnapshot userDocument) {
                                                if (userDocument.exists()) {
                                                    String userName = userDocument.getString("Name");
                                                    String userEmail = userDocument.getString("Email");
                                                    String userInfo = userName + " - " + userEmail + " - " + userId;
                                                    bookedUsersInfo.add(userInfo);

                                                    displayUserInfo();
                                                }
                                            }
                                        });
                            }
                        }
                    }
                });
    }

    private void displayUserInfo() {
        ReceptionAdapter adapter = new ReceptionAdapter(bookedUsersInfo);
        adapter.setOnItemClickListener(new ReceptionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String userId, String userName, String userEmail) {
                Intent intent = new Intent(ListReceptionActivity.this, DetailListUserActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("userName", userName);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}