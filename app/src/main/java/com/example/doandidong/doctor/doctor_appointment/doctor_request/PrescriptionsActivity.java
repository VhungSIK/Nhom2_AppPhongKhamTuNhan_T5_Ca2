package com.example.doandidong.doctor.doctor_appointment.doctor_request;

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
import com.example.doandidong.doctor.doctor_appointment.doctor_result.DoctorListResultActivity;
import com.example.doandidong.employee.accountant.ListPrescriptionUserActivity;
import com.example.doandidong.employee.technicians.ReceiveActivity;
import com.example.doandidong.employee.technicians.SendRequireActivity;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PrescriptionsActivity extends AppCompatActivity {
    private String doctorName;
    private String userName;
    private String doctorId;
    private String appointmentDate;
    private String appointmentType;
    private String currentTime;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescriptions);
        Intent intent = getIntent();
        doctorName = intent.getStringExtra("doctorName");
        userName = intent.getStringExtra("userName");
        appointmentDate = intent.getStringExtra("appointmentDate");
        appointmentType = intent.getStringExtra("appointmentType");
        currentTime = intent.getStringExtra("currentTime");
        userId = intent.getStringExtra("userId");
        doctorId = intent.getStringExtra("doctorId");
        Log.d("Pre", "userId: " + userId);
        Log.d("Pre", "sssss: " + doctorId);
        // Tham chiếu đến các TextView trong layout XML
        TextView doctorNameTextView = findViewById(R.id.doctorNameTextView);
        TextView userNameTextView = findViewById(R.id.userNameTextView);
        TextView appointmentDateTextView = findViewById(R.id.appointmentDateTextView);
        TextView appointmentTypeTextView = findViewById(R.id.appointmentTypeTextView);
        TextView currentTimeTextView = findViewById(R.id.currentTimeTextView);

        //kê đơn
        EditText sttEditText1 = findViewById(R.id.sttEditText1);
        EditText medicineNameEditText1 = findViewById(R.id.medicineNameEditText1);
        EditText dosageEditText1 = findViewById(R.id.dosageEditText1);
        EditText usageEditText1 = findViewById(R.id.usageEditText1);
        EditText routeEditText1 = findViewById(R.id.routeEditText1);
        EditText daysEditText1 = findViewById(R.id.daysEditText1);
        //2
        EditText sttEditText2 = findViewById(R.id.sttEditText2);
        EditText medicineNameEditText2 = findViewById(R.id.medicineNameEditText2);
        EditText dosageEditText2 = findViewById(R.id.dosageEditText2);
        EditText usageEditText2 = findViewById(R.id.usageEditText2);
        EditText routeEditText2 = findViewById(R.id.routeEditText2);
        EditText daysEditText2 = findViewById(R.id.daysEditText2);
        //3
        EditText sttEditText3 = findViewById(R.id.sttEditText3);
        EditText medicineNameEditText3 = findViewById(R.id.medicineNameEditText3);
        EditText dosageEditText3 = findViewById(R.id.dosageEditText3);
        EditText usageEditText3 = findViewById(R.id.usageEditText3);
        EditText routeEditText3 = findViewById(R.id.routeEditText3);
        EditText daysEditText3 = findViewById(R.id.daysEditText3);
        //4
        EditText sttEditText4 = findViewById(R.id.sttEditText4);
        EditText medicineNameEditText4 = findViewById(R.id.medicineNameEditText4);
        EditText dosageEditText4 = findViewById(R.id.dosageEditText4);
        EditText usageEditText4 = findViewById(R.id.usageEditText4);
        EditText routeEditText4 = findViewById(R.id.routeEditText4);
        EditText daysEditText4 = findViewById(R.id.daysEditText4);
        //5
        EditText sttEditText5 = findViewById(R.id.sttEditText5);
        EditText medicineNameEditText5 = findViewById(R.id.medicineNameEditText5);
        EditText dosageEditText5 = findViewById(R.id.dosageEditText5);
        EditText usageEditText5 = findViewById(R.id.usageEditText5);
        EditText routeEditText5 = findViewById(R.id.routeEditText5);
        EditText daysEditText5 = findViewById(R.id.daysEditText5);
        Button saveButton1 = findViewById(R.id.saveButton1);

        saveButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Lấy thông tin từ các EditText
                String stt1 = sttEditText1.getText().toString();
                String medicineName1 = medicineNameEditText1.getText().toString();
                String dosage1 = dosageEditText1.getText().toString();
                String usage1 = usageEditText1.getText().toString();
                String route1 = routeEditText1.getText().toString();
                String days1 = daysEditText1.getText().toString();
                //2
                String stt2 = sttEditText2.getText().toString();
                String medicineName2 = medicineNameEditText2.getText().toString();
                String dosage2 = dosageEditText2.getText().toString();
                String usage2 = usageEditText2.getText().toString();
                String route2 = routeEditText2.getText().toString();
                String days2 = daysEditText2.getText().toString();
                //3
                String stt3 = sttEditText3.getText().toString();
                String medicineName3 = medicineNameEditText3.getText().toString();
                String dosage3 = dosageEditText3.getText().toString();
                String usage3 = usageEditText3.getText().toString();
                String route3 = routeEditText3.getText().toString();
                String days3 = daysEditText3.getText().toString();
                //4
                String stt4 = sttEditText4.getText().toString();
                String medicineName4 = medicineNameEditText4.getText().toString();
                String dosage4 = dosageEditText4.getText().toString();
                String usage4 = usageEditText4.getText().toString();
                String route4 = routeEditText4.getText().toString();
                String days4 = daysEditText4.getText().toString();
                //5
                String stt5 = sttEditText5.getText().toString();
                String medicineName5 = medicineNameEditText5.getText().toString();
                String dosage5 = dosageEditText5.getText().toString();
                String usage5 = usageEditText5.getText().toString();
                String route5 = routeEditText5.getText().toString();
                String days5 = daysEditText5.getText().toString();

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference prescriptionCollection = db.collection("Prescriptions");

                Map<String, Object> prescriptionData = new HashMap<>();
                prescriptionData.put("stt1", stt1);
                prescriptionData.put("medicineName1", medicineName1);
                prescriptionData.put("dosage1", dosage1);
                prescriptionData.put("usage1", usage1);
                prescriptionData.put("route1", route1);
                prescriptionData.put("days1", days1);
                //2
                prescriptionData.put("stt2", stt2);
                prescriptionData.put("medicineName2", medicineName2);
                prescriptionData.put("dosage2", dosage2);
                prescriptionData.put("usage2", usage2);
                prescriptionData.put("route2", route2);
                prescriptionData.put("days2", days2);
                //3
                prescriptionData.put("stt3", stt3);
                prescriptionData.put("medicineName3", medicineName3);
                prescriptionData.put("dosage3", dosage3);
                prescriptionData.put("usage3", usage3);
                prescriptionData.put("route3", route3);
                prescriptionData.put("days3", days3);
                //4
                prescriptionData.put("stt4", stt4);
                prescriptionData.put("medicineName4", medicineName4);
                prescriptionData.put("dosage4", dosage4);
                prescriptionData.put("usage4", usage4);
                prescriptionData.put("route4", route4);
                prescriptionData.put("days4", days4);
                //5
                prescriptionData.put("stt5", stt5);
                prescriptionData.put("medicineName5", medicineName5);
                prescriptionData.put("dosage5", dosage5);
                prescriptionData.put("usage5", usage5);
                prescriptionData.put("route5", route5);
                prescriptionData.put("days5", days5);
                prescriptionData.put("doctorName", doctorName);
                prescriptionData.put("userName", userName);
                prescriptionData.put("appointmentDate", appointmentDate);
                prescriptionData.put("appointmentType", appointmentType);
                prescriptionData.put("currentTime", currentTime);
                prescriptionData.put("userId", userId);
                prescriptionData.put("doctorId", doctorId);

                prescriptionCollection.add(prescriptionData)
                        .addOnSuccessListener(documentReference -> {
                            // Đã lưu đơn thuốc thành công
                            Toast.makeText(PrescriptionsActivity.this, "Đơn thuốc đã được lưu thành công.", Toast.LENGTH_SHORT).show();
                            Intent receiveIntent = new Intent(PrescriptionsActivity.this, DoctorListResultActivity.class);
                            receiveIntent.putExtra("userId", userId);
                            if (intent != null) {
                                String userId = intent.getStringExtra("userId");
                                if (userId != null) {
                                    Intent listPrescriptionIntent = new Intent(PrescriptionsActivity.this, ListPrescriptionUserActivity.class);
                                    listPrescriptionIntent.putExtra("userId", userId);
                                    startActivity(listPrescriptionIntent);
                                }
                            }
                            startActivity(receiveIntent);
                        })
                        .addOnFailureListener(e -> {
                            // Xảy ra lỗi khi lưu đơn thuốc
                            Toast.makeText(PrescriptionsActivity.this, "Lỗi khi lưu đơn thuốc: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });


        // Đặt thông tin vào TextView
        doctorNameTextView.setText(doctorName);
        userNameTextView.setText(userName);
        appointmentDateTextView.setText(appointmentDate);
        appointmentTypeTextView.setText( appointmentType);
        currentTimeTextView.setText(currentTime);
    }
}
