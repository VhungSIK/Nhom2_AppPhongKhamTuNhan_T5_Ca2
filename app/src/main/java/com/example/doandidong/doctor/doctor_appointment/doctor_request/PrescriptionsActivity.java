package com.example.doandidong.doctor.doctor_appointment.doctor_request;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.doandidong.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class PrescriptionsActivity extends AppCompatActivity {
    private String doctorName;
    private String userName;
    private String appointmentDate;
    private String appointmentType;
    private String currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescriptions);
        Intent intent = getIntent();
        String doctorName = intent.getStringExtra("doctorName");
        String userName = intent.getStringExtra("userName");
        String appointmentDate = intent.getStringExtra("appointmentDate");
        String appointmentType = intent.getStringExtra("appointmentType");
        String currentTime = intent.getStringExtra("currentTime");

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

                // Lấy thông tin từ các TextView
                String doctorName = intent.getStringExtra("doctorName");
                String userName = intent.getStringExtra("userName");
                String appointmentDate = intent.getStringExtra("appointmentDate");
                String appointmentType = intent.getStringExtra("appointmentType");
                String currentTime = intent.getStringExtra("currentTime");


                // Tạo một đối tượng Prescription
                Prescription prescription1 = new Prescription(stt1, medicineName1, dosage1, usage1, route1, days1,
                        doctorName, userName, appointmentDate, appointmentType, currentTime);

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference prescriptionCollection = db.collection("Prescriptions");

                // Sử dụng `add()` để thêm đối tượng Prescription vào Firestore collection
                prescriptionCollection.add(prescription1);

                sttEditText1.setText("");
                medicineNameEditText1.setText("");
                dosageEditText1.setText("");
                usageEditText1.setText("");
                routeEditText1.setText("");
                daysEditText1.setText("");
            }
        });


        // Đặt thông tin vào TextView
        doctorNameTextView.setText("Doctor Name: " + doctorName);
        userNameTextView.setText("User Name: " + userName);
        appointmentDateTextView.setText("Appointment Date: " + appointmentDate);
        appointmentTypeTextView.setText("Appointment Type: " + appointmentType);
        currentTimeTextView.setText("Current Time: " + currentTime);
    }
}
