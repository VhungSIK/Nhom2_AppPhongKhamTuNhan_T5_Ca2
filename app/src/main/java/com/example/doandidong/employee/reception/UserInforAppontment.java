package com.example.doandidong.employee.reception;

import java.io.Serializable;

public class UserInforAppontment implements Serializable {
    private String userId;
    private String userName;
    private String userPhone;
    private String date;
    private String doctorName;
    private String time;
    private String type;
    private String doctorId;
    public UserInforAppontment() {
        // Default constructor
    }

    public UserInforAppontment(String userId, String userName, String userPhone, String date, String doctorName, String time, String type, String doctorId) {
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.date = date;
        this.doctorName = doctorName;
        this.time = time;
        this.type = type;
        this.doctorId = doctorId;
    }

    // Getters and setters...

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
}