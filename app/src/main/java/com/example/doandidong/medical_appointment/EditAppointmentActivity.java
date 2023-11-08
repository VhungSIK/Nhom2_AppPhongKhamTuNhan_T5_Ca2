package com.example.doandidong.medical_appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.doandidong.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;

public class EditAppointmentActivity extends AppCompatActivity {
    TextView editTextNewDate, editTextNewTime;
    TextView tvDoctorName, btn_click_arrow;
    TextView tvType;
    TextView btnSave;

    FirebaseFirestore db;
    DocumentReference docRef;
    String appointmentId;
    int initialHour, initialMinute;
    int initialYear, initialMonth, initialDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment);
        getSupportActionBar().setTitle("Sửa thông tin lịch đã đặt");
        editTextNewDate = findViewById(R.id.editTextNewDate);
        editTextNewTime = findViewById(R.id.editTextNewTime);
        tvDoctorName = findViewById(R.id.tvDoctorName);
        tvType = findViewById(R.id.tvType);
        btnSave = findViewById(R.id.btnSave);
        Calendar calendar = Calendar.getInstance();
        initialYear = calendar.get(Calendar.YEAR);
        initialMonth = calendar.get(Calendar.MONTH);
        initialDay = calendar.get(Calendar.DAY_OF_MONTH);
        editTextNewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        editTextNewTime = findViewById(R.id.editTextNewTime);
        String initialTime = getIntent().getStringExtra("initialTime");
        if (initialTime != null) {
            String[] timeParts = initialTime.split(":");
            if (timeParts.length == 2) {
                initialHour = Integer.parseInt(timeParts[0]);
                initialMinute = Integer.parseInt(timeParts[1]);
            }
            editTextNewTime.setText(initialTime);
        }

        editTextNewTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        db = FirebaseFirestore.getInstance();

        appointmentId = getIntent().getStringExtra("appointmentId");
        docRef = db.collection("Appointment").document(appointmentId);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String doctorName = documentSnapshot.getString("DoctorName");
                    String type = documentSnapshot.getString("Type");

                    tvDoctorName.setText(doctorName);
                    tvType.setText(type);

                    String existingDate = documentSnapshot.getString("Date");
                    String existingTime = documentSnapshot.getString("Time");
                    editTextNewDate.setText(existingDate);
                    editTextNewTime.setText(existingTime);
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String newDate = editTextNewDate.getText().toString();
                final String newTime = editTextNewTime.getText().toString();

                // Kiểm tra giới hạn thời gian từ 7:00 sáng đến 8:00 tối
                int hour = Integer.parseInt(newTime.split(":")[0]);
                if (hour < 7 || hour >= 20) {
                    Toast.makeText(EditAppointmentActivity.this, "Thời gian không hợp lệ. Phòng khám chỉ mở từ 7:00 sáng đến 8:00 tối.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Lấy ngày hiện tại
                Calendar currentCalendar = Calendar.getInstance();
                int currentYear = currentCalendar.get(Calendar.YEAR);
                int currentMonth = currentCalendar.get(Calendar.MONTH);
                int currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH);

                // Kiểm tra xem ngày đã chọn có nằm trong quá khứ không
                String[] dateParts = newDate.split("/");
                int selectedYear = Integer.parseInt(dateParts[2]);
                int selectedMonth = Integer.parseInt(dateParts[1]) - 1;
                int selectedDay = Integer.parseInt(dateParts[0]);

                if (selectedYear < currentYear || (selectedYear == currentYear && selectedMonth < currentMonth) || (selectedYear == currentYear && selectedMonth == currentMonth && selectedDay < currentDay)) {
                    Toast.makeText(EditAppointmentActivity.this, "Không thể đặt lịch vào ngày đã qua.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra ngày không được chọn thứ 7 hoặc Chủ Nhật
                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.set(selectedYear, selectedMonth, selectedDay);
                int selectedDayOfWeek = selectedCalendar.get(Calendar.DAY_OF_WEEK);
                if (selectedDayOfWeek == Calendar.SATURDAY || selectedDayOfWeek == Calendar.SUNDAY) {
                    Toast.makeText(EditAppointmentActivity.this, "Không thể đặt lịch vào thứ 7 hoặc Chủ Nhật.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Truy vấn Firebase để kiểm tra xem đã có lịch hẹn cho ngày và giờ đã chọn chưa
                CollectionReference appointmentsRef = db.collection("Appointment");
                Query query = appointmentsRef
                        .whereEqualTo("Date", newDate)
                        .whereEqualTo("Time", newTime);

                query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // Nếu có lịch hẹn tồn tại, không cho phép đặt và thông báo
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // Hiển thị thông báo cho người dùng rằng đã có người đặt lịch hẹn vào thời gian này
                            Toast.makeText(EditAppointmentActivity.this, "Giờ này đã có người đặt.", Toast.LENGTH_SHORT).show();
                        } else {
                            // Nếu không có lịch hẹn tồn tại và thời gian hợp lệ, chấp nhận lịch hẹn mới và cập nhật dữ liệu
                            docRef.update("Date", newDate, "Time", newTime);

                            Intent intent = new Intent(EditAppointmentActivity.this, AppointmentInfoActivity.class);
                            intent.putExtra("appointmentId", appointmentId);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });

    }

    private void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                EditAppointmentActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                        editTextNewTime.setText(selectedTime);
                    }
                },
                initialHour,
                initialMinute,
                true
        );

        timePickerDialog.show();
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                EditAppointmentActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year);
                        editTextNewDate.setText(selectedDate);
                    }
                },
                initialYear,
                initialMonth,
                initialDay
        );

        datePickerDialog.show();
    }
}
