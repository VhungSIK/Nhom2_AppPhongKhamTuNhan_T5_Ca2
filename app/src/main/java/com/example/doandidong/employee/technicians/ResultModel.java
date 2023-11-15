package com.example.doandidong.employee.technicians;

import java.util.Date;

public class ResultModel {
    private String doctorName;
    private String doctorId;
    private String userName;
    private String appointmentDate;
    private String appointmentType;
    private String currentTime;
    private String bloodGroup;
    private String quantification;
    private String index;
    private String totalAnalysis;
    private String userId;

    public ResultModel() {
        // Required empty public constructor for Firebase
    }

    public ResultModel(String doctorName,String doctorId, String userName, String appointmentDate, String appointmentType, String currentTime, String bloodGroup, String quantification, String index, String totalAnalysis, String userId) {
        this.doctorName = doctorName;
        this.userName = userName;
        this.appointmentDate = appointmentDate;
        this.appointmentType = appointmentType;
        this.currentTime = currentTime;
        this.bloodGroup = bloodGroup;
        this.quantification = quantification;
        this.index = index;
        this.totalAnalysis = totalAnalysis;
        this.userId = userId;
        this.doctorId = doctorId;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getUserName() {
        return userName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getQuantification() {
        return quantification;
    }

    public String getIndex() {
        return index;
    }

    public String getTotalAnalysis() {
        return totalAnalysis;
    }
}
