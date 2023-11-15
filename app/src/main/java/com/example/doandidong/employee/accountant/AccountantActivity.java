package com.example.doandidong.employee.accountant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.doandidong.R;

public class AccountantActivity extends AppCompatActivity {
    private TextView btn_List_Account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountant);
        btn_List_Account = findViewById(R.id.btn_list_account);
        btn_List_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountantActivity.this, ListPrescriptionUserActivity.class);
                startActivity(intent);
            }
        });
    }
}