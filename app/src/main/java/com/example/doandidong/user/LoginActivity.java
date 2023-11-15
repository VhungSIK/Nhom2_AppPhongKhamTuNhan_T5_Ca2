package com.example.doandidong.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doandidong.MainActivity;
import com.example.doandidong.R;
import com.example.doandidong.doctor.DoctorActivity;
import com.example.doandidong.employee.accountant.AccountantActivity;
import com.example.doandidong.employee.reception.ReceptionActivity;
import com.example.doandidong.employee.technicians.TechniciansActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.auth.User;

import androidx.annotation.NonNull;


public class LoginActivity extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button btLogin, btRegister;
    Button googleSignInButton;
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
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
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        // Handle connection failure
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        findViewById(R.id.googleSignInButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInWithGoogle();
            }
        });

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
                                    DocumentReference reception = db.collection("Reception").document(currentUserUID);

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
                                                                        reception.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                                            @Override
                                                                            public void onSuccess(DocumentSnapshot userSnapshot) {
                                                                                if (userSnapshot.exists()) {
                                                                                    String userType = userSnapshot.getString("userType");

                                                                                    if ("reception".equals(userType)) {
                                                                                        Toast.makeText(LoginActivity.this, "Bạn là nhân viên tiếp tân", Toast.LENGTH_LONG).show();
                                                                                        Intent intent = new Intent(LoginActivity.this, ReceptionActivity.class);
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
                            }
                        });
            }
        });
    }

    private void signInWithGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Handle unsuccessful login
            }
        }
    }

    // Trong phần firebaseAuthWithGoogle:
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                saveUserInfoToFirestore(user.getUid(), "user");
                                Log.d("LoginActivity", "Đăng nhập bằng Google thành công");
                                Toast.makeText(LoginActivity.this, "Đăng nhập bằng Google thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {
                            // Xác nhận và xử lý lỗi khi đăng nhập không thành công
                            Log.e("LoginActivity", "Đăng nhập bằng Google không thành công: " + task.getException().getMessage());
                            Toast.makeText(LoginActivity.this, "Đăng nhập bằng Google không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // Trong phần saveUserInfoToFirestore:
    private void saveUserInfoToFirestore(String userId, String userType) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef = db.collection("User").document(userId);
        // Giả sử đã có class User hoặc thay thế bằng lớp tương ứng trong firestore của bạn
        User user = new User(userType);

        userRef.set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("LoginActivity", "Thông tin người dùng đã được lưu vào Firestore");
                            Toast.makeText(LoginActivity.this, "Thông tin người dùng đã được lưu", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("LoginActivity", "Lỗi khi lưu thông tin người dùng vào Firestore: " + task.getException().getMessage());
                            Toast.makeText(LoginActivity.this, "Lưu thông tin người dùng thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}