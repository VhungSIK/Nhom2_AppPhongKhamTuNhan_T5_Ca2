package com.example.doandidong.employee.reception.details;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import com.example.doandidong.R;
import com.example.doandidong.employee.reception.ListReceptionActivity;

public class PushNotification extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    private Context context;


    public PushNotification(Context context) {
        this.context = context;
    }

    // Bạn không cần phương thức onCreate trong class này

    // Sự kiện click cho nút Xác nhận


    public void makeNotification() {
        Log.d("Notification", "makeNotification method called");
        String channelID = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelID);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        builder.setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("Thông báo")
                .setContentText("Đã xác nhận lịch khám")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(context, ListReceptionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("data", "Đã gửi lịch khám tới BS và KH");

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("Permission", "Permission status: " + ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_BOOT_COMPLETED));

            NotificationChannel notificationChannel = new NotificationChannel(channelID, "Thông báo", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        //  kiểm tra quyền ở đây

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECEIVE_BOOT_COMPLETED) != PackageManager.PERMISSION_GRANTED) {
            // Yêu cầu cấp quyền nếu chưa có
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.RECEIVE_BOOT_COMPLETED}, REQUEST_CODE);
        } else {
            // Quyền đã được cấp
            notificationManager.notify(0, builder.build());
        }

    }
}

