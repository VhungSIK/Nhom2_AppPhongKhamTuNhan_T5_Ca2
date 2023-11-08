package com.example.doandidong.doctor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.core.app.NotificationCompat;

import com.example.doandidong.R;
import com.example.doandidong.doctor.doctor_appointment.DoctorListAppointmentActivity;


public class Notification extends DoctorListAppointmentActivity {
    @Override
    protected void onCreate(Bundle savedIntanceSate){
        super.onCreate(savedIntanceSate);
        setContentView(R.layout.activity_detail_apointment_dtactivity);

        Button btnSendNotification = findViewById(R.id.btnXacNhan);
        btnSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }

            private void sendNotification() {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setContentTitle("Xác nhân lịch khám thành công")
                        .setContentText("Đã gửi đến khách hàng")
                        .setSmallIcon(R.drawable.bacsi)
                        .setLargeIcon(bitmap)
                        .build();

            }
        });

        



    }
}
