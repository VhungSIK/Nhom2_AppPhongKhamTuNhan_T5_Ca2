package com.example.doandidong.doctor.doctor_appointment.doctor_result;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.doandidong.R;
import com.example.doandidong.employee.technicians.ResultModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class DoctorListResultActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private CollectionReference resultsCollection;
    private RecyclerView recyclerView;
    private ResultAdapter resultAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list_result);

        db = FirebaseFirestore.getInstance();
        resultsCollection = db.collection("Results");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        resultAdapter = new ResultAdapter(this);
        recyclerView.setAdapter(resultAdapter);
        loadResults();
    }
    private void loadResults() {
        resultsCollection.get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<ResultModel> resultsList = new ArrayList<>();
            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                ResultModel result = documentSnapshot.toObject(ResultModel.class);
                resultsList.add(result);
            }
            resultAdapter.setResults(resultsList);
        }).addOnFailureListener(e -> {
            // Xử lý lỗi nếu cần
            Toast.makeText(DoctorListResultActivity.this, "Lỗi khi truy vấn dữ liệu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}
