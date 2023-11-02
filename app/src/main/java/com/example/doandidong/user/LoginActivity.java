package com.example.doandidong.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doandidong.MainActivity;
import com.example.doandidong.R;
import com.example.doandidong.doctor.DoctorActivity;
import com.example.doandidong.employee.accountant.AccountantActivity;
import com.example.doandidong.employee.technicians.TechniciansActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button btLogin, btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Đăng Nhập");

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        btRegister = findViewById(R.id.btRegister);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = etEmail.getText().toString();
                final String password = etPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Email không được bỏ trống!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Mật khẩu không được bỏ trống!", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    if (password.length() < 6) {
                                        etPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    final String currentUserUID = auth.getCurrentUser().getUid();
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    DocumentReference userRef = db.collection("User").document(currentUserUID);
                                    DocumentReference doctorRef = db.collection("Doctor").document(currentUserUID);
                                    DocumentReference accountant = db.collection("Accountant").document(currentUserUID);
                                    DocumentReference technicians = db.collection("Technicians").document(currentUserUID);

                                    userRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot userSnapshot) {
                                            if (userSnapshot.exists()) {
                                                String userType = userSnapshot.getString("userType");

                                                if ("user".equals(userType)) {
                                                    Toast.makeText(LoginActivity.this, "Sức khỏe quý hơn tiền bạc", Toast.LENGTH_LONG).show();
                                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    Toast.makeText(LoginActivity.this, "Loại người dùng không hợp lệ", Toast.LENGTH_LONG).show();
                                                }
                                            } else {
                                                technicians.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onSuccess(DocumentSnapshot userSnapshot) {
                                                        if (userSnapshot.exists()) {
                                                            String userType = userSnapshot.getString("userType");

                                                            if ("technicians".equals(userType)) {
                                                                Toast.makeText(LoginActivity.this, "Bạn là nhân viên xét nghiệm", Toast.LENGTH_LONG).show();
                                                                Intent intent = new Intent(LoginActivity.this, TechniciansActivity.class);
                                                                startActivity(intent);
                                                                finish();
                                                            } else {
                                                                Toast.makeText(LoginActivity.this, "Loại người dùng không hợp lệ", Toast.LENGTH_LONG).show();
                                                            }
                                                        } else {
                                                            accountant.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onSuccess(DocumentSnapshot userSnapshot) {
                                                                    if (userSnapshot.exists()) {
                                                                        String userType = userSnapshot.getString("userType");

                                                                        if ("accountant".equals(userType)) {
                                                                            Toast.makeText(LoginActivity.this, "Bạn là nhân viên kế toán", Toast.LENGTH_LONG).show();
                                                                            Intent intent = new Intent(LoginActivity.this, AccountantActivity.class);
                                                                            startActivity(intent);
                                                                            finish();
                                                                        } else {
                                                                            Toast.makeText(LoginActivity.this, "Loại người dùng không hợp lệ", Toast.LENGTH_LONG).show();
                                                                        }
                                                                    } else {
                                                                        doctorRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                            @Override
                                                                            public void onSuccess(DocumentSnapshot doctorSnapshot) {
                                                                                if (doctorSnapshot.exists()) {
                                                                                    // Người dùng là bác sĩ
                                                                                    Toast.makeText(LoginActivity.this, "Bạn là bác sĩ", Toast.LENGTH_LONG).show();
                                                                                    Intent intent = new Intent(LoginActivity.this, DoctorActivity.class);
                                                                                    startActivity(intent);
                                                                                    finish();
                                                                                } else {
                                                                                    Toast.makeText(LoginActivity.this, "Không tìm thấy thông tin người dùng", Toast.LENGTH_LONG).show();
                                                                                }
                                                                            }
                                                                        });
                                                                    }
                                                                }
                                                            });
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    });
                                }
                            }
                        });
            }
        });
    }
}