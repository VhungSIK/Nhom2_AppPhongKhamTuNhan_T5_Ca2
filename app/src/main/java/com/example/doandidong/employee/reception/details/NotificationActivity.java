package com.example.doandidong.employee.reception.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.doandidong.R;

public class NotificationActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        textView = findViewById(R.id.tvData);
        String data = getIntent().getStringExtra("data");
        textView.setText(data);
    }
}