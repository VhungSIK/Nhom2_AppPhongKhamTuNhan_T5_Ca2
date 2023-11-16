package com.example.doandidong.user.billusser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doandidong.R;
import com.example.doandidong.employee.accountant.DetailPrescriptionUserActivity;
import com.example.doandidong.employee.accountant.ListPrescriptionUserActivity;
import com.example.doandidong.employee.accountant.PrescriptionModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class DetailsBillUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_bill_user);
        TextView doctorNameTextView = findViewById(R.id.doctorNameTextView);
        TextView userNameTextView = findViewById(R.id.userNameTextView);
        TextView appointmentDateTextView = findViewById(R.id.appointmentDateTextView);
        TextView appointmentTypeTextView = findViewById(R.id.appointmentTypeTextView);
        TextView currentTimeTextView = findViewById(R.id.currentTimeTextView);
        TextView userid = findViewById(R.id.userid);
//start

        //kê đơn
        TextView sttEditText1 = findViewById(R.id.sttEditText1);
        TextView medicineNameEditText1 = findViewById(R.id.medicineNameEditText1);
        TextView dosageEditText1 = findViewById(R.id.dosageEditText1);
        TextView usageEditText1 = findViewById(R.id.usageEditText1);
        TextView routeEditText1 = findViewById(R.id.routeEditText1);
        TextView daysEditText1 = findViewById(R.id.daysEditText1);
        //2
        TextView sttEditText2 = findViewById(R.id.sttEditText2);
        TextView medicineNameEditText2 = findViewById(R.id.medicineNameEditText2);
        TextView dosageEditText2 = findViewById(R.id.dosageEditText2);
        TextView usageEditText2 = findViewById(R.id.usageEditText2);
        TextView routeEditText2 = findViewById(R.id.routeEditText2);
        TextView daysEditText2 = findViewById(R.id.daysEditText2);
        //3
        TextView sttEditText3 = findViewById(R.id.sttEditText3);
        TextView medicineNameEditText3 = findViewById(R.id.medicineNameEditText3);
        TextView dosageEditText3 = findViewById(R.id.dosageEditText3);
        TextView usageEditText3 = findViewById(R.id.usageEditText3);
        TextView routeEditText3 = findViewById(R.id.routeEditText3);
        TextView daysEditText3 = findViewById(R.id.daysEditText3);
        //4
        TextView sttEditText4 = findViewById(R.id.sttEditText4);
        TextView medicineNameEditText4 = findViewById(R.id.medicineNameEditText4);
        TextView dosageEditText4 = findViewById(R.id.dosageEditText4);
        TextView usageEditText4 = findViewById(R.id.usageEditText4);
        TextView routeEditText4 = findViewById(R.id.routeEditText4);
        TextView daysEditText4 = findViewById(R.id.daysEditText4);
        //5
        TextView sttEditText5 = findViewById(R.id.sttEditText5);
        TextView medicineNameEditText5 = findViewById(R.id.medicineNameEditText5);
        TextView dosageEditText5 = findViewById(R.id.dosageEditText5);
        TextView usageEditText5 = findViewById(R.id.usageEditText5);
        TextView routeEditText5 = findViewById(R.id.routeEditText5);
        TextView daysEditText5 = findViewById(R.id.daysEditText5);
        TextView totalTextView = findViewById(R.id.totalTextView);
        Button saveButtonbill = findViewById(R.id.saveButtonbill);

        saveButtonbill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailsBillUserActivity.this, UserPayActivity.class);
                intent.putExtra("userId", userid.getText().toString());
                intent.putExtra("userName", userNameTextView.getText().toString());
                intent.putExtra("total", totalTextView.getText().toString());

                startActivity(intent);

            }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        if (intent != null) {
            String receivedBillId = intent.getStringExtra("billId");
            if (receivedBillId != null) {
                CollectionReference billsCollection = db.collection("BillId");
                DocumentReference billRef = billsCollection.document(receivedBillId);

                billRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                PrescriptionModel prescription = document.toObject(PrescriptionModel.class);

                                if (prescription != null) {
                                                // Gán dữ liệu lấy được từ Firestore vào các TextView
                                                doctorNameTextView.setText(prescription.getDoctorName());
                                                userNameTextView.setText(prescription.getUserName());
                                                appointmentDateTextView.setText(prescription.getAppointmentDate());
                                                appointmentTypeTextView.setText(prescription.getAppointmentType());
                                                currentTimeTextView.setText(prescription.getCurrentTime());
                                                userid.setText(prescription.getUserId());


                                                sttEditText1.setText(prescription.getMedicineName1());
                                                medicineNameEditText1.setText(prescription.getDosage1());
                                                usageEditText1.setText(prescription.getUsage1());
                                                routeEditText1.setText(prescription.getRoute1());
                                                daysEditText1.setText(prescription.getDays1());
                                                dosageEditText1.setText(prescription.getDosage1());

                                                sttEditText2.setText(prescription.getMedicineName2());
                                                medicineNameEditText2.setText(prescription.getDosage2());
                                                usageEditText2.setText(prescription.getUsage2());
                                                routeEditText2.setText(prescription.getRoute2());
                                                daysEditText2.setText(prescription.getDays2());
                                                dosageEditText2.setText(prescription.getDosage2());

                                                sttEditText3.setText(prescription.getMedicineName3());
                                                medicineNameEditText3.setText(prescription.getDosage3());
                                                usageEditText3.setText(prescription.getUsage3());
                                                routeEditText3.setText(prescription.getRoute3());
                                                daysEditText3.setText(prescription.getDays3());
                                                dosageEditText3.setText(prescription.getDosage3());

                                                sttEditText4.setText(prescription.getMedicineName4());
                                                medicineNameEditText4.setText(prescription.getDosage4());
                                                usageEditText4.setText(prescription.getUsage4());
                                                routeEditText4.setText(prescription.getRoute4());
                                                daysEditText4.setText(prescription.getDays4());
                                                dosageEditText4.setText(prescription.getDosage4());

                                                sttEditText5.setText(prescription.getMedicineName5());
                                                medicineNameEditText5.setText(prescription.getDosage5());
                                                usageEditText5.setText(prescription.getUsage5());
                                                routeEditText5.setText(prescription.getRoute5());
                                                daysEditText5.setText(prescription.getDays5());
                                                dosageEditText5.setText(prescription.getDosage5());
                                    totalTextView.setText(prescription.getTotal());

                                                // Hiển thị dữ liệu lên giao diện người dùng
                                            }
                            } else {
                                Log.d("DetailPrescriptionUser", "No such document");
                            }
                        } else {
                            Log.d("DetailPrescriptionUser", "get failed with ", task.getException());
                        }
                    }
                });
            }
        }
    }
}