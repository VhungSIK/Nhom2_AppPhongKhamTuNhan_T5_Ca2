package com.example.doandidong.employee.technicians;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.doandidong.R;

public class TechniciansActivity extends AppCompatActivity {
Button btn_receive;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technicians);
        btn_receive = findViewById(R.id.btn_receive);
        btn_receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TechniciansActivity.this, ReceiveActivity.class);
                startActivity(intent);
            }
        });
    }
}