package com.example.doandidong.doctor.doctor_appointment;

import java.io.Serializable;

public class DoctorAppointment implements Serializable {
        String idA;
        String DoctorName;
        String Type;
        String Date;
        String Time;
        String UserName;
        String UserPhone;
        String UserEmail;
    String UserId;
    public DoctorAppointment(){

        }

    public DoctorAppointment(String idA, String doctorName, String type, String date, String time, String userName, String userPhone, String userEmail, String userId) {
        this.idA = idA;
        DoctorName = doctorName;
        Type = type;
        Date = date;
        Time = time;
        UserName = userName;
        UserPhone = userPhone;
        UserEmail = userEmail;
        UserId = userId;
    }

    public DoctorAppointment(String idA, String doctorName, String type, String date, String time, String userName, String userPhone, String userEmail) {
            this.idA = idA;
            this.DoctorName = doctorName;
            this.Type = type;
            this.Date = date;
            this.Time = time;
            this.UserName = userName;
            this.UserPhone = userPhone;
            this.UserEmail = userEmail;
        }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
            return UserName;
        }

        public void setUserName(String userName) {
            UserName = userName;
        }

        public String getUserPhone() {
            return UserPhone;
        }

        public void setUserPhone(String userPhone) {
            UserPhone = userPhone;
        }

        public String getUserEmail() {
            return UserEmail;
        }

        public void setUserEmail(String userEmail) {
            UserEmail = userEmail;
        }

        public String getIdA() {
            return idA;
        }

        public void setIdA(String idA) {
            this.idA = idA;
        }

        public String getDoctorName() {
            return DoctorName;
        }

        public void setDoctorName(String doctorName) {
            this.DoctorName = doctorName;
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            this.Type = type;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String date) {
            this.Date = date;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String time) {
            this.Time = time;
        }
    }
