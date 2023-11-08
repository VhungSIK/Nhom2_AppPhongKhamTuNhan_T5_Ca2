package com.example.doandidong.employee.technicians;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.doandidong.R;

public class DetailRequestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_request);
        Intent intent = getIntent();
        String doctorName = intent.getStringExtra("doctorName");
        String appointmentType = intent.getStringExtra("appointmentType");
        String appointmentDate = intent.getStringExtra("appointmentDate");
        String userName = intent.getStringExtra("userName");
        String userPhone = intent.getStringExtra("userPhone");
        String note = intent.getStringExtra("note");

        TextView doctorNameTextView = findViewById(R.id.doctorNameTextView);
        TextView appointmentTypeTextView = findViewById(R.id.appointmentTypeTextView);
        TextView appointmentDateTextView = findViewById(R.id.appointmentDateTextView);
        TextView userNameTextView = findViewById(R.id.userNameTextView);
        TextView userPhoneTextView = findViewById(R.id.userPhoneTextView);
        TextView noteTextView = findViewById(R.id.noteTextView);
        TextView btn_Click_Send_Require = findViewById(R.id.btn_click_send_require);

        doctorNameTextView.setText("Doctor Name: " + doctorName);
        appointmentTypeTextView.setText("Appointment Type: " + appointmentType);
        appointmentDateTextView.setText("Appointment Date: " + appointmentDate);
        userNameTextView.setText("User Name: " + userName);
        userPhoneTextView.setText("User Phone: " + userPhone);
        noteTextView.setText("Note: " + note);
        btn_Click_Send_Require.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailRequestActivity.this, SendRequireActivity.class);
                startActivity(i);
            }
        });
    }
}