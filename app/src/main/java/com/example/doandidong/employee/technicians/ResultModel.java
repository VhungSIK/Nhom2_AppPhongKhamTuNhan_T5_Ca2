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

    public ResultModel(String doctorName, String doctorId, String userName, String appointmentDate, String appointmentType, String currentTime, String bloodGroup, String quantification, String index, String totalAnalysis, String userId) {
        this.doctorName = doctorName;
        this.doctorId = doctorId;
        this.userName = userName;
        this.appointmentDate = appointmentDate;
        this.appointmentType = appointmentType;
        this.currentTime = currentTime;
        this.bloodGroup = bloodGroup;
        this.quantification = quantification;
        this.index = index;
        this.totalAnalysis = totalAnalysis;
        this.userId = userId;
    }

    public String getDoctorName() {
        return doctorName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getQuantification() {
        return quantification;
    }

    public void setQuantification(String quantification) {
        this.quantification = quantification;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTotalAnalysis() {
        return totalAnalysis;
    }

    public void setTotalAnalysis(String totalAnalysis) {
        this.totalAnalysis = totalAnalysis;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}