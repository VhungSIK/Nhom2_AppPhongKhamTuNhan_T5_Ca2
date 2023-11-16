package com.example.doandidong.employee.accountant;

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
import com.example.doandidong.doctor.doctor_appointment.doctor_request.Prescription;
import com.example.doandidong.doctor.doctor_appointment.doctor_request.PrescriptionsActivity;
import com.example.doandidong.doctor.doctor_appointment.doctor_result.DoctorListResultActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class DetailPrescriptionUserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_prescription_user);

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
        TextView  medicineNameEditText4 = findViewById(R.id.medicineNameEditText4);
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
// stop
        EditText price1 = findViewById(R.id.price1);
        EditText price2 = findViewById(R.id.price2);
        EditText price3 = findViewById(R.id.price3);
        EditText price4 = findViewById(R.id.price4);
        EditText price5 = findViewById(R.id.price5);
        TextView totalTextView = findViewById(R.id.totalTextView);
        Button saveButtonbill = findViewById(R.id.saveButtonbill);
        EditText[] priceEditTexts = new EditText[]{price1, price2, price3, price4, price5};
        for (EditText editText : priceEditTexts) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // Không cần làm gì ở đây
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // Không cần làm gì ở đây
                }

                @Override
                public void afterTextChanged(Editable s) {
                    // Tính toán tổng mỗi khi giá trị thay đổi
                    double totalValue = 0;
                    for (EditText editText : priceEditTexts) {
                        String price = editText.getText().toString();
                        // Kiểm tra nếu EditText có giá trị thì tính vào tổng
                        if (!price.isEmpty()) {
                            totalValue += Double.parseDouble(price);
                        }
                    }
                    // Hiển thị tổng số tiền trên TextView
                    DecimalFormat formatter = new DecimalFormat("#,### VND");
                    String totalString = "Total: " + formatter.format(totalValue);
                    totalTextView.setText(totalString);

                }
            });
        }

        saveButtonbill.setOnClickListener(new View.OnClickListener() {
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

                String doctorName = doctorNameTextView.getText().toString();
                String userName = userNameTextView.getText().toString();
                String appointmentDate = appointmentDateTextView.getText().toString();
                String appointmentType = appointmentTypeTextView.getText().toString();
                String currentTime = currentTimeTextView.getText().toString();
                String userId = userid.getText().toString();

                String p1 = price1.getText().toString();
                String p2 = price2.getText().toString();
                String p3 = price3.getText().toString();
                String p4 = price4.getText().toString();
                String p5 = price5.getText().toString();
                double totalValue = 0;
                for (EditText editText : priceEditTexts) {
                    String price = editText.getText().toString();
                    if (!price.isEmpty()) {
                        totalValue += Double.parseDouble(price);
                    }
                }
                // Hiển thị tổng số tiền trên TextView
                DecimalFormat formatter = new DecimalFormat("#,### VND");
                totalTextView.setText("Total: " + formatter.format(totalValue));
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference prescriptionCollection = db.collection("BillId");

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

                prescriptionData.put("price1",p1 );
                prescriptionData.put("price2", p2);
                prescriptionData.put("price3", p3);
                prescriptionData.put("price4", p4);
                prescriptionData.put("price5", p5);
                prescriptionData.put("total", formatter.format(totalValue));
                prescriptionCollection.add(prescriptionData)
                        .addOnSuccessListener(documentReference -> {
                            String billId = documentReference.getId(); // Lưu ID tạm thời
                            prescriptionData.put("billId", billId);
                            Log.d("Prescription", "Đã lưu đơn thuốc, ID: " + billId);

                            // Hiển thị thông báo thành công
                            prescriptionCollection.document(documentReference.getId()) // Tài liệu mới được tạo
                                    .update("billId", billId) // Cập nhật tempId với ID chính xác từ Firebase
                                    .addOnSuccessListener(aVoid -> {
                                        Log.d("Prescription", "Đã cập nhật tempId với ID thực sự.");
                                        Toast.makeText(DetailPrescriptionUserActivity.this, "Đơn thuốc đã được lưu thành công.", Toast.LENGTH_SHORT).show();

                                        // Chuyển đến màn hình ListPrescriptionUserActivity
                                        Intent receiveIntent = new Intent(DetailPrescriptionUserActivity.this, ListPrescriptionUserActivity.class);
                                        startActivity(receiveIntent);

                                        // Xác nhận việc lưu đơn thuốc thành công
                                        Log.d("Prescription", "Đã lưu đơn thuốc, ID: " + billId);
                                    });
                        })
                        .addOnFailureListener(e -> {
                            // Xảy ra lỗi khi lưu đơn thuốc
                            Toast.makeText(DetailPrescriptionUserActivity.this, "Lỗi khi lưu đơn thuốc: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("Prescription", "Lỗi khi lưu đơn thuốc: " + e.getMessage());
                        });
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            String prescriptionId = intent.getStringExtra("prescriptionId");
            if (prescriptionId != null) {
                // Khởi tạo Firestore
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                // Truy vấn tài liệu theo prescriptionId
                DocumentReference prescriptionRef = db.collection("Prescriptions").document(prescriptionId);
                prescriptionRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // Lấy dữ liệu từ DocumentSnapshot và chuyển đổi thành đối tượng PrescriptionModel
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
