package com.example.doandidong.employee.reception;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.doandidong.R;

public class ReceptionActivity extends AppCompatActivity {
    private TextView btn_List_Reception;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception);
        btn_List_Reception = findViewById(R.id.btn_list_reception);
        btn_List_Reception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReceptionActivity.this, ListReceptionActivity.class);
                startActivity(intent);
            }
        });
    }
}